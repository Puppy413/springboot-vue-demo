package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Curriculum;
import com.example.demo.entity.Teacher;


public interface CurriculumMapper extends BaseMapper<Curriculum>{
    Page<Curriculum> findPage(Page<Curriculum> page);
}
