package com.yealink.api.request;

import com.yealink.api.Constant;
import com.yealink.api.header.BodyParamHeaderBuilder;
import com.yealink.api.header.Header;
import com.yealink.api.header.HeaderBuilder;
import com.yealink.api.header.HeaderRequiredParams;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonSerializer;
import jodd.net.MimeTypes;

import java.nio.charset.Charset;

/**
 * @Des Demo for APIs whose parameters are Body Param;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other Only POST
 */
public class BodyParamRequest {

    public static HttpResponse post(String serviceUrl, String apiUrl, Object input) {

        String jsonStr = new JsonSerializer().deep(true).serialize(input);
        return post(serviceUrl, apiUrl, jsonStr);
    }

    public static HttpResponse post(String serviceUrl, String apiUrl, String jsonStr) {

        HttpRequest httpRequest = createRequest(serviceUrl, apiUrl, jsonStr);
        Header header = createHeader(Constant.HTTP_POST, apiUrl, jsonStr);
        setHeader(httpRequest, header);
        return httpRequest.send();
    }

    private static HttpRequest createRequest(String serviceUrl, String apiUrl, String jsonStr) {

        HttpRequest httpRequest = new HttpRequest()
                .method(Constant.HTTP_POST)
                .set(serviceUrl + apiUrl);

        if (jsonStr != null) {
            httpRequest.body(jsonStr.getBytes(Charset.forName(Constant.ENCODING)), MimeTypes.MIME_APPLICATION_JSON);
        }
        return httpRequest;
    }

    private static Header createHeader(String httpMethod, String apiUrl, String jsonBody) {

        HeaderRequiredParams params = new HeaderRequiredParams(Constant.ACCESSKEY_ID, Constant.ACCESSKEY_SECRET, apiUrl, httpMethod)
                .setJsonBody(jsonBody);

        HeaderBuilder headerBuilder = new BodyParamHeaderBuilder(params);
        return headerBuilder.buildHeader();
    }

    private static void setHeader(HttpRequest httpRequest, Header header) {

        httpRequest.header(Constant.X_CA_KEY, header.getX_ca_key())
                .header(Constant.X_CA_TIMESTAMP, header.getX_ca_timestamp())
                .header(Constant.X_CA_NONCE, header.getX_ca_nonce())
                .header(Constant.X_CA_SIGNATURE, header.getX_ca_signature());

        if (null != header.getContent_md5()) {
            httpRequest.header(Constant.CONTENT_MD5, header.getContent_md5());
        }
    }
}
