package com.mycompany.abc;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DonationsFragment extends Fragment implements Html.ImageGetter {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String title = getArguments().getString("title");
        String donations = getArguments().getString("donations");

        View v = inflater.inflate(R.layout.fragment_donations, container, false);
        TextView t = (TextView)v.findViewById(R.id.donations);

        Spanned spanned = Html.fromHtml(donations, this, null);
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
