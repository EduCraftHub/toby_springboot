package tobyspring.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// proxyBeanMethods : @Configuration을 이용해 애플리케이션 안에 우리가 원하는 빈을 등록하는 경우가 있음
@Configuration(proxyBeanMethods = false)
public @interface MyAutoConfiguration {
}
