package com.nirigo.mobile.view.passcode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nirigo.mobile.view.passcode.models.PasscodeItem;
import com.nirigo.mobile.view.passcode.models.PasscodeItemEmpty;

import java.util.Arrays;

/**
 * Created by Sicz-Mesziár János on 2015.06.14..
 *
 * Default adapter to PasscodeView.
 */
public class PasscodeAdapter extends PasscodeBaseAdapter {

    private LayoutInflater inflater;

    public PasscodeAdapter(Context context) {
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
                new PasscodeItemEmpty(),
                new PasscodeItem("0", PasscodeItem.TYPE_NUMBER),
                new PasscodeItemEmpty()
        ));
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PasscodeItem item = getItem(position);

        if(convertView == null){
            convertView = inflater.inflate(com.nirigo.mobile.view.passcode.R.layout.button_passcode, parent, false);
        }

        Button button = (Button) convertView;
               button.setText(item.getValue());

        button.setVisibility(item.getType() == PasscodeItem.TYPE_EMPTY ? View.INVISIBLE : View.VISIBLE);

        return convertView;
    }
}
