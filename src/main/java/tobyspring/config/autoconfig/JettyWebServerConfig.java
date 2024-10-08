package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@Conditional(JettyWebServerConfig.JettyCondition.class)
public class JettyWebServerConfig {
    // Bean은 ID가 하나씩 지정됨(기본적으로 메서드 이름을 따라감)
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletContainer() {
        return new JettyServletWebServerFactory();
    }

    static class JettyCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("org.eclipse.jetty.server.Server",
                    context.getClassLoader());
        }
    }
}
