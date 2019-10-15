package com.xuecheng.entities.system;


import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "xc_userinfo")
@Builder
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "Password")
    private String password;

    @Column(name = "Mobile")
    private String mobile;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "NickName")
    private String nickName;


    @Column(name = "RealName")
    private String realName;


    @JoinTable(name="xc_user_role",
            joinColumns={@JoinColumn(name="UserInfoID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="RoleID", referencedColumnName="ID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
