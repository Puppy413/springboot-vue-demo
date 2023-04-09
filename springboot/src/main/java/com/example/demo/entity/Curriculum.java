package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("curriculum")
@Data
public class Curriculum {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String curname;
    private String curmajor;
    private String curyear;
    private String curteacher;
    private String curtime;

    @TableField(exist = false)
    private List<Grade> gradeList;

}
