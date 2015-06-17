package com.nirigo.mobile.view.passcode.models;

/**
 * Created by Sicz-Mesziár János on 2015.06.14..
 *
 * Describe a button on PasscodeView.
 */
public class PasscodeItem {

    public final static int TYPE_EMPTY  = -1,
                            TYPE_NUMBER =  0,
                            TYPE_REMOVE =  1,
                            TYPE_CLEAR  =  2,
                            TYPE_ENTER  =  3;

    private String value;
    private int type;

    public PasscodeItem(String value, int type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return value;
    }
}
