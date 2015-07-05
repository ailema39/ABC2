package com.mycompany.abc;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public class DirectoryFragment extends Fragment {

    int[] icEmergency = new int[]{
            R.drawable.ic_ambulancias,
            R.drawable.ic_bomberos,
            R.drawable.ic_cruz_roja,
            R.drawable.ic_policia,
            R.drawable.ic_sinaproc
    };

    String[] organizations;
    String[] phones;
    String[] infos;
    String[] call;

    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Retrieving the currently selected item number
        position = getArguments().getInt("position");
        String title = getArguments().getString("title");

        organizations = getResources().getStringArray(R.array.organizations_array);
        phones = getResources().getStringArray(R.array.phones_array);
        infos = getResources().getStringArray(R.array.infos_array);
        call = getResources().getStringArray(R.array.call_array);

        View v = inflater.inflate(R.layout.fragment_directory, container, false);
        ImageButton b = (ImageButton)v.findViewById(R.id.call);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build the intent
                Uri number = Uri.parse(call[position]);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                // Verify it resolves
                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(callIntent);
                }
            }
        });

        TextView t = (TextView)v.findViewById(R.id.directory);
        t.setCompoundDrawablesWithIntrinsicBounds(0, icEmergency[position], 0, 0);

        Layout.Alignment align = Layout.Alignment.ALIGN_CENTER;
        String text = "<font color=#B54632>" + organizations[position] + "</font>";
        AlignmentSpan span = new AlignmentSpan.Standard(align);
        Spanned spanned = Html.fromHtml(text);
        SpannableString spannableString
                = new SpannableString(spanned);
        spannableString.setSpan(span, 0, spanned.length(), 0);
        t.append(spannableString);
        t.append("\n\n");

        align = Layout.Alignment.ALIGN_NORMAL;
        text = infos[position];
        span = new AlignmentSpan.Standard(align);
        spanned = Html.fromHtml(text);
        spannableString = new SpannableString(spanned);
        spannableString.setSpan(span, 0, spanned.length(), 0);
        t.append(spannableString);
        t.append("\n\n");

        text = phones[position];
        span = new AlignmentSpan.Standard(align);
        spanned = Html.fromHtml(text);
        spannableString = new SpannableString(spanned);
        spannableString.setSpan(span, 0, spanned.length(), 0);
        t.append(spannableString);
        t.append("\n\n");

        //t.setText(Html.fromHtml(text));
        /*Spanned spanned = Html.fromHtml(text);
        Spannable buffer=new SpannableString(spanned);
        buffer.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), spanned.gets);
        t.setText(buffer);*/
        t.setMovementMethod(LinkMovementMethod.getInstance());

        ((MainActivity)getActivity()).getSupportActionBar().setTitle(title);

        return v;
    }

    public void makeCall(View view){
        // Build the intent
        Uri number = Uri.parse(call[position]);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

        // Verify it resolves
        PackageManager packageManager = getActivity().getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(callIntent);
        }
    }
}
