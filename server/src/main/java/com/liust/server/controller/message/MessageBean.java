package com.liust.server.controller.message;

/**
 * @program: generator
 * @description:
 * @author: liust
 * @create: 2019-04-05 09:20
 **/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageBean<T> {

    public static final int SUCC = 1;

    public static final int FAIL = 0;

    public static final int AUTH = -1;

    private int status = SUCC;

    private String msg;

    private T data;

    public MessageBean() {
    }

    public MessageBean(T data) {
        this.data = data;
    }

    public MessageBean(Throwable e) {
        this.status = FAIL;
        this.msg = e.getMessage();
    }

    public MessageBean(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
