package com.tjspace.infoservice.entity.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "InfoTeacher对象", description = "")
public class InfoTeacher implements Serializable {

    // 本字段和数据库一一对应
    static {
        ID = "id";
        NAME = "name";
        DEPT_ID = "dept_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
        TITLE = "title";
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    final static public String ID;

    @ApiModelProperty(value = "教师姓名")
    private String name;
    final static public String NAME;

    @ApiModelProperty(value = "所属院系id")
    private String deptId;
    final static public String DEPT_ID;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    final static public String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    final static public String GMT_MODIFIED;

    @ApiModelProperty(value = "教师职称")
    private String title;
    final static public String TITLE;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        InfoTeacher teacher = (InfoTeacher) obj;
        return teacher.getName().equals(this.getName());
    }

}
