package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import java.util.List;

public class FragmentContactUs extends Fragment {
    public static final String TAG = "handbook";

    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contactus, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final FloatingActionsMenu floatingActionsMenu = new FloatingActionsMenu(getActivity());

        ObservableScrollView listView = (ObservableScrollView) rootView.findViewById(R.id.scrollInfoContact);
        rootView.findViewById(R.id.layoutMoreOptions).bringToFront();
        rootView.findViewById(R.id.layoutContactUs).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (floatingActionsMenu.isExpanded())
                    floatingActionsMenu.collapse();
                return true;
            }
        });
        rootView.findViewById(R.id.fabWebsite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ucu.edu.ph"));
                startActivity(webIntent);
            }
        });
        rootView.findViewById(R.id.fabCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+63755687612"));
                startActivity(callIntent);
            }
        });
        rootView.findViewById(R.id.fabPlace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = 15.9800295;
                double longitude = 120.5610351;
                String label = "Urdaneta City University";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        rootView.findViewById(R.id.fabMail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"urdanetacityuniversity@yahoo.com"});
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email Client"));
            }
        });
        return rootView;
    }
    public static boolean isMailClientPresent(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);
        if (list.size() == 0)
            return false;
        else
            return true;
    }
}
