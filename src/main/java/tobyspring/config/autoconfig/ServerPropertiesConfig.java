/* 미사용
package tobyspring.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServerPropertiesConfig {

    @Bean
    public ServerProperties serverProperties(Environment environment) {
        // 이전 코드
        ServerProperties properties = new ServerProperties();

        properties.setContextPath(environment.getProperty("contextPath"));
        properties.setPort(Integer.parseInt(environment.getProperty("port")));

        return properties;


        // ServerProperties로 바인딩 하겠다는 듯
        // 프로퍼티스의 프로퍼티 이름과 클래스의 프로퍼티 이름(Getter, Setter로 되어 있는)을 자동으로 바인딩
        return Binder.get(environment).bind("", ServerProperties.class).get();
    }
}
 */