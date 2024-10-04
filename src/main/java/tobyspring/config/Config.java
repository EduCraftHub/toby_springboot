package tobyspring.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class Config {
    // TomcatServletWebServerFactory와 DispatcherServlet는 유저 구성 정보(빈)에 포함되지 않아야 함
    // => 직접 빈으로 등록하지 않아도 자동으로 등록 되게 만들어야 한다는 뜻
    // 패키지를 옮겼기 때문에 @MySpringBootApplication의 컴포넌트 스캔 대상이 아님
    @Bean
    public ServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet(); // ApplicationContext를 어떻게 전달 받아야 할까?
    }
}
