package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration // @Bean 오브젝트(팩토리 메서드를 가진) 클래스라는 걸 인지하기 위해서 붙여주는 어노테이션
public class HellobootApplication {

    // 팩토리 메서드
    @Bean // 스프링 컨테이너가 빈으로 만들기 위한 정보라는 걸 인지하기 위해 붙여 줘야 함
    public HelloController helloController(HelloService helloService) {
        // HelloService 타입을 생성자에 주입 해야 함 -> 어떻게 가져올 것인가? => 여러 방법이 있음(현재 예시는 그 중 가장 쉬운 방법)
        // => 이 팩토리 메서드를 스프링 컨테이너가 호출할 예정 -> 스프링 컨테이너한테 '자바 코드로 HelloController를 만들 건데 그때 필요한 의존 오브젝트를 파라미터로 넘겨줘'라고 알려주기
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        // 빈을 받아서 사용하는 쪽에서 이 빈을 주입 받을 때, 어떤 타입을 기대하는가를 생각하고 return 타입을 정하는 것이 좋음 => 리턴 타입은 인터페이스로 설정하고 실제 return은 구현체
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        // GenericWebApplicationContext는 자바 코드로 만든 Configuration 정보를 읽을 수 없음
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcher", new DispatcherServlet() // 현재 버전 기준으로 this는 안 들어가게 됨
                    ).addMapping("/*");
                });

                webServer.start();
            }
        };

        // 자바 코드로 만든 구성 정보를 가지고 있는 클래스를 한 번 등록 해야 함 => 코드로 된 구성 정보가 있으니 여기서부터 출발하라고 알려주는 것
        applicationContext.register(HellobootApplication.class);
        applicationContext.refresh(); // 갖고 있는 구성 정보를 이용해서 컨테이너를 초기화하는 메서드
    }
}