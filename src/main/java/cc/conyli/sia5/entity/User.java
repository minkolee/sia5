package cc.conyli.sia5.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Table(name = "user")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "必须填写姓名")
    @Column(name = "username")
    private String username;

    @NotNull(message = "必须填写密码")
    @Column(name = "password")
    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
