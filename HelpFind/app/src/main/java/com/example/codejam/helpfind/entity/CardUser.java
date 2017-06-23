package com.example.codejam.helpfind.entity;

/**
 * Created by zhouming on 2017/6/21.
 */

public class CardUser {
    private final String _userName;
    private final String _info;
    private final String _imgURL;

    public CardUser(String _userName, String _info, String _imgURL) {
        this._userName = _userName;
        this._info = _info;
        this._imgURL = _imgURL;
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
}
