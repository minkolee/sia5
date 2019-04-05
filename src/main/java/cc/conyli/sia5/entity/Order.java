package cc.conyli.sia5.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "必须填写收件人")
    @Column(name = "delivery_name")
    private String delivery_name;

    @NotNull(message = "必须填写门牌号")
    @Column(name = "delivery_street")
    private String delivery_street;

    @NotNull(message = "必须填写城市")
    @Column(name = "delivery_city")
    private String delivery_city;

    @NotNull(message = "必须填写国家")
    @Column(name = "delivery_state")
    private String delivery_state;

    @NotNull(message = "必须填写邮编")
    @Column(name = "delivery_zip")
    private String delivery_zip;

    @NotNull(message = "必须填写信用卡号码")
    @Column(name = "cc_number")
    private String cc_number;

    @NotNull(message = "必须填写过期日")
    @Column(name = "cc_expiration")
    private String cc_expiration;

    @NotNull(message = "必须填写CVV2码")
    @Column(name = "cc_cvv")
    private String cc_cvv;

    @Column(name = "placed_at")
    private Date placed_at;

    @ManyToMany
    @JoinTable(name = "taco_order", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "taco_id"))
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void placedAt() {
        this.placed_at = new Date();
    }

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    public void addUser(User user) {
        this.user = user;
    }

}
