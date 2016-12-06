package com.drudyak.parsers;


public abstract class Parser {

    public void parse() {

        login();
        openWishList();
        savePrices();

    }

    protected abstract void login();
    protected abstract void openWishList();
    protected abstract void savePrices();

}
