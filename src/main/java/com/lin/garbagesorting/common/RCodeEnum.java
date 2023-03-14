package com.lin.garbagesorting.common;

public enum RCodeEnum {
    success(200, "操作成功"),
    sysError(500, "操作失败"),
    unLogin(401, "您还未登录"),
    unPermission(403, "您没有权限访问");

    private int code;
    private String desc;

    RCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
