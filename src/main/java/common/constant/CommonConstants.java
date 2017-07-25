package common.constant;

/**
 * Created by Administrator on 2017/2/27.
 * 通用常量
 */
public class CommonConstants extends ConfigurableConstants {

    protected CommonConstants() {
        super();
    }

    /** BaseDAOImpl 实体存放的包名 */
    public static final String MODEL_PAGECKAGE_NAME = getProperty("MODEL_PAGECKAGE_NAME", "com.zhijiaoqiao.datacenter.model");

    /** cookie中的JSESSIONID名称 */
    public static final String JSESSION_COOKIE = "JSESSIONID";

    /** url中的jsessionid名称 */
    public static final String JSESSION_URL = "jsessionid";

    /** HTTP POST请求 */
    public static final String POST = "POST";

    /** HTTP GET请求 */
    public static final String GET = "GET";

    /** 部署路径属性名称 */
    public static final String CONTEXT_PATH = "base";

    /** tomcat session id 共享属性名称 */
    public static final String JSESSION_ID = "jsessionId";

    public static final String USERTYPEID = "userTypeId";

    public static final String USERTYPE = "userType";

    /** session id 属性名称 */
    public static final String SESSION_ID = "sessionId";

    /** 登录模块用来保存user到session的标识 */
    public static final String LOGIN_USER = "login_user";

    /** 判断用户是否已经验证通过，即密码用户名都已经正确 */
    public static final String LOGIN_CHECK = "logining";

    /** 登录模块用来表示错误代码的标识 */
    public static final String LOGIN_ERR_MSG = "loginErrMsg";

    /** 默认时间格式 */
    public static final String COMMON_DATA_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** HttpInternalObjectManager维护的线程唯一request对象标记 */
    public static final String THREAD_GLOBAL_VARIABLE_REQUEST = "threadRequest";

    /** HttpInternalObjectManager维护的线程唯一response对象标记 */
    public static final String THREAD_GLOBAL_VARIABLE_RESPONSE = "threadResponse";

    /** 默认每页显示数量*/
    public static final Integer PAGE_SIZE = 10;

}
