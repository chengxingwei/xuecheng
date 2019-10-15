package com.xuecheng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BaseDTO implements Serializable {

    /**
     * 当前页
     */
    private Integer pageIndex;

    /**
     * 页码大小
     */
    private Integer pageSize;
}
