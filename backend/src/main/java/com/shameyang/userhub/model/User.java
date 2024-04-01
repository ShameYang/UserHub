package com.shameyang.userhub.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ShameYang
 * @date 2024/3/31 22:30
 * @description 封装用户数据
 */
@Data
@TableName("`user`")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
