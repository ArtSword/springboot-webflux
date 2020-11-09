package com.artsword.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class HelloController {

    @Value("${server.port}")
    private String serverPort;

    // mvc
    @GetMapping("/1")
    public String hello1() {
        log.info("普通mvc方法 getString --- begin");
        String string = getString();
        log.info("普通mvc方法 getString --- end");
        return string;
    }

    // webflux
    @GetMapping("/2")
    public Mono<String> hello() {
        log.info("webflux方法 getString --- begin");
        Mono<String> stringMono = Mono.fromSupplier(this::getString);
        log.info("webflux方法 getString --- end");
        return stringMono;
    }

    // flux 返回 0-n个元素
    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 10).mapToObj(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                    }
                    return "flux data--" + i;
                }));
        return result;
    }

    private String getString() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
        return "some string";
    }
}
