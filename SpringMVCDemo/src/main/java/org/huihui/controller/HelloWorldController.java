package org.huihui.controller;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.huihui.model.User;
import org.huihui.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huihui on 2017/6/25.
 */
@Controller
public class HelloWorldController {
    @Resource
    private UserService userService;

    @RequestMapping(value = {"/hello"}, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String hello() {
        User userById = userService.getUserById("1");
        return "helloWorld" + "  " + userById.name;

    }

    //自动匹配参数
    @RequestMapping(value = {"/login"}
            , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(String id) {
        User userById = userService.getUserById(id);
        return "helloWorld" + " 你是 " + userById.name;

    }

    //自动匹配参数
    @RequestMapping(value = {"/login1"}
            , produces = "text/html;charset=UTF-8"
            , method = RequestMethod.GET)
    @ResponseBody
    public String login1(@RequestParam(value = "id") String userId) {
        User userById = userService.getUserById(userId);
        return "helloWorld" + " 你是 " + userById.name;

    }

    //自动匹配参数 post
    @RequestMapping(value = {"/login1"}
            , produces = "text/html;charset=UTF-8"
            , method = RequestMethod.POST)
    @ResponseBody
    public String loginpost(@RequestParam(value = "id") String userId) {
        User userById = userService.getUserById(userId);
        return "helloWorld" + " 你是 " + userById.name;

    }

    //自动匹配参数 post
    @RequestMapping(value = {"/login2"}
            , produces = "text/html;charset=UTF-8"
            , method = RequestMethod.GET)
    @ResponseBody
    public void loginpost1(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");

        FTPClient ftpClient = new FTPClient();
        try {
            int reply;
            ftpClient.connect("127.0.0.1",2121);
            ftpClient.login("1", "1");
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return;
            }
            ftpClient.changeWorkingDirectory("/GOS_CAS/BACKUP/cas_config_backup");//转移到FTP服务器目录
            FTPFile[] fs = ftpClient.listFiles();
            for(int i=0;i<fs.length;i++){
                if(fs[i].getName().equals("app-release_signed_7zip_aligned.apk")){
                    String saveAsFileName = new String(fs[i].getName().getBytes("UTF-8"), "ISO8859-1");
                    response.setHeader("Content-Disposition", "attachment;fileName="+saveAsFileName);
                    OutputStream os = response.getOutputStream();
                    ftpClient.retrieveFile(fs[i].getName(), os);
                    os.flush();
                    os.close();
                    break;
                }
            }
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }
}
