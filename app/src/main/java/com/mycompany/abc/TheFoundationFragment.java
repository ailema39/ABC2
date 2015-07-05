package com.mycompany.abc;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class TheFoundationFragment extends Fragment implements Html.ImageGetter {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Retrieving the currently selected item number
        //int position = getArguments().getInt("position");
        String title = getArguments().getString("title");
        String aboutUs = getArguments().getString("aboutUs");

        View v = inflater.inflate(R.layout.fragment_the_foundation, container, false);
        Button b = (Button)v.findViewById(R.id.donation);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationsFragment dFragment = new DonationsFragment();
                Bundle bData = new Bundle();
                String[] foundation = getResources().getStringArray(R.array.foundation_array);
                String donations = getResources().getString(R.string.donations);
                int position = 1;
                bData.putInt("position", position);
                bData.putString("title", foundation[position]);
                bData.putString("donations", donations);
                dFragment.setArguments(bData);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft1 = fm.beginTransaction();
                ft1.replace(R.id.content_frame, dFragment);
                ft1.commit();
            }
        });

        TextView t = (TextView)v.findViewById(R.id.about_us);

        Spanned spanned = Html.fromHtml(aboutUs, this, null);
        t.setText(spanned);
        t.setMovementMethod(LinkMovementMethod.getInstance());

        ((MainActivity)getActivity()).getSupportActionBar().setTitle(title);

        return v;
    }

    @Override
    public Drawable getDrawable(String arg0) {
        int id = 0;

        if(arg0.equals("facebook.png")){
            id = R.drawable.ic_facebook;
        }

        if(arg0.equals("instagram.png")){
            id = R.drawable.ic_instagram;
        }
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(id);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        return d;
    }
}
