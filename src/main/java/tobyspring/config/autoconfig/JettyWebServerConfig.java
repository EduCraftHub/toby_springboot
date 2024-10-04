package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class JettyWebServerConfig {
    // Bean은 ID가 하나씩 지정됨(기본적으로 메서드 이름을 따라감)
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletContainer() {
        return new JettyServletWebServerFactory();
    }
}
