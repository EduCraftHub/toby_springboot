package tobyspring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 원래 자바 애노테이션의 디폴트 값은 클래스로, 애노테이션의 정보가 컴파일된 클래스까지는 살아 있지만 애노테이션이 달린 클래스를 메모리로 로딩할 때는 그 정보가 사라진다는 뜻
// 런타임까지 애노테이션 정보가 유지되도록 Retention을 꼭 RUNTIME으로 지정해줘야 함
@Retention(RetentionPolicy.RUNTIME)
// TYPE은 클래스, 인터페이스, 이넘에게 부여할 수 있는 애너테이션
@Target(ElementType.TYPE)
// @Bean 오브젝트(팩토리 메서드를 가진) 클래스라는 걸 인지하기 위해서 붙여주는 어노테이션
@Configuration
// @ComponentScan이 붙어 있으면 이 패키지를 시작으로 하위 패키지를 확인해서 @Component 붙은 클래스를 빈으로 등록
@ComponentScan
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}
