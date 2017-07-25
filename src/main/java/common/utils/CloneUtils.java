package common.utils;

import java.io.*;

/**
 * Created by Administrator on 2017/2/27.
 * 克隆工具类
 */
public class CloneUtils {

    /**
     * 通过序列化实现深度克隆,所以要求克隆的类必须实现Serializable接口
     *
     * @param obj
     *            指定要克隆的对象
     * @return 返回被克隆的对象
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepClone(T obj) {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        ByteArrayInputStream byteIn = null;
        ByteArrayOutputStream byteOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            in = new ObjectInputStream(byteIn);
            return (T) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
                if (null != byteOut) {
                    byteOut.close();
                }
                if (null != in) {
                    in.close();
                }
                if (null != byteIn) {
                    byteIn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}