package com.yealink.api;

import com.yealink.api.request.BodyParamRequest;
import com.yealink.api.request.QueryParamRequest;
import jodd.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Des OpenApiDemo;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other
 */
public class OpenApiDemo {

    //replace with valid service url
    private static final String SERVICE_URL = "https://dm.yealink.com";

    public static void main(String[] args) {

        postWithObject();
        //postWithJsonStr();

        getWithParamsMap();
        //getWithUrlParamsStr();
    }

    private static void postWithObject() {

        ServerProtocol.GetPagedList.Input input = new ServerProtocol.GetPagedList.Input()
                .setKey("Key");

        String apiUrl = "/api/open/v1/server/list";
        HttpResponse httpResponse = BodyParamRequest.post(SERVICE_URL, apiUrl, input);
        System.out.println(httpResponse.bodyText());
    }

    private static void postWithJsonStr() {

        String jsonStr = "{}";
        String apiUrl = "/api/open/v1/server/list";
        HttpResponse httpResponse = BodyParamRequest.post(SERVICE_URL, apiUrl, jsonStr);
        System.out.println(httpResponse.bodyText());
    }

    private static void getWithParamsMap() {

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("mac", "001565121212");

        String apiUrl = "/api/open/v1/device/checkMac";
        HttpResponse httpResponse = QueryParamRequest.get(SERVICE_URL, apiUrl, paramsMap);
        System.out.println(httpResponse.bodyText());
    }

    private static void getWithUrlParamsStr() {

        String apiUrl = "/api/open/v1/device/checkMac";
        String urlParamsStr = "?mac=001565121212";
        HttpResponse httpResponse = QueryParamRequest.get(SERVICE_URL, apiUrl, urlParamsStr);
        System.out.println(httpResponse.bodyText());
    }
}
