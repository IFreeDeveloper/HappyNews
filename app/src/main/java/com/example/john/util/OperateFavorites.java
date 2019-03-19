package com.example.john.util;

import org.litepal.LitePal;

import java.util.List;

public class OperateFavorites {
    public static boolean setFavorites(String username,String title,String imageURL,String newsURL){
        List<Favorites> favoritesList = LitePal.where("UserName == ? and NewsURL == ?",username,newsURL).find(Favorites.class);
        if(favoritesList.isEmpty()){
            Favorites favorites = new Favorites();
            favorites.setUserName(username);
            favorites.setTitle(title);
            favorites.setImageURL(imageURL);
            favorites.setNewsURL(newsURL);
            if(favorites.save()){
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
    public static List<Favorites> getFavorites(String username){
        List<Favorites> favoritesList = LitePal.where("username == ?",username).find(Favorites.class);
        return favoritesList;
    }
}
