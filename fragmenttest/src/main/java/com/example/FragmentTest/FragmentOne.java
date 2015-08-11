package com.example.FragmentTest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

/**
 * Created by Administrator on 2015/8/11.
 */
public class FragmentOne extends Fragment implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        FragmentTwo fTwo = new FragmentTwo();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.id_content,fTwo,"TWO");
        tx.addToBackStack(null);
    }
}
