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
@Table(name = "xc_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RoleName")
    private String roleName;

    @Column(name = "Status")
    private Integer status;

    @JoinTable(name="xc_role_menu",
            joinColumns={@JoinColumn(name="RoleID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="MenuID", referencedColumnName="ID")})
    @ManyToMany
    private Set<Menu> menus;
}
