package com.lin.garbagesorting.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class R implements Serializable {

    private static final long serialVersionUID = -1700124294169946145L;

    private int code;
    private boolean success;
    private String msg;
    private Object data;
    private static final int CODE_SUCCESS = 200;
    private static final String CODE_SYS_ERROR = "500";
//    public R() {
//    }
//
//    public R(String msg) {
//        this.msg = msg;
//    }
//
//    public R(int code, Object data)
//    {
//        this.code = code;
//        this.data = data;
//    }
//
    public R(int code, String msg, boolean success, Object data)
    {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public R(int codeSuccess) {
    }

    public   static  R successCode(int codeSuccess) {
        return new R(codeSuccess);
    }


    public static R success()
    {
        return new R(RCodeEnum.success.getCode(), RCodeEnum.success.getDesc(), true, null);
    }
    public static R suc(Object data) {
        return new R(CODE_SUCCESS, true,"操作成功", data);
    }
    public static R suc(int code) {
        return new R(CODE_SUCCESS);
    }
    public static R success(String msg)
    {
        return new R(RCodeEnum.success.getCode(), msg, true, null);
    }

    public static R success(Object data)
    {
        return new R(RCodeEnum.success.getCode(), RCodeEnum.success.getDesc(), true, data);
    }

    public static R success(String msg, Object data)
    {
        return new R(RCodeEnum.success.getCode(), msg, true, data);
    }

    public static R success(int code, String msg, Object data)
    {
        return new R(code, msg, true, data);
    }

    public static R error()
    {
        return new R(RCodeEnum.sysError.getCode(), RCodeEnum.sysError.getDesc(), false, null);
    }

    public static R error(String msg)
    {
        return new R(RCodeEnum.sysError.getCode(), msg, false, null);
    }

    public static R error(int code, String msg)
    {
        return new R(code, msg, false, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



}
