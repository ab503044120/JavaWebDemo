package org.huihui.dao;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class BaseUser implements User {
    private String name;

    private String password;

    private String homeDirectory;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public AuthorizationRequest authorize(AuthorizationRequest arg0) {
        return null;
    }

    public List<Authority> getAuthorities() {
        return null;
    }

    public List<Authority> getAuthorities(Class<? extends Authority> arg0) {
        return null;
    }

    public boolean getEnabled() {
        return true;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public int getMaxIdleTime() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @return 返回 writepermission
     */
    public boolean getWritePermission() {
        return true;
    }
}