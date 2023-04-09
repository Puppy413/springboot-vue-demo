package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.TeacherMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    TeacherMapper teacherMapper;

    @PostMapping
    public Result<?> save (@RequestBody Teacher teacher){
        if(teacher.getPassword()==null){
            teacher.setPassword("123456");
        }
        if(teacher.getRole()==null){
            teacher.setRole(1);
        }
        teacherMapper.insert(teacher);
        return Result.success();
    }
    @PostMapping("/login")
    public Result<?> login (@RequestBody Teacher teacher){
        Teacher res = teacherMapper.selectOne(Wrappers.<Teacher>lambdaQuery().eq(Teacher::getNum,teacher.getNum()).eq(Teacher::getPassword,teacher.getPassword()));
        if(res==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(res);
    }
    @PutMapping
    public Result<?> update (@RequestBody Teacher teacher){
        teacherMapper.updateById(teacher);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        teacherMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage (@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Teacher>wrapper = Wrappers.<Teacher>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Teacher::getName,search);
        }
        Page<Teacher> teacherPage=teacherMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(teacherPage);
    }
}
