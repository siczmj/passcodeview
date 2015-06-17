package com.nirigo.mobile.passcode.examples;

import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nirigo.mobile.passcode.R;
import com.nirigo.mobile.passcode.examples.adapters.AndroidPasscodeAdapter;
import com.nirigo.mobile.passcode.other.BaseFragment;
import com.nirigo.mobile.view.passcode.PasscodeView;
import com.nirigo.mobile.view.passcode.models.PasscodeItem;

/**
 * Created by Sicz-Mesziár János on 2015.06.17..
 * Android example
 */
public class ExampleAndroidFragment extends BaseFragment {

    private PasscodeView passcodeView;
    private AndroidPasscodeAdapter adapter;
    private AppCompatTextView passcodeTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateFragmentLayout(inflater, container, R.layout.fragment_example_android);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        passcodeView = (PasscodeView) this.view.findViewById(R.id.passcode_view);
        passcodeTextView = (AppCompatTextView) this.view.findViewById(R.id.passcode_textview);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        adapter = new AndroidPasscodeAdapter(getActivity());

        passcodeView.setAdapter(adapter);
        passcodeView.setOnItemClickListener(new PasscodeView.OnItemClickListener() {
            public void onItemClick(PasscodeView view, int position, View item, Object o) {

                PasscodeItem passcodeItem = view.getAdapter().getItem(position);

                if (passcodeItem.getType() == PasscodeItem.TYPE_ENTER) {

                    Toast.makeText(getActivity(), o.toString(), Toast.LENGTH_LONG).show();
                    passcodeTextView.setText("");

                } else {

                    passcodeTextView.append(o.toString());

                }

            }

        });


    }
}
