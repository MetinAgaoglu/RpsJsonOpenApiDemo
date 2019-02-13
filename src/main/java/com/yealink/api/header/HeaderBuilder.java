package com.yealink.api.header;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

import static com.yealink.api.Constant.ALGORITHM_NAME;
import static com.yealink.api.Constant.ENCODING;

/**
 * @Des HeaderBuilder;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other
 */
public abstract class HeaderBuilder {

    protected final HeaderRequiredParams headerRequiredParams;
    protected Header header = new Header();

    HeaderBuilder(HeaderRequiredParams headerRequiredParams) {
        this.headerRequiredParams = headerRequiredParams;
    }

    public Header buildHeader() {
        this.buildBasicHeader().buildSignatureHeader();
        return header;
    }

    public abstract HeaderBuilder buildBasicHeader();

    private HeaderBuilder buildSignatureHeader() {

        String stringToSign = combineSignatureItems();
        header.setX_ca_signature(sign(headerRequiredParams.getAccessKeySecret(), stringToSign));
        return this;
    }

    public abstract String combineSignatureItems();

    public String nowTimestampStr() {
        return System.currentTimeMillis() + "";
    }

    public String randomUUID() {

        return UUID.randomUUID().toString();
    }

    private String sign(String accessKeySecret, String stringToSign) {

        try {
            Mac macAlg = Mac.getInstance(ALGORITHM_NAME);
            macAlg.init(new SecretKeySpec(accessKeySecret.getBytes(ENCODING), ALGORITHM_NAME));
            byte[] signData = macAlg.doFinal(stringToSign.getBytes(ENCODING));
            return new String(Base64.encodeBase64(signData), ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
