package com.yealink.api.header;

import com.yealink.api.Constant;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static jodd.util.StringPool.COLON;

/**
 * @Des BodyParamHeaderBuilder;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other
 */
public class BodyParamHeaderBuilder extends HeaderBuilder {

    public BodyParamHeaderBuilder(HeaderRequiredParams headerRequiredParams) {
        super(headerRequiredParams);
    }

    @Override
    public HeaderBuilder buildBasicHeader() {

        header.setX_ca_key(headerRequiredParams.getAccessKeyId())
                .setX_ca_timestamp(nowTimestampStr())
                .setX_ca_nonce(randomUUID());

        String jsonBody = headerRequiredParams.getJsonBody();
        if (null != jsonBody && jsonBody.length() > 0) {
            header.setContent_md5(createBodyMD5(headerRequiredParams.getJsonBody()));
        }
        return this;
    }

    @Override
    public String combineSignatureItems() {

        return headerRequiredParams.getHttpMethod() + "\n" +
                formatSignatureItemContentMD5() +
                Constant.X_CA_KEY + COLON + header.getX_ca_key() + "\n" +
                Constant.X_CA_NONCE + COLON + header.getX_ca_nonce() + "\n" +
                Constant.X_CA_TIMESTAMP + COLON + header.getX_ca_timestamp() + "\n" +
                headerRequiredParams.getApiUrl().substring(1);
    }

    private String createBodyMD5(String jsonBody) {

        try {
            return new String(Base64.encodeBase64(md5(jsonBody)), Constant.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private byte[] md5(String plainText) {

        byte[] secretBytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance(Constant.MD5);
            md.update(plainText.getBytes(Charset.forName(Constant.ENCODING)));
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return secretBytes;
    }

    private String formatSignatureItemContentMD5() {

        if (null != header.getContent_md5() && header.getContent_md5().length() > 0) {
            return Constant.CONTENT_MD5 + COLON + header.getContent_md5() + "\n";
        }
        return "";
    }
}