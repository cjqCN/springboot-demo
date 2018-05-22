package com.example.demo.bean.po;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User extends SuperEntity {

    private String userName;

    private String realName;

    private String email;

    private String phone;

    private Byte sex;

    private String passwd;

    private String salt;

    private Byte type;

    private Long supplierId;

    private Byte status;

    private String remark;


}