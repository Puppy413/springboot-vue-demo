package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("teacher")
@Data
public class Teacher {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String num;
    private String password;
    private String tsex;
    private Integer tage;
    private String taddress;
    private String ttel;
    private Integer role;

}
