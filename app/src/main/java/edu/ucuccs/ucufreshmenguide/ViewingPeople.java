package edu.ucuccs.ucufreshmenguide;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;


public class ViewingPeople extends ActionBarActivity {
    private List<ClassFaculty> faculty;
    private RecyclerView rv;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_people);
        mToolbar = (Toolbar) findViewById(R.id.toolbarPeople);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.myPrimaryDarkColor));
        }
        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        faculty = new ArrayList<>();
        faculty.add(new ClassFaculty("Emma Wilson", "23 years old", R.mipmap.ic_book));
        faculty.add(new ClassFaculty("Lavery Maiss", "25 years old", R.mipmap.ic_call));
        faculty.add(new ClassFaculty("Lillie Watts", "35 years old", R.mipmap.ic_home));
    }

    private void initializeAdapter(){
        FacultyAdapter adapter = new FacultyAdapter(faculty);
        rv.setAdapter(adapter);
    }
}
