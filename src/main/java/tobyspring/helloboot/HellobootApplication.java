package tobyspring.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HellobootApplication {

    public static void main(String[] args) {

        // TomcatServletWebServerFactory : 이 자체가 톰캣 서블릿 웹서버는 아니고, 톰캣 서블릿 웹서버 생성 과정, 설정 지원 클래스
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // getWebServer : 서블릿 컨테이너를 만드는 생성 함수
        WebServer webServer = serverFactory.getWebServer();
        webServer.start();

    }
}