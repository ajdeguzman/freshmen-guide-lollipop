package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class FragmentUCUHymn extends Fragment {
    public static final String TAG = "hymn";
    TextView txtLyrics;
    View rootView;
    VideoView videoHymn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_hymn, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        videoHymn = (VideoView) rootView.findViewById(R.id.videoHymn);
        videoHymn.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + R.raw.hymn);
        videoHymn.setKeepScreenOn(true);
        videoHymn.start();
        MediaController myController = new MediaController(getActivity());
        videoHymn.setMediaController(myController);
        txtLyrics = (TextView) rootView.findViewById(R.id.txtLyrics);
        txtLyrics.setText(Html.fromHtml(getString(R.string.title_hymn)));

        rootView.findViewById(R.id.btnFullScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ViewingHymn.class);
                int index = 2;
                i.putExtra("index_key", index);
                startActivity(i);
            }
        });
        return rootView;
    }

}
