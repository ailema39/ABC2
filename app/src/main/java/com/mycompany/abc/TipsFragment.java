package com.mycompany.abc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TipsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Retrieving the currently selected item number
        //int position = getArguments().getInt("position");
        String title = getArguments().getString("title");
        String tip1 = getArguments().getString("tip1");
        String tip2 = getArguments().getString("tip2");
        String tip3 = getArguments().getString("tip3");

        View v = inflater.inflate(R.layout.fragment_tips, container, false);
        TextView t = (TextView)v.findViewById(R.id.tip_1);

        t.setText(tip1);
        t = (TextView)v.findViewById(R.id.tip_2);
        t.setText(tip2);
        t = (TextView)v.findViewById(R.id.tip_3);
        t.setText(tip3);

        ((MainActivity)getActivity()).getSupportActionBar().setTitle(title);

        return v;
    }

}
