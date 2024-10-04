package tobyspring.config;

import org.springframework.context.annotation.Import;
import tobyspring.config.autoconfig.DispatcherServletConfig;
import tobyspring.config.autoconfig.TomcatWebServerConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// @Import 안에 ImportSelector 인터페이스를 구현한 클래스를 넣으면 해당 클래스에 있는 메서드를 실행해서 String[] 의 값을 동적으로 Import
@Import(MyAutoConfigImportSelector.class)
public @interface EnableMyAutoConfiguration {
}
