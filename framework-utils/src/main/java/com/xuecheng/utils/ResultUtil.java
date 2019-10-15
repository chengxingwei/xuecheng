package com.xuecheng.utils;

import com.xuecheng.enums.ResultTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ResultUtil implements Serializable {
    /**
     * 0成功 1失败
     */
    private Integer code;

    /**
     *消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    public ResultUtil(Object data){
        this.code = ResultTypeEnum.SUCCESS.toValue();
        this.data = data;
    }

    public ResultUtil(){
        this.code = ResultTypeEnum.SUCCESS.toValue();
    }

}
