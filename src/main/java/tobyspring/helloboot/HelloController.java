package tobyspring.helloboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // GetMapping 등이 붙어 있으면 웹 요청을 처리할 수 있도록 만들어진 컨트롤러라고 판단 후 그 안의 요청 정보 추출해서 매핑 테이블을 만들어둠
    @GetMapping
    @ResponseBody // 이 어노테이션을 이용하면 웹 응답의 바디에 넣어서 전달 할 수 있음 => @RestController가 붙으면 모든 메서드에 @RequestBody가 붙었다고 가정
    public String hello(String name) {
    // String을 return 하면 해당 이름을 가진 뷰를 찾아서 return
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
