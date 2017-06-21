package com.example.codejam.helpfind.entity;

/**
 * Created by zhouming on 2017/6/21.
 */

public class CardUser {
    private final String _userName;
    private final String _info;

    public CardUser(String _userName, String _info) {
        this._userName = _userName;
        this._info = _info;
    }

    public String get_userName() {
        return _userName;
    }

    public String get_info() {
        return _info;
    }
}
