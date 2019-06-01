package com.xuecheng.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ExcelWriteImage implements Serializable {
    private Integer dx1  = 0;
    private Integer dy1 = 0;
    private Integer dx2 = 0;
    private Integer dy2 = 0;
    private Short col1 = 0;
    private Integer row1 = 0;
    private Short col2 = 0;
    private Integer row2 = 0;
    private Integer picType = 5;
    private byte[] picData;
}
