package com.example.john.util;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    private String EmailAdress;
    private String Password;
    private String UserName;
    private String Mobile;
    private String FavoriteChannel;
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getMobile() {
        return Mobile;
    }
    public void setMobile(String mobile) {
        Mobile = mobile;
    }
    public String getEmailAdress() {
        return EmailAdress;
    }
    public void setEmailAdress(String emailAdress) {
        EmailAdress = emailAdress;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getFavoriteChannel(){return FavoriteChannel;}
    public void setFavoriteChannel(String favoriteChannel){FavoriteChannel = favoriteChannel;}
}
