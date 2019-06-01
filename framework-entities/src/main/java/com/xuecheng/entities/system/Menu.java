package com.xuecheng.entities.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "xc_menu")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MenuName")
    private String menuName;

    @Column(name = "ParentID")
    private Long parentId;

    @Column(name = "MenuPath")
    private String menuPath;

    @JoinTable(name="xc_menu_btn",
            joinColumns={@JoinColumn(name="MenuID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="BtnID", referencedColumnName="ID")})
    @ManyToMany
    private Set<Button> buttons;
}
