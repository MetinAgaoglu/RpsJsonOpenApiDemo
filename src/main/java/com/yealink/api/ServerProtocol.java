package com.yealink.api;

/**
 * @Des ServerProtocol;
 * @Author Seakeer;
 * @Date 2018/12/8;
 * @Other
 */
public interface ServerProtocol {

    interface GetPagedList {

        class Input {

            private String key;

            private String status;

            private Integer limit;

            private Integer skip;

            private Boolean autoCount;

            public String getKey() {
                return key;
            }

            public Input setKey(String key) {
                this.key = key;
                return this;
            }

            public String getStatus() {
                return status;
            }

            public Input setStatus(String status) {
                this.status = status;
                return this;
            }

            public Integer getLimit() {
                return limit;
            }

            public Input setLimit(Integer limit) {
                this.limit = limit;
                return this;
            }

            public Integer getSkip() {
                return skip;
            }

            public Input setSkip(Integer skip) {
                this.skip = skip;
                return this;
            }

            public Boolean getAutoCount() {
                return autoCount;
            }

            public Input setAutoCount(Boolean autoCount) {
                this.autoCount = autoCount;
                return this;
            }
        }
    }
}
