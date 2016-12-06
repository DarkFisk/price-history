package com.drudyak.parsers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class RozetkaParser extends Parser {


    protected void login() {

        try {
            Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

            System.out.println(doc.body());
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