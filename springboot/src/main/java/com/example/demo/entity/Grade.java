package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("grade")
@Data
public class Grade {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String gsname;
    private String gcurname;
    private String gtname;
    private String gmajor;
    private Integer score;
    private Double gp;

}

//    @TableField(exist = false)
//    private List<Student> studentList;


