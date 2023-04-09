package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Classes;


public interface ClassesMapper extends BaseMapper<Classes>{
    Page<Classes> findPage(Page<Classes> page);
}
