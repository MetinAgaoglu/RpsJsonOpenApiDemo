package com.yealink.api.header;

import java.util.Map;

/**
 * @Des HeaderRequiredParams;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other
 */
public class HeaderRequiredParams {

    private String accessKeyId;
    private String accessKeySecret;
    private String apiUrl;
    private String httpMethod;

    private String jsonBody;
    private Map<String, String> paramsMap;

    public HeaderRequiredParams(String accessKeyId, String accessKeySecret, String apiUrl, String httpMethod) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.apiUrl = apiUrl;
        this.httpMethod = httpMethod.toUpperCase();
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public HeaderRequiredParams setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public HeaderRequiredParams setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public HeaderRequiredParams setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public HeaderRequiredParams setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public HeaderRequiredParams setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
        return this;
    }

    public Map<String, String> getParamsMap() {
        return paramsMap;
    }

    public HeaderRequiredParams setParamsMap(Map<String, String> paramsMap) {
        this.paramsMap = paramsMap;
        return this;
    }
}
