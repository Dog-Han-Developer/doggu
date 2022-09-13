package com.doghandeveloper.doggu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Base Time Entity에 별도로 auditing을 넣어줘야 할 것 같다.
// @EntityListeners(AuditingEntityListener.class)
// 아래는 프로젝트 전체를 감시하기 때문에, 모든 테스트에 불필요하게 감시를 넣어줘야 한다.
// @EnableJpaAuditing
@SpringBootApplication
public class DogguApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogguApplication.class, args);
	}

}
