package cc.conyli.sia5.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "不能不输入")
    @Size(min = 2, message = "至少两个字符")
    @Column(name = "name")
    private String name;

    @NotNull(message = "不能不输入")
    @Size(min = 2, message = "至少两个字符")
    @Column(name = "type")
    private String type;
}
