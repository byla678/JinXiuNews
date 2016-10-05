package com.itfeige.my.jinxiunews.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itfeige.my.jinxiunews.R;

/**
 * Created by a on 2016/10/2.
 */
public class FindFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancState) {
        View view = inflater.inflate(R. layout.activity_find,null);
        return view;
    }
}
