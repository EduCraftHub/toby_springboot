package tobyspring.helloboot;

public interface HelloService {
    // DI란, 인터페이스를 중간에 잘 두고 코드 레벨의 의존 관계를 제거한 다음에 동적으로 스프링 컨테이너(어셈블러)를 사용해서 둘 사이의 연관 관계를 주입이라는 방법 사용
    String sayHello(String name);
}
