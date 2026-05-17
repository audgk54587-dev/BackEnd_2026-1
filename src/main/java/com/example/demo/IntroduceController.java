package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


@Controller
public class IntroduceController {
    @GetMapping("/introduce/html")  // http://localhost:8080/introduce/html -> responseBody가 없으므로 introduce.html로 이동
    public String introduceHtml() {
        return "introduce"; //introduce.html 파일 열기
    }

    @ResponseBody   // return에 있는 글자 자체를 적어라
    @GetMapping("/introduce/string")     // http://localhost:8080/introduce/string -> 아래 함수 진행
    public String introduceString(String name) {
        name = "김수환무거북이";
        return "안녕하세요 제 이름은 " + name + "입니다!";
    }

    @ResponseBody
    @GetMapping("/introduce/json")      // http://localhost:8080/introduce/json -> 아래 함수 진행
    public Map<String, Object> introduceJson() {
        //Map<String, Object>: 쌍 반환
        Map<String, Object> data = new HashMap<>();
        //HashMap<>: Map(추상 클래스)으로 만든 실제 클래스
        data.put("age", 26);    //.put(): Map 안에 데이터를 넣는 명령어
        data.put("name", "허준기");
        return data;
    }

    @ResponseBody
    @GetMapping("/json")
    public Map<String, Object> introduceArticle() {
        Map<String, Object> data = new HashMap<>();
        data.put("age", 26);
        data.put("name", "허준기");
        return data;
    }

    @PostMapping("/article")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Article createArticle(@RequestBody Article article) {
        return article;
    }

    @PutMapping("/article/{id}")
    @ResponseBody
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article){
        return article;
    }

    @DeleteMapping("/article/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable Long id) { }

    @GetMapping("/article/{id}")
    @ResponseBody
    public Article getArticle(@PathVariable Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "404");
    }

}
