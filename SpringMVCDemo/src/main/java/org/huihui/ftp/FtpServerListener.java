package org.huihui.ftp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * <FTP SERVER操作类>
 * <功能详细描述>
 *
 * @author wenkaixuan
 * @version [版本号, 2014-3-31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FtpServerListener implements ServletContextListener {
    /**
     * 日志对象
     */
    private Log logger = LogFactory.getLog(FtpServerListener.class);

    /**
     * {@inheritDoc}
     */
    public void contextDestroyed(ServletContextEvent sce) {
        logger.error("Stopping FtpServer");
        DefaultFtpServer server = (DefaultFtpServer) sce.getServletContext().getAttribute("FTPSERVER_CONTEXT_NAME");
        if (server != null) {
            server.stop();
            sce.getServletContext().removeAttribute("FTPSERVER_CONTEXT_NAME");
            logger.error("FtpServer stopped");
        } else {
            logger.error("No running FtpServer found");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void contextInitialized(ServletContextEvent sce) {
        logger.error("Starting FtpServer");
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        DefaultFtpServer server = (DefaultFtpServer) ctx.getBean("ftpServer");
        sce.getServletContext().setAttribute("FTPSERVER_CONTEXT_NAME", server);
        try {
            // 获取操作系统
            String osName = System.getProperty("os.name");

            // 如果是windows系統
            if (osName.toLowerCase().indexOf("windows") > -1) {
                // 设置FTP路径
                Global.ftpPath = "C:\\ftpServer";
            } else {
                // 设置FTP路径
                Global.ftpPath = "/home/ftpServer";
            }

            // 判断该路径是否存在，如果不存在则创建
            File file = new File(Global.ftpPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 更新FTP服务器用户信息
            // 首先获取到FTP用户管理对象
            UserManager userManage = server.getUserManager();

            // 删除原有的用户信息
            userManage.delete(Global.ftpUserName);

            // 构造新的用户并保存到数据库
            BaseUser base = new BaseUser();
            base.setName(Global.ftpUserName);
            base.setPassword(Global.ftpPassword);
            base.setHomeDirectory(Global.ftpPath);
            userManage.save(base);

            // 启动FTP服务
            server.start();
            logger.error("FtpServer started");
        } catch (Exception e) {
            logger.error("Failed to start FtpServer");
            logger.error(Global.LOG_EXCEPTION_NAME, e);
        }
    }
}