package com.tjspace.infoservice.entity.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@ApiModel(value = "InfoMajor对象", description = "")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoMajor implements Serializable {
    static {
        ID = "id";
        NAME = "name";
        DEPT_ID = "dept_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    static final public String ID;

    @ApiModelProperty(value = "专业名称")
    private String name;
    static final public String NAME;

    @ApiModelProperty(value = "所属院系名称")
    private String deptId;
    static final public String DEPT_ID;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    static final public String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    static final public String GMT_MODIFIED;
}
