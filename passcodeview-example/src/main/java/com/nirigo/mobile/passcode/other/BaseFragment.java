package com.nirigo.mobile.passcode.other;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Sicz-Mesziár János on 2015.06.16..
 *
 * Base class using better fragments.
 */
public class BaseFragment extends Fragment {

    protected View view;

    protected View inflateFragmentLayout(LayoutInflater inflater, ViewGroup container, int fragmentLayout){
        if(view == null){
            view = inflater.inflate(fragmentLayout, container, false);
        }else {
            removeParent(view);
        }
        return view;
    }

    protected void removeParent(View view){
        if(view == null || view.getParent() == null) return;
        ((ViewGroup)view.getParent()).removeView(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(view != null)
                view.setPadding(0, 0, 0, ScreenUtils.getNavigationBarSize(getActivity()).y);
        }
    }
}
