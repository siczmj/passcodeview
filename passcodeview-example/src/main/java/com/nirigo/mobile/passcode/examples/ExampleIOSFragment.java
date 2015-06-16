package com.nirigo.mobile.passcode.examples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nirigo.mobile.passcode.R;
import com.nirigo.mobile.passcode.examples.adapters.CustomPasscodeAdapter;
import com.nirigo.mobile.passcode.examples.adapters.IOSPasscodeAdapter;
import com.nirigo.mobile.passcode.other.BaseFragment;
import com.nirigo.mobile.view.passcode.PasscodeIndicator;
import com.nirigo.mobile.view.passcode.PasscodeView;

/**
 * Created by Sicz-Mesziár János on 2015.06.16..
 */
public class ExampleIOSFragment extends BaseFragment {

    private PasscodeView passcodeView;
    private PasscodeIndicator passcodeIndicator;

    private IOSPasscodeAdapter iosPasscodeAdapter;

    private String yourCurrentPasscode = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateFragmentLayout(inflater, container, R.layout.fragment_example_ios);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        passcodeView = (PasscodeView) this.view.findViewById(R.id.passcode_view);
        passcodeIndicator = (PasscodeIndicator) this.view.findViewById(R.id.passcode_indicator);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        iosPasscodeAdapter = new IOSPasscodeAdapter(getActivity());

        passcodeView.setAdapter(iosPasscodeAdapter);
        passcodeView.setOnItemClickListener(new PasscodeView.OnItemClickListener() {
            public void onItemClick(PasscodeView view, int position, View item, Object o) {

                yourCurrentPasscode += o.toString();
                passcodeIndicator.setIndicatorLevel(yourCurrentPasscode.length());

                if(yourCurrentPasscode.length() == passcodeIndicator.getIndicatorLength()){
                    yourCurrentPasscode = "";
                }

            }
        });
    }
}
