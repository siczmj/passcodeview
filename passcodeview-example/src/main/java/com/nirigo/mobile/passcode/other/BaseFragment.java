package com.nirigo.mobile.passcode.other;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

}
