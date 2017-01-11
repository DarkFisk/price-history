package com.drudyak.parsers;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class RozetkaParser extends Parser {


    protected void login() {

        try {
            // Document doc = Jsoup.connect("https://my.rozetka.com.ua/signin/").get();

            Connection.Response loginForm = Jsoup.connect("https://my.rozetka.com.ua/signin/")
                    .method(Connection.Method.GET)
                    .execute();

            Document doc = Jsoup.parse(loginForm.body());


            System.out.println(doc.select("form"));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    protected void openWishList() {

    }

    protected void savePrices() {

    }
}

//todo get selectors;