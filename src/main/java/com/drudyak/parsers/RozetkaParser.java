package com.drudyak.parsers;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RozetkaParser extends Parser {
    
    private String LOGIN = "drudyak@gmail.com";
    private String PASSWORD = "**************";
    
    private Map<String, String> COOKIES;

    protected void login() {

        try {
            // open the login page so that get User Session ID
            Connection.Response loginPageResponse = Jsoup.connect("https://my.rozetka.com.ua/signin/")
                    .method(Connection.Method.GET)
                    .execute();
            System.out.println("User Session ID: " + loginPageResponse.cookie("uid"));
            
            // Submit the login form with correct request headers and params
            Connection.Response loginResponse = Jsoup.connect("https://my.rozetka.com.ua/cgi-bin/form.php?r=https%3A%2F%2Fmy.rozetka.com.ua%2Fsignin%2F&action=SignIn")
                    .method(Method.POST)
                    .headers(new HashMap<String, String>(){{
                        put("Accept", "text/javascript, text/html, application/xml, text/xml, */*");
                        put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                        put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
                        put("X-Requested-With", "XMLHttpRequest");
                        put("X-Rozetka-Header", "true");
                        put("ajaxAction", "https://my.rozetka.com.ua/signin/#SignIn");
                    }})
                    .ignoreContentType(true)
                    .data("cookieexists", "false")
                    .data("login", LOGIN)
                    .data("password", PASSWORD)
                    .data("request_token", loginPageResponse.cookie("uid"))
                    .cookies(loginPageResponse.cookies())
                    .execute();
            
            COOKIES = loginResponse.cookies();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    protected void openWishList() {
        // Currently we have been authenticated and can check
        Document wishlistsPage;
        try {
            wishlistsPage = Jsoup.connect("https://my.rozetka.com.ua/ua/profile/wishlists/")//
                    .cookies(COOKIES)//
                    .get();

            // print wishlist
            Elements productBoxes = wishlistsPage.select(".wishlist-g-i");
            for(Element productBox : productBoxes) {
                System.out.println("=============");
                System.out.println("Name: " + productBox.select(".g-title-link").text());
                System.out.println("Price: " + productBox.select(".g-price-uah").text());
                System.out.println("Available: " + !productBox.attr("class").contains("unavailable"));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected void savePrices() {
      //TODO: save price history
    }
}

//todo get selectors;
