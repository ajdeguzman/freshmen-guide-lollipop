package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FragmentAcademics extends Fragment {
    private Toolbar mToolbar;
    private View rootView;
    private Button btnStudOrg;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_handbook, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        expListView = (ExpandableListView) rootView.findViewById(R.id.listView);
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3) {
                return false;
            }
        });
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
                Log.d("HAHA", groupPosition + "");
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                listDataHeader.get(groupPosition);
                listDataChild.get(childPosition);
                Intent myIntent = new Intent(getActivity(), ViewingIndividualCollege.class);
                myIntent.putExtra("childPosition", childPosition);
                myIntent.putExtra("groupPosition", groupPosition);
                startActivity(myIntent);

                return false;
            }
        });

        expListView.expandGroup(0);
        return rootView;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<>();

        listDataHeader.add("Colleges");
        listDataHeader.add("Graduate Studies");
        listDataHeader.add("Continuing Professional Development");

        List<String> colleges = new ArrayList<String>();
        colleges.add("Accountancy and Business Administration");
        colleges.add("Arts and Languages");
        colleges.add("Caregiver");
        colleges.add("Computer Studies");
        colleges.add("Criminology");
        colleges.add("Education");
        colleges.add("Engineering and Architecture");
        colleges.add("Hotel and Restaurant Services and Tourism Management");
        colleges.add("Law");
        colleges.add("Library and Information Science");
        colleges.add("Midwifery");
        colleges.add("Nursing");
        colleges.add("Pharmacy");
        colleges.add("Political Science");
        colleges.add("Science and Mathematics");
        colleges.add("Social Work");
        listDataChild.put(listDataHeader.get(0), colleges);

    }

}
