package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// 하나의 대상, 하나의 클래스 정도 수준으로 테스트를 빠르게 진행할 수 있도록 만든 것이 단위 테스트(컨테이너 없이 테스트 가능)
public class HelloControllerTest {

    @Test
    void helloController() {
        // 테스트 스텁 => DI의 일종으로 테스트에서 사용하는 수동 DI
        // helloService 안에서 문제가 일어나 Controller Test가 실패하는 상황을 예방하기 위해 HelloController를 다른 의존 오브젝트로부터 분리 시켜야 함
        // HelloController에 적용 되어 있는 Dependency Injection이 가능하도록 설계된 의존 관계 주입 방식을 활용하면 됨
        /* 이런 식으로 익명 클래스를 활용할 수 있음
        HelloController helloController = new HelloController(new HelloService() {
            @Override
            public String sayHello(String name) {
                return "";
            }
        })
         */

        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
