package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration // @Bean 오브젝트(팩토리 메서드를 가진) 클래스라는 걸 인지하기 위해서 붙여주는 어노테이션
@ComponentScan // @ComponentScan이 붙어 있으면 이 패키지를 시작으로 하위 패키지를 확인해서 @Component 붙은 클래스를 빈으로 등록
public class HellobootApplication {

    @Bean
    public ServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet(); // ApplicationContext를 어떻게 전달 받아야 할까?
    }


    public static void main(String[] args) {

        // GenericWebApplicationContext는 자바 코드로 만든 Configuration 정보를 읽을 수 없음
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                // dispatcherServlet에 applicationContext 넘겨 주지 않아도 SpringContainer가 dispatcherServlet에 applicationContext 필요하구나 하고 주입
                // => 이걸 이해하려면 빈의 라이프 사이클을 알아야 함
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcher", dispatcherServlet // 클래스를 잘못 불러왔었음
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