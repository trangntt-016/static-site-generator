package com.os.jssg.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.os.jssg.JssgApplication;

import java.io.*;

public class JSonUtils {
  public JssgApplication parseJSon(String config) throws FileNotFoundException, UnsupportedEncodingException {
    Gson gson = new Gson();
    JssgApplication args =
        gson.fromJson(new JsonReader(new InputStreamReader(
                new FileInputStream(config), "UTF-8")), JssgApplication.class);

    return args;
  }
}
