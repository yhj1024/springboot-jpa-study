package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

//		Hello hello = new Hello();
//		hello.setData("테스트");
//		String data = hello.getData();
//		System.out.println(data);

//		디펜던시 분석
//		핵심라이브러리 : 스프링 mvc, orm, jpa, 하이버네이트
//		기타 : h2 db client, hikari cp, web(타임리프), 로깅 slf4j (인터페이스) log4j (구현체), 테스트
//      mockito (목 lib), assertj (테스트 코드짧게 도와주는 라이브러리), spring-test (스프링 통합 테스트 지원)
		SpringApplication.run(JpashopApplication.class, args);
	}

}
