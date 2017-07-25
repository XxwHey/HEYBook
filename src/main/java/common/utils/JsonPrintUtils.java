package common.utils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
/**
 * Created by Administrator on 2017/2/27.
 */
public class JsonPrintUtils {

    public static void printJsonp(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String jsonpCallback = request.getParameter("jsonpCallback");
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            JSONObject resultJSON = JSONObject.fromObject(map, jsonConfig); // 根据需要拼装json
            writer.print(jsonpCallback + "(" + resultJSON.toString(1, 1) + ")");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (writer != null) {
                JSONObject json = new JSONObject();
                json.put("result", false);
                json.put("msg", e.getMessage());
                writer.flush();
                writer.close();
            }
        }
    }


}