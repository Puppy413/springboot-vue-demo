package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Classes;
import com.example.demo.entity.Curriculum;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.CurriculumMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Resource
    CurriculumMapper curriculumMapper;

    @PostMapping
    public Result<?> save (@RequestBody Curriculum curriculum){

        curriculumMapper.insert(curriculum);
        return Result.success();
    }

    @PutMapping
    public Result<?> update (@RequestBody Curriculum curriculum){
        curriculumMapper.updateById(curriculum);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        curriculumMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage (@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Curriculum>wrapper = Wrappers.<Curriculum>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Curriculum::getCurname,search);
        }
//        Page<Curriculum> curriculumPage = curriculumMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        Page<Curriculum> curriculumPage = curriculumMapper.findPage(new Page<>(pageNum,pageSize));
        return Result.success(curriculumPage);
    }

}
