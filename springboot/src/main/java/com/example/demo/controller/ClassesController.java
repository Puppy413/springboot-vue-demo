package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Classes;
import com.example.demo.mapper.ClassesMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Resource
    ClassesMapper classesMapper;

    @PostMapping
    public Result<?> save (@RequestBody Classes classes){

        classesMapper.insert(classes);
        return Result.success();
    }

    @PutMapping
    public Result<?> update (@RequestBody Classes classes){
        classesMapper.updateById(classes);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        classesMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage (@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Classes>wrapper = Wrappers.<Classes>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Classes::getCname,search);
        }
        Page<Classes> classesPage = classesMapper.findPage(new Page<>(pageNum,pageSize));
        return Result.success(classesPage);
    }
}
