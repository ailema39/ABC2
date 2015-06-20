package com.mycompany.abc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DangerSituationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Retrieving the currently selected item number
        //int position = getArguments().getInt("position");
        String title = getArguments().getString("title");
        String aTip = getArguments().getString("aTip");
        String bTip = getArguments().getString("bTip");
        String cTip = getArguments().getString("cTip");

        View v = inflater.inflate(R.layout.fragment_danger_situation, container, false);
        TextView t = (TextView)v.findViewById(R.id.a_tip);

        t.setText(aTip);
        t = (TextView)v.findViewById(R.id.b_tip);
        t.setText(bTip);
        t = (TextView)v.findViewById(R.id.c_tip);
        t.setText(cTip);

        ((MainActivity)getActivity()).getSupportActionBar().setTitle(title);

        return v;
    }

}
