package io.github.netoht.uati.collector.domain;

/**
 * Created by neto on 23/01/17.
 */
public class ResponseMessage {

    private int code;
    private String message;

    public ResponseMessage() {
    }

    public int getCode() {
        return code;
    }

    public ResponseMessage setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
