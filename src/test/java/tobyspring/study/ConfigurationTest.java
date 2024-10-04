package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    void configuration() {
        // MyConfig의 bean1, bean2는 다른 주소의 common을 갖고 있는데, 스프링부트 컨텍스트의 구성 정보로 실행하게 되면 같은 객체를 갖게 됨
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if (this.common == null) this.common = super.common();
            return this.common;
        }
    }

    // proxyBeanMethods = true(디폴트): MyConfig 클래스가 직접 빈으로 등록되는 게 아니라 프록시 오브젝트를 앞에 하나 두고 그게 빈으로 등록 됨
    // proxyBeanMethods = false : 프록시를 만들지 않고 자바 코드 그대로 동작하게 하는 것, 사실 상 @Configuration 대신 @Component로 바꿔도 상관 없음
    // => @Bean이 붙은 메서드가 또 다른 Bean 메서드를 직접 호출하는 방식으로 사용하지 않고, 이 자체로 Bean을 생성하는데 충분한 작업을 수행한다고 하면 false로 두고 사용해도 됨(다른 오브젝트를 의존하지 않음)
    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }
}
