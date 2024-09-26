package tobyspring.helloboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Component를 메타 어노테이션으로 갖고 있는 어노테이션을 붙여도 @Component가 붙은 것과 동일한 효과
// 메타 어노테이션 : 어노테이션 위에 붙은 어노테이션
// 어노테이션으로 Component 스캔도 되고, 어떤 역할의 클래스인지 표기도 하고 싶은 등의 상황에서 사용 => Controller, Service도 이렇게 구현된 것
@Retention(RetentionPolicy.RUNTIME) // 언제까지 살아 있을 건지 지정
@Target(ElementType.TYPE) // 어노테이션을 적용할 대상
public @interface MyComponent {
}
