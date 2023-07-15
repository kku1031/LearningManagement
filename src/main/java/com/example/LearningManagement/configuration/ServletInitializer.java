package com.example.LearningManagement.configuration;

import com.example.LearningManagement.LearningManagementApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	//프로토콜/도메인/user/sub01_3_1.asp
	//user라는 폴더밑에 sub01_3_1.asp란 파일이 물리적으로 존재.
	//=>논리적주소랑 물리적 주소(실제파일)랑 동일 : 스프링은 다름.
	//스프링은 실제 파일이랑 논리적 주소가 달라서 매핑해야함.
	//매핑 해주는것이 Controller

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LearningManagementApplication.class);
	}

}
