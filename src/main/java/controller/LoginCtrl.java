package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.IUserService;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
public class LoginCtrl {

    @Autowired
    IUserService userService;

}
