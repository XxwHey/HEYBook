package common.utils;

import common.constant.CommonConstants;
import common.constant.ContentTypeConstants;
import common.utils.Bean.UploadFile;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by Administrator on 2017/2/27.
 * Http对象工具类,包括request、response、cookies等操作功能
 */
public class HttpUtils {

    /** 支持的内容类型 */
    public static final HashMap<String, String> contentTypeMap = new HashMap<String, String>();
    /* 初始化 contentTypeMap 加载默认是的支持类型 */
    static {
        contentTypeMap.put("html", "text/html");
        contentTypeMap.put("xml", "text/xml");
        contentTypeMap.put("hta", "application/hta");
        contentTypeMap.put("doc", "application/msword");
        contentTypeMap.put("wps", "application/vnd.ms-works");
        contentTypeMap.put("xls", "application/vnd.ms-excel");
        contentTypeMap.put("htm", "text/html");
        contentTypeMap.put("gif", "image/gif");
        contentTypeMap.put("jpeg", "image/jpeg");
        contentTypeMap.put("jpg", "image/jpeg");
        contentTypeMap.put("mht", "message/rfc822");
        contentTypeMap.put("mhtml", "message/rfc822");
        contentTypeMap.put("pdf", "application/pdf");
        contentTypeMap.put("ppt", "application/vnd.ms-powerpoint");
        contentTypeMap.put("pps", "application/vnd.ms-powerpoint");
        contentTypeMap.put("tif", "image/tiff");
        contentTypeMap.put("tiff", "image/tiff");
        contentTypeMap.put("txt", "text/plain");
        contentTypeMap.put("zip", "application/zip");
        contentTypeMap.put("rar", "application/rar");
        contentTypeMap.put("class", "application/x-java-vm");
        contentTypeMap.put("jar", "application/x-java-archive");
        contentTypeMap.put("ser", "application/x-java-serialized");
        contentTypeMap.put("exe", "application/octet-stream");
        contentTypeMap.put("hdml", "text/x-hdml");
        contentTypeMap.put("bmp", "image/bmp");
        contentTypeMap.put("ico", "image/x-icon");
        contentTypeMap.put("wml", "text/vnd.wap.wml");
        contentTypeMap.put("wmls", "text/vnd.wap.wmlscript");
        contentTypeMap.put("wmlc", "application/vnd.wap.wmlc");
        contentTypeMap.put("wmlsc", "application/vnd.wap.wmlscript");
        contentTypeMap.put("wbmp", "image/vnd.wap.wbmp");
        contentTypeMap.put("csv", "application/msexcel");
        contentTypeMap.put("vsd", "application/vnd.visio");
        contentTypeMap.put("p7b", "application/x-pkcs7-certificates");
        contentTypeMap.put("cer", "application/x-x509-ca-cert");
        contentTypeMap.put("der", "application/x-x509-ca-cert");
    }

    /**
     * 触发文件下载，浏览器下载文件
     *
     * @param response
     *            请求响应对象
     * @param filePath
     *            要下载文件的路径(绝对路径)
     * @param showName
     *            下载时候显示的文件,要带上扩展名
     * @return 是否成功触发下载
     */
    public static boolean download(HttpServletResponse response, String filePath, String showName) {
        String contentType = ""; // response Content
        String extendName = "*";
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            if (StringUtils.isNotBlank(filePath)) {
                File file = new File(filePath);
                String fileName = file.getName();
                int index = fileName.lastIndexOf(".");// 获取扩展名
                if (index > 0) {
                    extendName = fileName.substring(index + 1);
                    extendName = extendName.toLowerCase().trim();
                }
                bis = new BufferedInputStream(new FileInputStream(file));
                if (null != bis) {
                    int bytesA = bis.available();
                    byte[] data = new byte[1024];
                    // MIME类型
                    contentType = ContentTypeConstants.getProperty(extendName);
                    if (null == contentType) {
                        contentType = contentTypeMap.get(extendName);
                        contentType = (null == contentType ? "" : contentType);// application/*.*
                    }
                    response.setContentType(contentType);
                    response.setContentLength(bytesA);
                    bos = new BufferedOutputStream(response.getOutputStream());
                    String titleName = new String((0 < showName.length() ? showName : ("download" + extendName)).getBytes("UTF-8"), "ISO8859-1");
                    response.setHeader("Content-Disposition", "attachment;filename=\"" + titleName + "\"");
                    int bytesRead = 0;
                    while (-1 != (bytesRead = bis.read(data))) {
                        bos.write(data, 0, bytesRead);
                    }
                    bos.flush();
                    bos.close();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Edity by bin.20140416 触发文件下载，浏览器下载文件
     *
     * @param response
     *            请求响应对象
     * @param bytes
     *            对应文件的二进制数据
     * @param showName
     *            下载时候显示的文件,要带上扩展名
     * @return 是否成功触发下载
     */
    public static boolean download(HttpServletResponse response, byte[] bytes, String showName) {
        String contentType = ""; // response Content
        String extendName = "*";
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            if (bytes != null) {
                int index = showName.lastIndexOf(".");// 获取扩展名
                if (index > 0) {
                    extendName = showName.substring(index + 1);
                    extendName = extendName.toLowerCase().trim();
                }
                bis = new BufferedInputStream(new ByteArrayInputStream(bytes));
                if (null != bis) {
                    int bytesA = bis.available();
                    byte[] data = new byte[1024];
                    // MIME类型
                    contentType = ContentTypeConstants.getProperty(extendName);
                    if (null == contentType) {
                        contentType = contentTypeMap.get(extendName);
                        contentType = (null == contentType ? "" : contentType);// application/*.*
                    }
                    response.setContentType(contentType);
                    response.setContentLength(bytesA);
                    bos = new BufferedOutputStream(response.getOutputStream());
                    String titleName = new String((0 < showName.length() ? showName : ("download" + extendName)).getBytes("UTF-8"), "ISO8859-1");
                    response.setHeader("Content-Disposition", "attachment;filename=\"" + titleName + "\"");
                    int bytesRead = 0;
                    while (-1 != (bytesRead = bis.read(data))) {
                        bos.write(data, 0, bytesRead);
                    }
                    bos.flush();
                    bos.close();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传文件处理
     *
     * @param request
     *            请求对象，必须为MultipartHttpServletRequest实例，否则上传失败
     * @param fileFieldName
     *            对应表单的File元素的名称
     * @param fileSavePath
     *            文件保存的路径 ，必须为绝对路径，不需要带上文件名
     * @return 返回UploadFile对象，对象包含基本上传文件信息
     */
    public static UploadFile upload(HttpServletRequest request, String fileFieldName, String fileSavePath) {
        UploadFile uploadFile = null;
        MultipartHttpServletRequest multipartRequest = null;
        if (request instanceof MultipartHttpServletRequest) {
            multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile(fileFieldName);
            if (file != null && fileSavePath != null) {
                String name = file.getOriginalFilename();
                int lPointPos = name.lastIndexOf(".");
                String extendName = (lPointPos > 0 ? name.substring(name.lastIndexOf(".")) : "");
                String fileName = UUID.randomUUID().toString().replace("-", "") + extendName;
                BufferedOutputStream bos = null;
                try {
                    // 写出文件
                    File realFile = new File(fileSavePath, fileName);
                    bos = new BufferedOutputStream(new FileOutputStream(realFile));
                    bos.write(file.getBytes());
                    bos.close();
                    String OriginalFilename = file.getOriginalFilename();
                    uploadFile = new UploadFile(OriginalFilename, fileName, realFile.length(), extendName, realFile.getAbsolutePath(), realFile);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != bos) {
                        try {
                            bos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return uploadFile;
    }

    /**
     * 获取对应参数的值，并使用URLDecoder以UTF-8格式转码。如果请求是以post方法提交的， 那么将通过HttpServletRequest::getParameter获取。
     *
     * @param request
     *            请求对象
     * @param name
     *            参数名称
     * @return 参数值(多个则获取最后一个)
     */
    public static String getQueryParam(HttpServletRequest request, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (request.getMethod().equalsIgnoreCase(CommonConstants.POST)) {
            return request.getParameter(name);
        }
        String queryStr = request.getQueryString();
        if (StringUtils.isBlank(queryStr)) {
            return null;
        }
        try {
            queryStr = URLDecoder.decode(queryStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] values = parseQueryString(queryStr).get(name);
        if (values != null && values.length > 0) {
            return values[values.length - 1];
        } else {
            return null;
        }
    }

    /**
     * 获取所有参数的键值队，并使用URLDecoder以UTF-8格式转码。如果请求是以post方法提交的， 那么将通过HttpServletRequest::getParameter获取
     *
     * @param request
     *            请求对象
     * @return 一个map,[参数名/参数值(多个时候为数组)]
     */
    public static Map<String, Object> getQueryParams(HttpServletRequest request) {
        Map<String, String[]> map;
        if (request.getMethod().equalsIgnoreCase(CommonConstants.POST)) {
            map = request.getParameterMap();
        } else {
            String queryStr = request.getQueryString();
            if (StringUtils.isBlank(queryStr)) {
                return new HashMap<String, Object>();
            }
            try {
                queryStr = URLDecoder.decode(queryStr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map = parseQueryString(queryStr);
        }
        Map<String, Object> params = new HashMap<String, Object>(map.size());
        int len;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            len = entry.getValue().length;
            if (len == 1) {
                params.put(entry.getKey(), entry.getValue()[0]);
            } else if (len > 1) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return params;
    }

    /**
     *
     * Parses a query string passed from the client to the server and builds a <code>HashTable</code> object with key-value pairs. The query string should be in the form of a string packaged by the GET or POST method, that is, it should have key-value pairs in the form
     * <i>key=value</i>, with each pair separated from the next by a &amp; character.
     *
     * <p>
     * A key can appear more than once in the query string with different values. However, the key appears only once in the hashtable, with its value being an array of strings containing the multiple values sent by the query string.
     *
     * <p>
     * The keys and values in the hashtable are stored in their decoded form, so any + characters are converted to spaces, and characters sent in hexadecimal notation (like <i>%xx</i>) are converted to ASCII characters.
     *
     * @param queryStr
     *            a string containing the query to be parsed
     *
     * @return a <code>HashTable</code> object built from the parsed key-value pairs
     *
     * @exception IllegalArgumentException
     *                if the query string is invalid
     *
     */
    public static Map<String, String[]> parseQueryString(String queryStr) {
        String valArray[] = null;
        if (queryStr == null) {
            throw new IllegalArgumentException();
        }
        Map<String, String[]> ht = new HashMap<String, String[]>();
        StringTokenizer st = new StringTokenizer(queryStr, "&");
        while (st.hasMoreTokens()) {
            String pair = (String) st.nextToken();
            int pos = pair.indexOf('=');
            if (pos == -1) {
                continue;
            }
            String key = pair.substring(0, pos);
            String val = pair.substring(pos + 1, pair.length());
            if (ht.containsKey(key)) {
                String oldVals[] = (String[]) ht.get(key);
                valArray = new String[oldVals.length + 1];
                for (int i = 0; i < oldVals.length; i++) {
                    valArray[i] = oldVals[i];
                }
                valArray[oldVals.length] = val;
            } else {
                valArray = new String[1];
                valArray[0] = val;
            }
            ht.put(key, valArray);
        }
        return ht;
    }

    /**
     * 获取指定前缀的参数的键值队
     *
     * @param request
     *            请求对象
     * @param prefix
     *            前缀
     * @return 指定的键值队[参数名/参数值(多个时候","隔开)]
     */
    public static Map<String, String> getRequestMap(HttpServletRequest request, String prefix) {
        return getRequestMap(request, prefix, false);
    }

    /**
     * 获取指定前缀的参数的键值队,并在键值队中保留前缀名
     *
     * @param request
     *            请求对象
     * @param prefix
     *            前缀
     * @return 指定的键值队[参数名/参数值(多个时候","隔开)]
     */
    public static Map<String, String> getRequestMapWithPrefix(HttpServletRequest request, String prefix) {
        return getRequestMap(request, prefix, true);
    }

    /**
     * 获取指定前缀的参数的键值队
     *
     * @param request
     *            请求对象
     * @param prefix
     *            前缀
     * @param nameWithPrefix
     *            键值队中是否保存前缀名
     * @return 指定的键值队[参数名/参数值(多个时候","隔开)]
     */
    protected static Map<String, String> getRequestMap(HttpServletRequest request, String prefix, boolean nameWithPrefix) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> names = request.getParameterNames();
        String name, key, value;
        while (names.hasMoreElements()) {
            name = names.nextElement();
            if (name.startsWith(prefix)) {
                key = nameWithPrefix ? name : name.substring(prefix.length());
                value = StringUtils.join(request.getParameterValues(name), ',');
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 将Map转换成key=value&key=value&key=value......的格式
     *
     * @param paramMap
     *            指定的参数Map
     * @return 转换后的queryString
     */
    public static String formatQueryString(Map<String, String[]> paramMap) {
        List<String> keyValList = new ArrayList<String>();
        if (MapUtils.isNotEmpty(paramMap)) {
            Set<String> keySet = paramMap.keySet();
            Iterator<String> keyIt = keySet.iterator();
            while (keyIt.hasNext()) {
                String key = keyIt.next();
                if (StringUtils.isNotBlank(key)) {
                    String[] values = paramMap.get(key);
                    for (int i = 0, len = values.length; i < len; i++) {
                        String value = values[i];
                        keyValList.add(key + "=" + value);
                    }
                }
            }
        }
        return StringUtils.join(keyValList.toArray(new String[0]), "&");
    }

    /**
     * 获取访问者IP
     *
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     *
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)， 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     *            请求对象
     * @return 获取访问IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    //获取MAC地址的方法
    public static String getMACAddress(InetAddress ia)throws Exception{
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

        //下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();

        for(int i=0;i<mac.length;i++){
            if(i!=0){
                sb.append("-");
            }
            //mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length()==1?0+s:s);
        }

        //把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }

    /**
     * 获得当的访问路径
     *
     * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString
     *
     * @param request
     *            请求对象
     * @return 访问路径
     */
    public static String getLocation(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        StringBuffer buff = request.getRequestURL();
        String uri = request.getRequestURI();
        String origUri = helper.getOriginatingRequestUri(request);
        buff.replace(buff.length() - uri.length(), buff.length(), origUri);
        String queryString = helper.getOriginatingQueryString(request);
        if (queryString != null) {
            buff.append("?").append(queryString);
        }
        return buff.toString();
    }

    /**
     * 获得请求的session id，但是HttpServletRequest#getRequestedSessionId()方法有一些问题。 当存在部署路径的时候，会获取到根路径下的jsessionid。
     *
     * @see HttpServletRequest#getRequestedSessionId()
     *
     * @param request
     *            请求对象
     * @return session Id
     */
    public static String getRequestedSessionId(HttpServletRequest request) {
        String sid = request.getRequestedSessionId();
        String ctx = request.getContextPath();
        // 如果session id是从url中获取，或者部署路径为空，那么是在正确的。
        if (request.isRequestedSessionIdFromURL() || StringUtils.isBlank(ctx)) {
            return sid;
        } else {
            // 手动从cookie获取
            Cookie cookie = getCookie(request, CommonConstants.JSESSION_COOKIE);
            if (cookie != null) {
                return cookie.getValue();
            } else {
                return null;
            }
        }
    }

    /**
     * 发送文本。使用UTF-8编码。
     *
     * @param response
     *            响应对象
     * @param text
     *            发送的字符串
     */
    public static void renderText(HttpServletResponse response, String text) {
        render(response, "text/plain;charset=UTF-8", text);
    }

    /**
     * 发送json。使用UTF-8编码。
     *
     * @param response
     *            响应对象
     * @param text
     *            发送的字符串
     */
    public static void renderJson(HttpServletResponse response, String text) {
        render(response, "application/json;charset=UTF-8", text);
    }

    /**
     * 发送xml。使用UTF-8编码。
     *
     * @param response
     *            响应对象
     * @param text
     *            发送的字符串
     */
    public static void renderXml(HttpServletResponse response, String text) {
        render(response, "text/xml;charset=UTF-8", text);
    }

    /**
     * 发送内容。使用UTF-8编码。
     *
     * @param response
     *            响应对象
     * @param contentType
     *            内容类型
     * @param text
     *            发送的字符串
     */
    public static void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            response.getWriter().write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得cookie
     *
     * @param request
     *            请求对象
     * @param name
     *            cookie名称
     * @return 如果存在则返回cookies, 否则为空。
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 根据部署路径，将cookie保存在根目录。
     *
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @param name
     *            cookies名称
     * @param value
     *            cookies值
     * @param expiry
     *            超时间时间
     * @param domain
     *            域名
     * @return 返回生成的cookies
     */
    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer expiry, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry);
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * 取消cookie
     *
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @param name
     *            cookies名称
     * @param domain
     *            域名
     */
    public static void cancleCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }

    /**
     * 复制http内置对象的Attribute(暂时只支持HttpSession/HttpServletRequest/HttpServletResponse类型内置对象的复制)
     *
     * @param srcHttpInternalObject
     *            源http内置对象
     * @param desHttpInternalObject
     *            目标http内置对象
     */
    public static void copyHttpInternalObjectAttribute(Object srcHttpInternalObject, Object desHttpInternalObject) {
        try {
            if (null != srcHttpInternalObject && null != desHttpInternalObject) {
                if ((srcHttpInternalObject instanceof HttpSession || srcHttpInternalObject instanceof HttpServletRequest || srcHttpInternalObject instanceof HttpServletResponse)
                        && (desHttpInternalObject instanceof HttpSession || desHttpInternalObject instanceof HttpServletRequest || desHttpInternalObject instanceof HttpServletResponse)) {
                    Enumeration<?> attributeNames = (Enumeration<?>) AfxBeanUtils.invokeMethod(srcHttpInternalObject, "getAttributeNames", new Object[0], new Class<?>[] {});
                    while (attributeNames.hasMoreElements()) {
                        Object name = attributeNames.nextElement();
                        Object val = AfxBeanUtils.invokeMethod(srcHttpInternalObject, "getAttribute", new Object[] { name }, new Class<?>[] { String.class });
                        AfxBeanUtils.invokeMethod(desHttpInternalObject, "setAttribute", new Object[] { name, val }, new Class<?>[] { String.class, Object.class });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        download(null, "c:/新建文本文档.txt", "test.txt");
    }
}
