package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("student")
@Data
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String num;
    private String password;
    private String saddress;
    private String stel;
    private String sdepartment;
    private String smajor;
    private String sclass;
    private Integer role;



}
