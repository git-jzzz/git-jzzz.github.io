package cn.sb.audioInterface.util;

import lombok.Data;

@Data
public class Result {
    private String code;
    private String msg;
    private Object data;

    public  static Result ok(String msg){
        Result result = new Result();
        result.setCode("200");
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result ok(Object data) {
        Result result = new Result();
        result.setCode("200");
        result.setMsg("success");
        result.setData(data);
        return result;
    }


    public static Result error(String msg) {
        Result result = new Result();
        result.setCode("500");
        result.setMsg(msg);
        result.setData(null);
        return result;
    }



}
