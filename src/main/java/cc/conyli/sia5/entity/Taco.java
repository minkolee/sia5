package cc.conyli.sia5.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "taco")
public class Taco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Notnull对于长度为0的字符串不起作用
    @NotNull
    @Size(min = 3, message = "至少三个字符")
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "taco_ingredient", joinColumns = @JoinColumn(name = "taco_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @Size(min = 1, message = "至少选择一个原料")
    private List<Ingredient> ingredients = new ArrayList<>();

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    //添加一个方法用于给自己的外键添加关联对象
    public void addIngredient(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
    }



}
