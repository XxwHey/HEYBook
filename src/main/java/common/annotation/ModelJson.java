package common.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/2/27.
 * 针对SpringMVC的参数解析，表示参数传入的是JSON，需要转换成具体的对象(VO、POJO)。
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModelJson {
}
