package com.yealink.api.header;

import com.yealink.api.Constant;
import jodd.util.StringUtil;

import java.util.Map;
import java.util.TreeMap;

import static jodd.util.StringPool.COLON;

/**
 * @Des QueryFormParamHeaderBuilder;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other
 */
public class QueryFormParamHeaderBuilder extends HeaderBuilder {

    public QueryFormParamHeaderBuilder(HeaderRequiredParams headerRequiredParams) {
        super(headerRequiredParams);
    }

    @Override
    public HeaderBuilder buildBasicHeader() {

        header.setX_ca_key(headerRequiredParams.getAccessKeyId())
                .setX_ca_timestamp(nowTimestampStr())
                .setX_ca_nonce(randomUUID());
        return this;
    }

    @Override
    public String combineSignatureItems() {

        String tempStrToSign = headerRequiredParams.getHttpMethod() + "\n" +
                Constant.X_CA_KEY + COLON + header.getX_ca_key() + "\n" +
                Constant.X_CA_NONCE + COLON + header.getX_ca_nonce() + "\n" +
                Constant.X_CA_TIMESTAMP + COLON + header.getX_ca_timestamp() + "\n" +
                headerRequiredParams.getApiUrl().substring(1);
        return tempStrToSign + createFormattedQFStr(headerRequiredParams.getParamsMap());
    }

    private String createFormattedQFStr(Map<String, String> queryParamsMap) {

        if (null == queryParamsMap || queryParamsMap.isEmpty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        Map<String, String> sortMap = new TreeMap<>();
        sortMap.putAll(queryParamsMap);

        sortMap.forEach((key, value) -> {

            if (1 < stringBuilder.length()) {
                stringBuilder.append("&");
            }
            stringBuilder.append(key);
            if (!StringUtil.isBlank(value)) {
                stringBuilder.append("=").append(value);
            }
        });

        return stringBuilder.toString();
    }
}
