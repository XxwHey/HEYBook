package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/3/16.
 */
@Controller
public class PersonalCtrl {

    @RequestMapping(value = "personal/resume.do")
    public String resume(HttpServletRequest request, HttpServletResponse response) {
        return "personal/resume";
    }
}
