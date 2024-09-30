package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration // @Bean 오브젝트(팩토리 메서드를 가진) 클래스라는 걸 인지하기 위해서 붙여주는 어노테이션
@ComponentScan // @ComponentScan이 붙어 있으면 이 패키지를 시작으로 하위 패키지를 확인해서 @Component 붙은 클래스를 빈으로 등록
public class HellobootApplication {

    @Bean
    public ServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet(); // ApplicationContext를 어떻게 전달 받아야 할까?
    }

    public static void main(String[] args) {
        // MySpringApplication.run(HellobootApplication.class, args);
        SpringApplication.run(HellobootApplication.class, args);
    }
}