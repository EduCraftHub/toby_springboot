package tobyspring.config.autoconfig;

import org.springframework.stereotype.Component;
import tobyspring.config.MyConfigurationProperties;

@MyConfigurationProperties(prefix = "server")
// BeanPostProcessor : 스프링에는 스프링 컨테이너의 기능을 확장할 수 있는 포인트가 있는데, 이건 Bean 오브젝트를 만든 다음 그걸 가공할 수 있는 기회를 주는 것
// => ServerProperties 클래스의 오브젝트가 만들어지면 property를 바인딩 하는 작업을 할 예정
public class ServerProperties {

    private String  contextPath;

    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
