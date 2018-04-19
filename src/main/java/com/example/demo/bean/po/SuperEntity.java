package com.example.demo.bean.po;

import com.example.demo.util.id.GenerateIdUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static com.example.demo.util.TimeUtil.date;


@Data
public class SuperEntity implements Serializable {

    //=====================================数据库字段对应关系 start======================================

    public static final String ID_COLUMN_NAME = "id";
    public static final String CREATE_DATE_COLUMN_NAME = "create_date";
    public static final String CREATE_USER_ID_COLUMN_NAME = "create_user_id";
    public static final String MODIFY_DATE_COLUMN_NAME = "modify_date";
    public static final String MODIFY_USER_ID_COLUMN_NAME = "modify_user_id";
    public static final String DELETE_DATE_COLUMN_NAME = "delete_date";
    public static final String DELETE_USER_ID_COLUMN_NAME = "delete_user_id";
    public static final String DELETE_ENUM_COLUMN_NAME = "is_deleted";
    public static final String LOCK_VERSION_COLUMN_NAME = "lock_version";
    public static final String CREATE_USER_NAME_COLUMN_NAME = "create_user_name";
    public static final String MODIFY_USER_NAME_COLUMN_NAME = "modify_user_name";
    public static final String DELETE_USER_NAME_COLUMN_NAME = "delete_user_name";

    //=====================================数据库字段对应关系 end======================================

    private static final long serialVersionUID = 7560570481504032191L;

    private Long id;

    private int isDeleted;

    private Long lockVersion;

    //======================================================

    private Date createDate;

    private Long createUserId;

    private String createUserName;

    private Date modifyDate;

    private Long modifyUserId;

    private String modifyUserName;

    private Date deleteDate;

    private Long deleteUserId;

    private String deleteUserName;

    //=====================================set get start======================================

    public void setDelete(String userName, Long userId, Date date) {
        this.deleteDate = date;
        this.deleteUserId = userId;
        this.isDeleted = 1;
        this.deleteUserName = userName;
    }

    public void setDelete(String userName, Long userId) {
        this.setDelete(userName, userId, date());
    }

    public void setModify(String userName, Long userId) {
        this.setModify(userName, userId, date());
    }

    public void setModify(String userName, Long userId, Date date) {
        this.modifyUserName = userName;
        this.modifyDate = date;
        this.modifyUserId = userId;
    }

    public void setCreate(String userName, Long userId, Date date) {
        this.id = GenerateIdUtil.getId();
        this.createDate = date;
        this.createUserId = userId;
        this.createUserName = userName;
        this.isDeleted = 0;
    }

    public void setCreate(String userName, Long userId) {
        this.setCreate(userName, userId, date());
    }


    //=====================================set get end======================================

}
