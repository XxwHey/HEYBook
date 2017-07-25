package common.utils;

/**
 * Created by Administrator on 2017/5/11.
 */
public class CommonUtils {

    public static String getValue(Object value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }

}
