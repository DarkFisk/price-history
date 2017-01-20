package com.drudyak.parsers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CitrusParser extends Parser{

    private String LOGIN = "drudyak@gmail.com";
    private String PASSWORD = "";

    private String TOKEN = "";

    private Map<String, String> COOKIES;

    protected void login() {

        setLogin();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setPASSWORD();

    }

    protected void openWishList() {
//        // Currently we have been authenticated and can check
//        Document wishlistsPage;
//        try {
//            wishlistsPage = Jsoup.connect("https://my.rozetka.com.ua/ua/profile/wishlists/")//
//                    .cookies(COOKIES)//
//                    .get();
//
//            // print wishlist
//            Elements productBoxes = wishlistsPage.select(".wishlist-g-i");
//            for(Element productBox : productBoxes) {
//                System.out.println("=============");
//                System.out.println("Name: " + productBox.select(".g-title-link").text());
//                System.out.println("Price: " + productBox.select(".g-price-uah").text());
//                System.out.println("Available: " + !productBox.attr("class").contains("unavailable"));
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
    }

    protected void savePrices() {
        //TODO: save price history
    }

    private void setLogin() {

        try {
            // open the login page so that get User Session ID
            Connection.Response loginPageResponse = Jsoup.connect("https://my.citrus.ua/ru/auth/login")
                    .method(Connection.Method.GET)
                    .execute();
            Document loginPageDoc = Jsoup.parse(loginPageResponse.body());
            TOKEN = loginPageDoc.select("input[name=\"_token\"]").val();
//            System.out.println("User Session ID: " + loginPageResponse.cookie("uid"));

            // Submit the login form with correct request headers and params
            Connection.Response loginResponse = Jsoup.connect("https://my.citrus.ua/ru/auth/login")
                    .method(Connection.Method.POST)
                    .headers(new HashMap<String, String>(){{
                        put("Accept", "text/javascript, text/html, application/xml, text/xml, */*");
                        put("Content-Type", "application/x-www-form-urlencoded");
                        put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
                        put("Host", "my.citrus.ua");
                        put("Origin", "https://my.citrus.ua");
                        put("Referer", "https://my.citrus.ua/ru/auth/login");
                        put("Upgrade-Insecure-Requests", "1");
                    }})
                    .ignoreContentType(true)
                    .data("_token", TOKEN)
                    .data("identity", LOGIN)
                    .cookies(loginPageResponse.cookies())
                    .execute();

//            System.out.println(loginResponse.body());

            COOKIES = loginResponse.cookies();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void setPASSWORD() {

        try {
            // open the login page so that get User Session ID
            Connection.Response emailPageResponse = Jsoup.connect("https://my.citrus.ua/ru/auth/email")
                    .method(Connection.Method.GET)
                    .execute();
//            Document emailPageDoc = Jsoup.parse(emailPageResponse.body());
//            System.out.println("User Session ID: " + emailPageResponse.body());

            // Submit the login form with correct request headers and params
            Connection.Response emailResponse = Jsoup.connect("https://my.citrus.ua/ru/auth/email")
                    .method(Connection.Method.POST)
                    .headers(new HashMap<String, String>(){{
                        put("Accept", "text/javascript, text/html, application/xml, text/xml, */*");
                        put("Content-Type", "application/x-www-form-urlencoded");
                        put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
                        put("Host", "my.citrus.ua");
                        put("Origin", "https://my.citrus.ua");
                        put("Referer", "https://my.citrus.ua/ru/auth/email");
                        put("Upgrade-Insecure-Requests", "1");
                    }})
                    .ignoreContentType(true)
                    .data("_token", TOKEN)
                    .data("password", PASSWORD)
                    .cookies(emailPageResponse.cookies())
                    .execute();

            System.out.println(emailResponse.body());

            COOKIES = emailResponse.cookies();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

