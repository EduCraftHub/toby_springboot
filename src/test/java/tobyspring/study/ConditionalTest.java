package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {
    @Test
    public void conditional() {
        // true
        // Test 전용 어플리케이션 컨텍스트
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();

        // 이 컨텍스트 안에 이런 클래스 타입(MyBean.class)의 빈을 갖고 있는가 확인
        contextRunner.withUserConfiguration(Config1.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(Config1.class);
                });

        // false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                   assertThat(context).doesNotHaveBean(MyBean.class);
                   assertThat(context).doesNotHaveBean(Config2.class);
                });
    }

    @Configuration
    @Conditional(TrueCondition.class)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @Conditional(FalseCondition.class)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {}

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }
}
