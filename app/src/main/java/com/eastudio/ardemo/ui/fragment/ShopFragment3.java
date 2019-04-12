package com.eastudio.ardemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eastudio.ardemo.ui.fragment.presenter.ShopFragmentPresenter;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;

import com.eastudio.ardemo.R;

@RequiresPresenter(ShopFragmentPresenter.class)
public class ShopFragment3 extends BeamFragment<ShopFragmentPresenter> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewContent = inflater.inflate(R.layout.fragment_shop3, container, false);
        return viewContent;
    }

}
