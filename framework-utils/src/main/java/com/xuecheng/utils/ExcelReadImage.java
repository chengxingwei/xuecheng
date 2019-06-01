package com.xuecheng.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.PictureData;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ExcelReadImage implements Serializable {
//    //所属表单索引
//    private Integer sheetIndex = 0;
    //列号
    private Integer colIndex = 0;
    //行号
    private Integer rowIndex = 0;
    //excel图片
    private PictureData pictureData;
}
