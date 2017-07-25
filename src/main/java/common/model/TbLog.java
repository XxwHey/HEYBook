package common.model;

/**
 * Created by Administrator on 2017/2/27.
 */
public class TbLog extends BaseModel {

    private static final long serialVersionUID = 1695609152782529947L;

    // Fields
    private Integer id;

    private String title;

    private String loggerType;

    private Integer userId;

    private String userName;

    private String ipAddress;

    private String url;

    private String logTime;

    private String content;

    // Constructors
    /** default constructor */
    public TbLog() {
    }

    /** full constructor */
    public TbLog(String title, String loggerType, Integer userId, String userName, String ipAddress, String url, String logTime, String content) {
        this.title = title;
        this.loggerType = loggerType;
        this.userId = userId;
        this.userName = userName;
        this.ipAddress = ipAddress;
        this.url = url;
        this.logTime = logTime;
        this.content = content;
    }

    // Property accessors
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLoggerType() {
        return this.loggerType;
    }

    public void setLoggerType(String loggerType) {
        this.loggerType = loggerType;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogTime() {
        return this.logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}