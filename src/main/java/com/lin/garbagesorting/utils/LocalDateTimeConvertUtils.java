package com.lin.garbagesorting.utils;

import java.time.LocalDateTime;

public class LocalDateTimeConvertUtils {
    public  static  String Convert(LocalDateTime localDateTime){
        String time = localDateTime.toString();
        String convertTime = "";
        for(int i = 0 ;i < time.length();i++) {
            if((time.substring(i,i+1).equals("T"))){
                return convertTime;

            }else {
                convertTime= convertTime+time.substring(i,i+1);
            }

        }
        return convertTime;
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
       String time =  Convert(localDateTime);
        System.out.println(time);
        System.out.println(localDateTime);
    }
}
