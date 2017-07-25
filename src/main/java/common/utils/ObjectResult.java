package common.utils;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/27.
 * 固定格式的数据返回对象
 */
public class ObjectResult {

    //响应状态
    public boolean success;

    //响应信息
    public String msg = null;

    //响应数据
    public Map<String, Object> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
