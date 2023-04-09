package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.mapper.GradeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Resource
    GradeMapper gradeMapper;

    @PostMapping
    public Result<?> save(@RequestBody Grade grade) {
        if (grade.getGp() == null) {
            if (grade.getScore() > 0 && grade.getScore() < 60) {
                grade.setGp(0.0);
            } else if (grade.getScore() >= 60 && grade.getScore() < 70) {
                grade.setGp(1.0);
            } else if (grade.getScore() >= 70 && grade.getScore() < 80) {
                grade.setGp(2.0);
            } else if (grade.getScore() >= 80 && grade.getScore() < 90) {
                grade.setGp(3.0);
            } else if (grade.getScore() >= 90 && grade.getScore() <= 100) {
                grade.setGp(4.0);
            } else {
                grade.setGp(-1.0);
            }

        }
        gradeMapper.insert(grade);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Grade grade) {
        if (grade.getScore() > 0 && grade.getScore() < 60) {
            grade.setGp(0.0);
        } else if (grade.getScore() >= 60 && grade.getScore() < 70) {
            grade.setGp(1.0);
        } else if (grade.getScore() >= 70 && grade.getScore() < 80) {
            grade.setGp(2.0);
        } else if (grade.getScore() >= 80 && grade.getScore() < 90) {
            grade.setGp(3.0);
        } else if (grade.getScore() >= 90 && grade.getScore() <= 100) {
            grade.setGp(4.0);
        } else {
            grade.setGp(-1.0);
        }
        gradeMapper.updateById(grade);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        gradeMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Grade> wrapper = Wrappers.<Grade>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Grade::getGcurname, search);
        }
        Page<Grade> gradePage = gradeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(gradePage);
    }
}
