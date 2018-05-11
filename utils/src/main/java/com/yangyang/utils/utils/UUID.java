package com.yangyang.utils.utils;

public class UUID {
    public static String getUUID() {
        java.util.UUID uuid= java.util.UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }

    public static  void  main(String[] args) {


        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(UUID.getUUID());
        }



    }
}
