package tobyspring.helloboot;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {

        // 스프링 컨테이너를 만들어서 HelloController를 집어 넣고, HelloController를 직접 생성해서 사용하는 대신, 스프링 컨테이너한테 요청해서 가져와 사용하는 방식 적용
        // ApplicationContext = 스프링 컨테이너가 되는 것
        GenericApplicationContext applicationContext = new GenericApplicationContext(); // => ApplicationContext 중 코드에 의해 쉽게 만들 수 있는 클래스
        // 서블릿처럼 오브젝트를 생성해서 넣어주는 것도 가능하지만, 일반적으로 어떤 클래스를 이용해서 빈을 생성할 건지 메타 정보를 넣어주는 식으로 구성
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh(); // 갖고 있는 구성 정보를 이용해서 컨테이너를 초기화하는 메서드

        // TomcatServletWebServerFactory : 이 자체가 톰캣 서블릿 웹서버는 아니고, 톰캣 서블릿 웹서버 생성 과정, 설정 지원 클래스
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // ServletContextInitializer : 서블릿을 등록하는 데 필요한 작업을 수행하는 Object 만들 때 사용
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통 기능은 앞에서 처리한다고 가정
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name()) ) {
                        String name = req.getParameter("name"); // 바인딩 : 처리하는 오브젝트에게 평범한 데이터 타입으로 변환해서 넘겨주는 것

                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String ret = helloController.hello(name); // 매핑 : /hello라는 경로로 들어오면 해당 서비스로 연결해줌

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start();

    }
}