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

import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {

        // TomcatServletWebServerFactory : 이 자체가 톰캣 서블릿 웹서버는 아니고, 톰캣 서블릿 웹서버 생성 과정, 설정 지원 클래스
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // ServletContextInitializer : 서블릿을 등록하는 데 필요한 작업을 수행하는 Object 만들 때 사용
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    resp.setStatus(200);
                    resp.setHeader("Content-Type", "text/plain");
                    resp.getWriter().println("Hello Servlet!");
                }
            }).addMapping("/hello");
        });
        webServer.start();

    }
}