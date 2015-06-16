package com.nirigo.mobile.passcode.examples.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nirigo.mobile.passcode.R;
import com.nirigo.mobile.view.passcode.adapters.PasscodeBaseAdapter;
import com.nirigo.mobile.view.passcode.models.PasscodeItem;

import java.util.Arrays;

/**
 * Created by Sicz-Mesziár János on 2015.06.16..
 */
public class CustomPasscodeAdapter extends PasscodeBaseAdapter {

    private LayoutInflater inflater;

    public CustomPasscodeAdapter(Context context) {
        super(Arrays.asList(
                new PasscodeItem("1", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("2", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("3", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("4", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("5", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("6", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("7", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("8", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("9", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("X", PasscodeItem.TYPE_CLEAR),
                new PasscodeItem("0", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("<", PasscodeItem.TYPE_REMOVE)
        ));
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PasscodeItem item = getItem(position);

        if(convertView == null || convertView.getTag() != PasscodeItem.class){
            convertView = inflater.inflate(R.layout.button_passcode_custom, parent, false);
            convertView.setTag(PasscodeItem.class);
        }

        AppCompatButton button = (AppCompatButton) convertView;
        button.setText(item.getValue());
        button.setVisibility(item.getType() == PasscodeItem.TYPE_EMPTY ? View.INVISIBLE : View.VISIBLE);

        return convertView;

    }


}
