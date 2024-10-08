package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@Conditional(TomcatWebServerConfig.TomcatCondition.class)
public class TomcatWebServerConfig {
    @Bean("tomcatWebServerFactory")
    public ServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory();
    }

    static class TomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // 톱캣 클래스(라이브러리)가 현재 프로젝트에 포함 되어 있으면 true를 return
            // 클래스 로더를 이전에는 생성자나 setter 메서드로 주입을 받았는데, Condition을 구현한 메서드의 ConditionContext에서 애플리케이션 환경에 관한 오브젝트를 받아올 수 있음
            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat",
                    context.getClassLoader());
        }
    }
}
