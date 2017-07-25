package common.constant;

/**
 * Created by Administrator on 2017/2/27.\
 * 内容类型常量
 */
public class ContentTypeConstants extends ConfigurableConstants {

    // 静态初始化读入config.properties中的设置
    static {
        load("classpath:contentType.properties");
    }

    protected ContentTypeConstants() {
        super();
    }

    public static void main(String[] args) {
        System.out.println(getProperties() + " " + getProperty("apk"));
    }
}