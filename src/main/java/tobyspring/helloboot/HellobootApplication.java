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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {

        // TomcatServletWebServerFactory : 이 자체가 톰캣 서블릿 웹서버는 아니고, 톰캣 서블릿 웹서버 생성 과정, 설정 지원 클래스
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // ServletContextInitializer : 서블릿을 등록하는 데 필요한 작업을 수행하는 Object 만들 때 사용
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            HelloController helloController = new HelloController();

            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통 기능은 앞에서 처리한다고 가정

                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name()) ) {
                        String name = req.getParameter("name"); // 바인딩 : 처리하는 오브젝트에게 평범한 데이터 타입으로 변환해서 넘겨주는 것

                        String ret = helloController.hello(name); // 매핑 : /hello라는 경로로 들어오면 해당 서비스로 연결해줌

                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);
                    } else if (req.getRequestURI().equals("/user")) {
                        // 처리 로직
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }

                }
            }).addMapping("/*");
        });
        webServer.start();

    }
}