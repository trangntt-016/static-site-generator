package com.os.jssg.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.os.jssg.JssgApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSonUtils {
    public JssgApplication parseJSon(String config) throws FileNotFoundException {
        Gson gson = new Gson();
        JssgApplication args = gson.fromJson(new JsonReader(new FileReader(config)), JssgApplication.class);

        return args;
    }
}
