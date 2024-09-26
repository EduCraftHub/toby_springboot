package tobyspring.helloboot;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// @Component : Component 스캔은 @Component이 붙은 클래스를 찾아서 빈으로 등록 해줌
@RestController // RestController는 Controller를 메타 어노테이션으로 갖고 있음 => 차이는 RestController는 @ResponseBody(응답을 Body에 넣어 보냄)
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // GetMapping 등이 붙어 있으면 웹 요청을 처리할 수 있도록 만들어진 컨트롤러라고 판단 후 그 안의 요청 정보 추출해서 매핑 테이블을 만들어둠
    @GetMapping("/hello")
    public String hello(String name) {
    // String을 return 하면 해당 이름을 가진 뷰를 찾아서 return
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
