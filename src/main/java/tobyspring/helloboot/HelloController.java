package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        // Controller의 중요한 역할 중 하나는 유저의 요청 사항을 검증하는 것 : 파라미터가 이상하거나 Null이면 Exception을 발생시키는 등
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
