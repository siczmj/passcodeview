package com.nirigo.mobile.passcode.examples.models;

import com.nirigo.mobile.view.passcode.models.PasscodeItem;

/**
 * Created by Sicz-Mesziár János on 2015.06.16..
 */
public class PasscodeItemIOS extends PasscodeItem{

    private String characters;

    public PasscodeItemIOS(String value, int type, String characters) {
        super(value, type);
        this.characters = characters;
    }

    public String getCharacters() {
        return characters;
    }
}
