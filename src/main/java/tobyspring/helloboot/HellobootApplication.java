package tobyspring.helloboot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.EnableMyAutoConfiguration;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication {

    /*
    ApplicationRunner를 구현한 Bean을 등록하면, 스프링부트 초기화 작업 완료 후에 해당 인터페이스에 정의된 run 메서드를 통해 실행
    application.properties 보다 우선순위인 것은 OS의 환경변수이고, 그것보다 우선순위가 높은 것은 System property(자바 명령어를 실행할 때, D 옵션을 주고 Property 이름과 value를 주는 방법)
    @Bean
    ApplicationRunner applicationRunner(Environment env) {
        // 익명 클래스로 new ApplicationRunner 구현한 메서드(람다)
        return args -> {
            String name = env.getProperty("my.name");
            System.out.println("my.name: " + name);
        };
    }
     */

    public static void main(String[] args) {
        // MySpringApplication.run(HellobootApplication.class, args);
        SpringApplication.run(HellobootApplication.class, args);
    }
}