package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentContactUs extends Fragment {
    public static final String TAG = "handbook";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast t = Toast.makeText(getActivity(), "Pinch Map to Zoom", Toast.LENGTH_SHORT);
        t.show();
        return inflater.inflate(R.layout.fragment_contactus, container, false);
    }
}
