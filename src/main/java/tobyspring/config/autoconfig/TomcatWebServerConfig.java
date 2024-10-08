package tobyspring.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

    // 프로퍼티에서 치환자로 읽어오는 건 기본 기능이 아니라서 확장 필요 => PropertySourcesPlaceholderConfigurer Bean 등록 필요
    @Value("${contextPath}")
    String  contextPath;

    @Bean("tomcatWebServerFactory")
    // DefferedImportSelector를 사용해서 자동 구성 정보를 구현하면 유저 구성 정보를 먼저 로딩하고, 그 다음에 자동 구성 정보를 하나씩 로딩 하기 때문
    // ConditionalOnMissingBean : 유저 구성 정보에 ServletWebServerFactory Bean이 있다면 그걸 사용하고, 없다면 자동 구성 정보인 이 클래스의 Bean을 사용
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath(this.contextPath);
        return factory;
    }
}
