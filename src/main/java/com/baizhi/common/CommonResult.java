package com.baizhi.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    private String status;
    private String message;
    private Object data;

    public CommonResult success(String message,Object data){
        CommonResult commonResult = new CommonResult();

        commonResult.setStatus("100");
        commonResult.setMessage(message);
        commonResult.setData(data);

        return commonResult;
    }

    public CommonResult success(Object data){
        CommonResult commonResult = new CommonResult();

        commonResult.setStatus("100");
        commonResult.setMessage("请求成功");
        commonResult.setData(data);

        return commonResult;
    }
    public CommonResult failed(String message,Object data){
        CommonResult commonResult = new CommonResult();

        commonResult.setStatus("104");
        commonResult.setMessage(message);
        commonResult.setData(data);

        return commonResult;
    }

    public CommonResult failed(String message){
        CommonResult commonResult = new CommonResult();

        commonResult.setStatus("104");
        commonResult.setMessage(message);
        commonResult.setData(null);
        return commonResult;
    }


    public CommonResult failed(){
        CommonResult commonResult = new CommonResult();

        commonResult.setStatus("104");
        commonResult.setMessage("请求失败");
        commonResult.setData(null);
        return commonResult;
    }



}
