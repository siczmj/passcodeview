package com.nirigo.mobile.passcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nirigo.mobile.passcode._base.BaseFragment;
import com.nirigo.mobile.view.passcode.PasscodeIndicator;
import com.nirigo.mobile.view.passcode.PasscodeView;

/**
 * Created by Sicz-Mesziár János on 2015.06.16..
 */
public class ExampleCustomizedFragment extends BaseFragment{

    private PasscodeView passcodeView;
    private PasscodeIndicator passcodeIndicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateFragmentLayout(inflater, container, R.layout.fragment_example_customized);
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


    }

}
