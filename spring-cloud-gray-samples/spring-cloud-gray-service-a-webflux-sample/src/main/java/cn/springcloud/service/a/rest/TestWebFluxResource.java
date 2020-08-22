package cn.springcloud.service.a.rest;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestWebFluxResource {

    @Value("${test.sleepTime:50}")
    private long sleepTime = 50;

    @Autowired
    Environment env;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Mono<Map<String, String>> testGet(@RequestParam(value = "version", required = false) String version) {
        long start = System.currentTimeMillis();
//        try {
//            Thread.sleep(sleepTime);
//        } catch (InterruptedException e) {
//        }
        return Mono.just(ImmutableMap.of("test", "success.",
                "version", StringUtils.defaultIfEmpty(version, ""),
                "serverPort", env.getProperty("server.port"),
                "usedTime", String.valueOf(System.currentTimeMillis() - start)));
    }

}
