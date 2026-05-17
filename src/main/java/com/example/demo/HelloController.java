package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //스프링 부트에게 URL을 가로채서 처리하는 컨트롤러임을 지정
public class HelloController {

    @ResponseBody //글자 자체를 적어라 -> 없으면 Html로 이동
    @GetMapping("/hello")   // http://localhost:8080/hello라고 치면, 바로 밑에 있는 함수를 실행
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello"; //hello를 html로 생각함
    }
}