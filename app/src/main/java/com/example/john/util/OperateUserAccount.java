package com.example.john.util;

import android.content.ContentValues;

import com.example.john.util.User;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class OperateUserAccount {
    public static String Login(String username,String password){
        List<User> user = LitePal.where("EmailAdress == ? and Password == ?",username,password).find(User.class);
        if(user.isEmpty()){
            return null;
        }
        else{
            return user.get(0).getUserName();
        }
    }
    public static boolean Register(String username, String email, String mobile, String password){
        List<User> userlist1 = LitePal.where("UserName == ?",username).find(User.class);
        List<User> userlist2 = LitePal.where("EmailAdress == ?",email).find(User.class);
        if((userlist1.isEmpty())&&(userlist2.isEmpty())){
            User user = new User();
            user.setUserName(username);
            user.setEmailAdress(email);
            user.setMobile(mobile);
            user.setPassword(password);
            user.setFavoriteChannel("business,entertainment,general,health,science,sports,technology");
            if(user.save()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public static String getChannel(String username){
        List<User> userlist = LitePal.where("UserName == ?",username).find(User.class);
        return userlist.get(0).getFavoriteChannel();
    }
    public static  void setChannel(String username,String channel){
        ContentValues values = new ContentValues();
        values.put("title", "今日iPhone6 Plus发布");
        User user = new User();
        user.setFavoriteChannel(channel);
        user.updateAll("UserName = ?",username);
    }
}
