package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentUCUHymn extends Fragment {
    public static final String TAG = "hymn";
    TextView txtLyrics;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hymn, container, false);
        txtLyrics = (TextView)rootView.findViewById(R.id.txtLyrics);
        txtLyrics.setText(Html
                .fromHtml(getString(R.string.title_hymn)));
        /*Intent i = new Intent(getActivity(), ViewingHymn.class);
        int index = 2;
        i.putExtra("index_key", index);
        startActivity(i);*/

        return rootView;
    }

}
