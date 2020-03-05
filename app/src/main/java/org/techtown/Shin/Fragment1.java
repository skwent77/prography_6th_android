package org.techtown.Shin;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class Fragment1 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment1,container,false);
        return rootView;

    }
}
