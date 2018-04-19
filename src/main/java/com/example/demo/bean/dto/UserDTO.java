package com.example.demo.bean.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @author jqChan
 * @date 2018/4/19
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class UserDTO {

    private Long id;

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
