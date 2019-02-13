package com.yealink.api.request;

import com.yealink.api.Constant;
import com.yealink.api.header.Header;
import com.yealink.api.header.HeaderBuilder;
import com.yealink.api.header.HeaderRequiredParams;
import com.yealink.api.header.QueryFormParamHeaderBuilder;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Des Demo for APIs whose parameters are Query Param;;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other Only GET
 */
public class QueryParamRequest {

    public static HttpResponse get(String serviceUrl, String apiUrl, Map<String, String> queryParamsMap) {

        HttpRequest httpRequest = createRequest(serviceUrl, apiUrl, queryParamsMap);
        Header header = createHeader(Constant.HTTP_GET, apiUrl, queryParamsMap);
        setHeader(httpRequest, header);
        return httpRequest.send();
    }

    public static HttpResponse get(String serviceUrl, String apiUrl, String urlParamsStr) {

        HttpRequest httpRequest = createRequest(serviceUrl, apiUrl, urlParamsStr);
        Header header = createHeader(Constant.HTTP_GET, apiUrl, convertToMap(urlParamsStr));
        setHeader(httpRequest, header);
        return httpRequest.send();
    }

    private static HttpRequest createRequest(String serviceUrl, String apiUrl, Map<String, String> queryParamsMap) {

        HttpRequest httpRequest = new HttpRequest()
                .method(Constant.HTTP_GET)
                .set(serviceUrl + apiUrl);
        queryParamsMap.forEach((key, value) -> httpRequest.query(key, value));
        return httpRequest;
    }

    private static HttpRequest createRequest(String serviceUrl, String apiUrl, String urlParamsStr) {

        return new HttpRequest()
                .method(Constant.HTTP_GET)
                .set(serviceUrl + apiUrl + urlParamsStr);
    }

    private static Header createHeader(String httpMethod, String apiUrl, Map<String, String> paramsMap) {

        HeaderRequiredParams params = new HeaderRequiredParams(Constant.ACCESSKEY_ID, Constant.ACCESSKEY_SECRET, apiUrl, httpMethod)
                .setParamsMap(paramsMap);

        HeaderBuilder headerBuilder = new QueryFormParamHeaderBuilder(params);
        return headerBuilder.buildHeader();
    }

    private static void setHeader(HttpRequest httpRequest, Header header) {

        httpRequest.header(Constant.X_CA_KEY, header.getX_ca_key())
                .header(Constant.X_CA_TIMESTAMP, header.getX_ca_timestamp())
                .header(Constant.X_CA_NONCE, header.getX_ca_nonce())
                .header(Constant.X_CA_SIGNATURE, header.getX_ca_signature());
    }

    private static Map<String, String> convertToMap(String urlParamsStr) {

        Map<String, String> paramsMap = new HashMap<>();
        try {
            String[] params = urlParamsStr.substring(1).split(Constant.REGEX);
            for (int i = 0; i < params.length; i = i + 2) {
                paramsMap.put(params[i], params[i + 1]);
            }
            return paramsMap;
        } catch (Exception e) {
            e.printStackTrace();
            return paramsMap;
        }
    }
}
