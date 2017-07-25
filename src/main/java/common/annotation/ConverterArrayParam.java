package common.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/2/27.
 * 针对SpringMVC的参数解析，表示参数传入的是数组，用“,”隔开，然后转换成具体的数组。
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConverterArrayParam {
}