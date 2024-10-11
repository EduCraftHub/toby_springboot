package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

public class HelloApiTest {

    @Test
    void helloApi() {
        // 그냥 RestTemplate은 400대나 500대 응답 코드가 왔을 때 예외를 던지기 때문에 순수하게 응답과 상태 코드 등을 보려고 하면 TestRestTemplate이 나음
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Spring");

        // 반환 타입 유의해서 작성하기 : getStatusCode()는 enum HttpStatus
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // 타입을 res 선언할 때, String으로 지정했기 때문에 String을 비교한다고 생각하면 됨
        assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    void failsHelloApi() {

        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:9090/app/hello?name=", String.class);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
