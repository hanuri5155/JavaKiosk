package com.javagroup3.javakiosk.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) // 데이터베이스 관련 오류 뜨면 일단은 주석 풀고 실행 해보세요. Auto Configuration에서 DataSource 관련 설정 로드를 WAS 구동시 무시하는 어노테이션(@) 입니다.
@RestController
public class HomeController {
    // 메인 페이지 요청
    @GetMapping("/static-index") // 메인 페이지 주소
    public String index(){
        return "index.html";
    }
}
