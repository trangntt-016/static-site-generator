package com.os.jssg.utils;

public class TextUtils {
    public static String processText(String text){
        return text.replace("-"," ").replace("./","").replace("'","").replace("\"","");
    }
}
