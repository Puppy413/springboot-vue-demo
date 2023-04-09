package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("classes")
@Data
public class Classes {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String cname;
    private String cdepartment;
    private String cmajor;

    @TableField(exist = false)
    private List<Student> studentList;

}
