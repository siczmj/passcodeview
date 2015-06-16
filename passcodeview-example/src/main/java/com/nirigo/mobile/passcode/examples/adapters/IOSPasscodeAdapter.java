package com.nirigo.mobile.passcode.examples.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nirigo.mobile.passcode.R;
import com.nirigo.mobile.passcode.examples.models.PasscodeItemIOS;
import com.nirigo.mobile.view.passcode.adapters.PasscodeBaseAdapter;
import com.nirigo.mobile.view.passcode.models.PasscodeItem;
import com.nirigo.mobile.view.passcode.models.PasscodeItemEmpty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sicz-Mesziár János on 2015.06.16..
 */
public class IOSPasscodeAdapter extends PasscodeBaseAdapter {

    private LayoutInflater inflater;

    public IOSPasscodeAdapter(Context context) {
        super(Arrays.asList(
                new PasscodeItemIOS("1", PasscodeItem.TYPE_NUMBER, ""),
                new PasscodeItemIOS("2", PasscodeItem.TYPE_NUMBER, "ABC"),
                new PasscodeItemIOS("3", PasscodeItem.TYPE_NUMBER, "DEF"),
                new PasscodeItemIOS("4", PasscodeItem.TYPE_NUMBER, "GHI"),
                new PasscodeItemIOS("5", PasscodeItem.TYPE_NUMBER, "JKL"),
                new PasscodeItemIOS("6", PasscodeItem.TYPE_NUMBER, "MNO"),
                new PasscodeItemIOS("7", PasscodeItem.TYPE_NUMBER, "PQRS"),
                new PasscodeItemIOS("8", PasscodeItem.TYPE_NUMBER, "TUV"),
                new PasscodeItemIOS("9", PasscodeItem.TYPE_NUMBER, "WXYZ"),
                new PasscodeItemEmpty(),
                new PasscodeItemIOS("0", PasscodeItem.TYPE_NUMBER, ""),
                new PasscodeItemEmpty()
        ));
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null || convertView.getTag() != PasscodeItemIOS.class){
            convertView = inflater.inflate(R.layout.button_passcode_ios, parent, false);
            convertView.setTag(PasscodeItemIOS.class);
        }

        PasscodeItem item = getItem(position);

        AppCompatTextView numberTextView = (AppCompatTextView) convertView.findViewById(R.id.number);
        numberTextView.setText(item.getValue());
        AppCompatTextView charactersTextView = (AppCompatTextView) convertView.findViewById(R.id.characters);
        if(item instanceof PasscodeItemIOS){
            charactersTextView.setText(((PasscodeItemIOS) item).getCharacters());
        }else{
            charactersTextView.setText("");
        }

        convertView.setVisibility(item.getType() == PasscodeItem.TYPE_EMPTY ? View.INVISIBLE : View.VISIBLE);

        return convertView;

    }


}
