package com.study.jade.controller;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@CrossOrigin
@Controller
@RequestMapping(value = "/test")
public class JadeController {

    @RequestMapping(value = "/home")
    public String homePage(){
        return "/home.html";
    }


    @RequestMapping(value = "/img/show", method = RequestMethod.GET)
    public void showImage(HttpServletRequest request, HttpServletResponse response) {
        //服务器
        String uploadPath = "/home/img/test.png";
        //本地
//            String uploadPath = "D:\\img";
//        if (null == request.getParameter("name") || "".equals(request.getParameter("name"))) {
//            return;
//        }
//        String filePath = uploadPath + "\\" + request.getParameter("name");
//        String filePath = uploadPath + "/" + request.getParameter("name");
        String filePath = uploadPath;
        try {
            FileCopyUtils.copy(new FileInputStream(filePath), response.getOutputStream());
        } catch (IOException e) {

        }
    }

    @RequestMapping("/getPic")
    public void getPic( HttpServletResponse response) {
        response.setContentType("image/jpeg");
        String ip = "47.100.130.172";
        //连接linux服务器
        Connection conn = new Connection(ip,22);
        try {
            conn.connect();
            //输入连接密码
            boolean isAuthenticated = conn.authenticateWithPassword("root", "Xyc950405");
            //校验密码是否正确
            if (isAuthenticated == false) {
                System.err.println("authentication failed");
            }
            //scp执行代码
            SCPClient client = new SCPClient(conn);
            client.get("/home/img/test.png",response.getOutputStream());
            conn.close();
        } catch (IOException ex) {
            System.out.println("连接服务器失败");
        }
    }
}
