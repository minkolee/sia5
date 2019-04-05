package cc.conyli.sia5.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class UserForConfirmPassword implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "必须填写姓名")
    private String username;

    @NotNull(message = "必须填写密码")
    private String password;

    @NotNull(message = "必须填写密码")
    private String confirm_password;

    @NotNull(message = "必须填写全名")
    private String fullname;

    @NotNull(message = "必须填写街道")
    private String street;

    @NotNull(message = "必须填写城市")
    private String city;

    @NotNull(message = "必须填写省份")
    private String state;

    @NotNull(message = "必须填写邮编")
    private String zip;

    @NotNull(message = "必须填写手机号码")
    private String phonenumber;

}
