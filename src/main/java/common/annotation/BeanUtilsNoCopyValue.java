package common.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/2/27.
 * 针对BeanUtils的字段值复制，表示不参与复制的字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanUtilsNoCopyValue {
}
