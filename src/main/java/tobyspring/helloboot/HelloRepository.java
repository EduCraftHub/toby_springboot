package tobyspring.helloboot;

public interface HelloRepository {
    Hello findHello(String name);

    void increaseCount(String name);

    // default를 언제 쓰는지 궁금하다면, Comparator 클래스 참고
    // dafault 메서드 안에 구현을 넣을 수 있음, dafault 메서드의 장점은 인터페이스를 구현할 클래스에 구현할 메서드가 줄고 만들어진 인터페이스의 메서드 활용 가능
    default int countOf(String name) {
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}
