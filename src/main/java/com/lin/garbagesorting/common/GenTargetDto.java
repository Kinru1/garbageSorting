package com.lin.garbagesorting.common;

public class GenTargetDto {
    private String columnName;      //列名称
    private String columnType;      //列类型
    private String columnComment;   //列描述
    private String columnDefault;   //列默认值
    private boolean columnNull;      //是否允许为null

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public boolean getColumnNull() {
        return columnNull;
    }

    public void setColumnNull(boolean columnNull) {
        this.columnNull = columnNull;
    }
}
