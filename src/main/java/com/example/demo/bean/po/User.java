package com.example.demo.bean.po;

import lombok.Data;

@Data
public class User extends SuperEntity {

    private String acount;

    private String realName;

    private String phone;

    private Byte sex;

    private String passwd;

    private String salt;

    private Byte type;

    private Long companyId;

    private Long websiteId;

    private Boolean status;

    private String remark;


}