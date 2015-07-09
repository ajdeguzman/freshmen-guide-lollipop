package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FragmentOrgChart extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment_org_chart, container, false);
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
        prepareListData();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
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

        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                listDataHeader.get(groupPosition);
                listDataChild.get(childPosition);

                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0: {
                                Intent i = new Intent(getActivity(),ChartViewLandscape.class);
                                int index = 0;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 1: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 1;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 2: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 2;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 3: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 3;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 4: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 4;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 5: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 5;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 6: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 6;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 7: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 7;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 8: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 8;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 9: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 9;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 10: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 10;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 11: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 11;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 12: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 12;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }

                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 13;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 1: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 14;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 2: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 15;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 3: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 16;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 4: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 17;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 5: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 18;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 6: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 19;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 7: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 20;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 8: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 21;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 9: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 22;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 10: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 23;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 11: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 24;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 12: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 25;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 13: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 26;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 14: {
                                Intent i = new Intent(getActivity(), ChartViewing.class);
                                int index = 27;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                            case 15: {
                                Intent i = new Intent(getActivity(), ChartViewLandscape.class);
                                int index = 28;
                                i.putExtra("index_key", index);
                                startActivity(i);
                                break;
                            }
                        }
                        break;
                }
                return false;
            }
        });
        expListView.expandGroup(0);
        return rootView;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Administrative | Non-Teaching Personnel");
        listDataHeader.add("Academic | Teaching Organization");

        List<String> s1 = new ArrayList<String>();
        s1.add("Academic Council");
        s1.add("Administrative Service");
        s1.add("Office of Student Affairs");
        s1.add("Development Center");
        s1.add("Human Resource Management Office");
        s1.add("Board of Regents");
        s1.add("Accounting Office");
        s1.add("Cashier Office");
        s1.add("Extension Service");
        s1.add("Alumni");
        s1.add("University Clinic");
        s1.add("Graduate School");
        s1.add("PEHM Department/Sports Development and Cultural Affairs");

        List<String> s2 = new ArrayList<String>();
        s2.add("Computer Studies");
        s2.add("Library and Information Science Department");
        s2.add("College of Accountancy and Business Administration");
        s2.add("College of Arts and Languages");
        s2.add("College of Nursing");
        s2.add("College of Pharmacy");
        s2.add("College of Science and Mathematics");
        s2.add("College of Social Work");
        s2.add("College of Criminology");
        s2.add("College of Education");
        s2.add("College of Engineering and Architecture");
        s2.add("School of Midwifery and Caregiving");
        s2.add("Political Science Department");
        s2.add("Department of Psychology");
        s2.add("College of Tourism");
        s2.add("School of Hotel and Restaurant Management and Tourism Management");

        listDataChild.put(listDataHeader.get(0), s1);
        listDataChild.put(listDataHeader.get(1), s2);

    }

}
