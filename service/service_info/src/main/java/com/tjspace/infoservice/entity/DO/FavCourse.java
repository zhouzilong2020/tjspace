package com.tjspace.infoservice.entity.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Fu Lin
 */
@Data
public class FavCourse implements Serializable {
    static {
        ID = "id";
        USER_ID = "user_id";
        COURSE_ID = "course_id";
        IS_DELETED = "is_deleted";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
    }

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    public static final String ID;

    private String userId;
    public static final String USER_ID;

    private String courseId;
    public static final String COURSE_ID;

    private Boolean isDeleted = false;
    public static final String IS_DELETED;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    public static final String GMT_CREATE;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    public static final String GMT_MODIFIED;
}
