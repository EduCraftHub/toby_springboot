package tobyspring.helloboot;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Component : Component 스캔은 @Component이 붙은 클래스를 찾아서 빈으로 등록 해줌
@Service
public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
