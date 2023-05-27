package com.maple.plugin.data;

public class InputData {

    // 输入的文件名 例如：gaoGuanQi
    public String fileName;
    // 驼峰命名 例如: GaoGuanQi
    public String upName;
    //下划线命名 例如: gao_guan_qi
    public String lowName;

    @Override
    public String toString() {
        return "{" +
                "name='" + fileName + '\'' +
                ", upName='" + upName + '\'' +
                ", lowName='" + lowName + '\'' +
                '}';
    }
}
