package com.example.SpringTypeConverter.controller;

import com.example.SpringTypeConverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    // Servlet은 항상 HttpServletRequest로 받아서 본인이 원하는 자료형으로 형변환 해야함.
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data"); // HTTP 요청 파라미터는 모두 문자로 처리된다.
        log.info("data = {}", data);
        Integer intValue = Integer.valueOf(data);
        System.out.println("intValue = " + intValue);

        return "ok";
    }

    // 스프링은 파라미터에 원하는 자료형으로 선언만 해두면 중간에서 알아서 타입을 변환해줌
    // @RequestParam, @ModelAttribute, @PathVariable에 스프링의 타입 변환이 적용됨(내부에서 ConversionService를 사용해서)
    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        System.out.println("data = " + data);

        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort Ip = " + ipPort.getIp());
        System.out.println("ipPort port = " + ipPort.getPort());

        return "ok";
    }

}
