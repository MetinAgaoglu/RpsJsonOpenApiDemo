package com.yealink.api.header;

/**
 * @Des Header;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other
 */
public class Header {

    private String x_ca_key;
    private String x_ca_timestamp;
    private String x_ca_nonce;
    private String x_ca_signature;
    private String content_md5;

    public String getX_ca_key() {
        return x_ca_key;
    }

    public Header setX_ca_key(String x_ca_key) {
        this.x_ca_key = x_ca_key;
        return this;
    }

    public String getX_ca_timestamp() {
        return x_ca_timestamp;
    }

    public Header setX_ca_timestamp(String x_ca_timestamp) {
        this.x_ca_timestamp = x_ca_timestamp;
        return this;
    }

    public String getX_ca_nonce() {
        return x_ca_nonce;
    }

    public Header setX_ca_nonce(String x_ca_nonce) {
        this.x_ca_nonce = x_ca_nonce;
        return this;
    }

    public String getX_ca_signature() {
        return x_ca_signature;
    }

    public Header setX_ca_signature(String x_ca_signature) {
        this.x_ca_signature = x_ca_signature;
        return this;
    }

    public String getContent_md5() {
        return content_md5;
    }

    public Header setContent_md5(String content_md5) {
        this.content_md5 = content_md5;
        return this;
    }
}
