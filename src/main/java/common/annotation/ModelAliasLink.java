package common.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/2/27.
 * 用于POJO，表示对应的Field需要连接(Hibernate)，支持多层的连接。
 * 默认情况是不用任何参数，表示当前Filed使用他自己的名字作为别名， 该操作只是支持一层连接；通过thisAlias和alias实现多层连接。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModelAliasLink {

    /** 指定当前Field的别名(用于多层连接) */
    String thisAlias() default "";

    /** 通过Alias指定多层连接情况 */
    Alias[] alias() default {};
}