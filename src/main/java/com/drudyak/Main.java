package com.drudyak;


import com.drudyak.parsers.Parser;
import com.drudyak.parsers.RozetkaParser;

public class Main {
    public static void main(String[] args) {

        Parser rozetkaParser = new RozetkaParser();

        rozetkaParser.parse();

    }

}
