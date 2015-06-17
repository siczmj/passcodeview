package com.nirigo.mobile.passcode.examples.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nirigo.mobile.passcode.R;
import com.nirigo.mobile.passcode.examples.models.PasscodeItemAndroid;
import com.nirigo.mobile.passcode.examples.models.PasscodeItemAndroidImage;
import com.nirigo.mobile.view.passcode.adapters.PasscodeBaseAdapter;
import com.nirigo.mobile.view.passcode.models.PasscodeItem;
import com.nirigo.mobile.view.passcode.models.PasscodeItemEmpty;

import java.util.Arrays;

/**
 * Created by Sicz-Mesziár János on 2015.06.17..
 */
public class AndroidPasscodeAdapter extends PasscodeBaseAdapter {

    private LayoutInflater inflater;

    public AndroidPasscodeAdapter(Context context) {
        super(Arrays.asList(
                new PasscodeItemAndroid("1", PasscodeItem.TYPE_NUMBER, ""),
                new PasscodeItemAndroid("2", PasscodeItem.TYPE_NUMBER, "ABC"),
                new PasscodeItemAndroid("3", PasscodeItem.TYPE_NUMBER, "DEF"),
                new PasscodeItemAndroid("4", PasscodeItem.TYPE_NUMBER, "GHI"),
                new PasscodeItemAndroid("5", PasscodeItem.TYPE_NUMBER, "JKL"),
                new PasscodeItemAndroid("6", PasscodeItem.TYPE_NUMBER, "MNO"),
                new PasscodeItemAndroid("7", PasscodeItem.TYPE_NUMBER, "PQRS"),
                new PasscodeItemAndroid("8", PasscodeItem.TYPE_NUMBER, "TUV"),
                new PasscodeItemAndroid("9", PasscodeItem.TYPE_NUMBER, "WXYZ"),
                new PasscodeItemEmpty(),
                new PasscodeItemAndroid("0", PasscodeItem.TYPE_NUMBER, ""),
                new PasscodeItemAndroidImage("Enter", PasscodeItem.TYPE_ENTER, R.drawable.ic_keyboard_return_white_36dp)
        ));
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PasscodeItem item = getItem(position);

        if(item instanceof PasscodeItemAndroidImage) {  // Image type

            if (convertView == null || convertView.getTag() != PasscodeItemAndroidImage.class) {
                convertView = inflater.inflate(R.layout.button_passcode_android_image, parent, false);
                convertView.setTag(PasscodeItemAndroidImage.class);
            }

            ImageView imageView = (ImageView) convertView;
            imageView.setImageResource(((PasscodeItemAndroidImage) item).getIconRes());

        }else { // Number type

            if (convertView == null || convertView.getTag() != PasscodeItemAndroid.class) {
                convertView = inflater.inflate(R.layout.button_passcode_android, parent, false);
                convertView.setTag(PasscodeItemAndroid.class);
            }

            AppCompatTextView numberTextView = (AppCompatTextView) convertView.findViewById(R.id.number);
            numberTextView.setText(item.getValue());
            AppCompatTextView charactersTextView = (AppCompatTextView) convertView.findViewById(R.id.characters);

            if(item instanceof PasscodeItemAndroid){
                charactersTextView.setText(((PasscodeItemAndroid) item).getCharacters());
            }else{
                charactersTextView.setText("");
            }

        }

        convertView.setVisibility(item.getType() == PasscodeItem.TYPE_EMPTY ? View.INVISIBLE : View.VISIBLE);

        return convertView;

    }

}
