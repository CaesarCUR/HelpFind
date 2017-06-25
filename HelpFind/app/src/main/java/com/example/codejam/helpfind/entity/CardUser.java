package com.example.codejam.helpfind.entity;

import java.util.ArrayList;

/**
 * Created by zhouming on 2017/6/21.
 */

public class CardUser {
    private final String _userName;
    private final String _info;
    private final String _imgURL;
    private ArrayList<String> _imageURLs;

    public CardUser(String _userName, String _info, String _imgURL, ArrayList<String> _imageURLs) {
        this._userName = _userName;
        this._info = _info;
        this._imgURL = _imgURL;
        this._imageURLs = _imageURLs;
    }

    public String get_userName() {
        return _userName;
    }

    public String get_info() {
        return _info;
    }

    public String get_imgURL() {
        return _imgURL;
    }

    public ArrayList<String> get_imageURLs() {
        return _imageURLs;
    }

    //    public ArrayList<String> get_imageURLs() {
//        return _imageURLs;
//    }
}
