package com.xuecheng.entities.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "xc_button")
public class Button implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BtnName")
    private String btnName;

    @Column(name = "BtnClass")
    private String btnClass;

    @Column(name = "BgImage")
    private String bgImage;

    @Column(name = "BthStyle")
    private String bthStyle;

    @Column(name = "Status")
    private Integer status;
}
