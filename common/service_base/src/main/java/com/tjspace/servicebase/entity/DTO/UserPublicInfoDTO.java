package com.tjspace.servicebase.entity.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tjspace.servicebase.Serialize.AvatarSerialize;
import com.tjspace.servicebase.Serialize.DegreeSerialize;
import com.tjspace.servicebase.Serialize.GenderSerialize;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPublicInfoDTO {
    static {
        USER_ID = "id";
        NICKNAME = "nickname";
        GENDER = "gender";
        GRADE = "grade";
        DEGREE = "degree";
        DEPT_NAME = "dept_name";
        AVATAR = "avatar";
    }

    @JsonIgnore
    private String userId;
    public static final String USER_ID;

    private String nickname;
    public static final String NICKNAME;

    @JsonSerialize(using = GenderSerialize.class)
    private Boolean gender;
    public static final String GENDER;

    private Integer grade;
    public static final String GRADE;

    @JsonSerialize(using = DegreeSerialize.class)
    private Integer degree;
    public static final String DEGREE;

    private String deptName;
    public static final String DEPT_NAME;

    @JsonSerialize(using = AvatarSerialize.class)
    private String avatar;
    public static final String AVATAR;

}
