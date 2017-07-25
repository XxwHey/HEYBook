package common.utils;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/27.
 */
public class ResponseUtils {
    public static void setWrite(HttpServletResponse response, String dataName, Object data) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("result", true);
            json.put(dataName, data);
            writer.print(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (writer != null) {
                returnErrorMsg(writer, e.toString());
            }
        }
    }

    public static void setWrite(HttpServletResponse response, Map<String, Object> dataMap) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            for (String key : dataMap.keySet()) {
                json.put(key, dataMap.get(key));
            }
            json.put("result", true);
            writer.print(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (writer != null) {
                returnErrorMsg(writer, e.toString());
            }
        }
    }

    public static void returnErrorMsg(PrintWriter writer, String errorMsg) {
        JSONObject json = new JSONObject();
        json.put("result", false);
        json.put("msg", errorMsg);
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 147; i++) {
            if (i!= 22 && i!=23 && i != 25 && i != 26 && i!=28 && i != 80 && i != 129 && i != 130) {
                System.out.println(
                        "INSERT INTO `tb_school_permission` (`school_id`, `modular_id`) VALUES ('5', '" + i + "');");
            }
        }
        for (int i = 153; i <= 156; i++) {
            System.out.println(
                    "INSERT INTO `tb_school_permission` (`school_id`, `modular_id`) VALUES ('5', '" + i + "');");
        }
        for (int i = 161; i <= 162; i++) {
            System.out.println(
                    "INSERT INTO `tb_school_permission` (`school_id`, `modular_id`) VALUES ('5', '" + i + "');");
        }
        for (int i = 167; i <= 171; i++) {
            System.out.println(
                    "INSERT INTO `tb_school_permission` (`school_id`, `modular_id`) VALUES ('5', '" + i + "');");
        }
        System.out.println(
                "INSERT INTO `tb_school_permission` (`school_id`, `modular_id`) VALUES ('5', '175');");
        System.out.println(
                "INSERT INTO `tb_school_permission` (`school_id`, `modular_id`) VALUES ('5', '176');");

    }
}