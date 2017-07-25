package common.ResourceView;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

/**
 * Created by Administrator on 2017/3/16.
 * HTML视图解析器
 */
public class HtmlResourceView extends InternalResourceView {

    @Override
    public boolean checkResource(Locale locale) {
        File file = new File(this.getServletContext().getRealPath("/") + getUrl());
        return file.exists();  //判断是否存在
    }
}
