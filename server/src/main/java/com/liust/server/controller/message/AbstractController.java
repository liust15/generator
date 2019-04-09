package com.liust.server.controller.message;

/**
 * @program: generator
 * @description:
 * @author: liust
 * @create: 2019-04-05 09:22
 **/


import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractController {

    public interface Supplier<T> {
        T get() throws Exception;
    }

    public <T> MessageBean<T> process(T t) {
        try {
            return new MessageBean<>(t);
        } catch (RuntimeException e) {
            return new MessageBean<>(e);
        } catch (Throwable e) {
            log.error("system error", e);
            return new MessageBean<>(e);
        }
    }


}
