package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public  class StudOrgFragment extends Fragment {
    public static final String TAG = "hymn";
    private Toolbar mToolbar;
    private Button btnStudOrg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stud_org, container, false);
        btnStudOrg = (Button)rootView.findViewById(R.id.btnStudOrg);

            btnStudOrg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    startActivity(new Intent(getActivity(), ViewingStudOrgList.class));
                }
            });
            return rootView;
        }


    }
