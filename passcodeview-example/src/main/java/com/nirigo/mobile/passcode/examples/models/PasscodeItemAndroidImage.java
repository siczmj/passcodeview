package com.nirigo.mobile.passcode.examples.models;

import com.nirigo.mobile.view.passcode.models.PasscodeItem;

/**
 * Created by Sicz-Mesziár János on 2015.06.17..
 */
public class PasscodeItemAndroidImage extends PasscodeItem{

    private int iconRes;

    public PasscodeItemAndroidImage(String value, int type, int iconRes) {
        super(value, type);
        this.iconRes = iconRes;
    }

    public int getIconRes() {
        return iconRes;
    }
}
