package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    StudentMapper studentMapper;

    @PostMapping
    public Result<?> save (@RequestBody Student student){
        if(student.getPassword()==null){
            student.setPassword("123456");
        }
        if(student.getRole()==null){
            student.setRole(2);
        }
        studentMapper.insert(student);
        return Result.success();
    }
    @PostMapping("/login")
    public Result<?> login (@RequestBody Student student){
        Student res = studentMapper.selectOne(Wrappers.<Student>lambdaQuery().eq(Student::getNum,student.getNum()).eq(Student::getPassword,student.getPassword()));
        if(res==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(res);
    }
    @PutMapping
    public Result<?> update (@RequestBody Student student){
        studentMapper.updateById(student);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        studentMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage (@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Student>wrapper = Wrappers.<Student>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Student::getName,search);
        }
        Page<Student> studentPage = studentMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(studentPage);
    }
}
