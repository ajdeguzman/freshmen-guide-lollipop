package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);



        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("UCU Freshmen Guide", BitmapFactory.decodeResource(getResources(), R.mipmap.ucu_logo));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fHymn = new FragmentUCUHymn();
        Fragment fMap = new FragmentCampusMap();
        Fragment fhandbook= new FragmentHandbook();
        Fragment fcontactUs = new FragmentContactUs();
        Fragment fstudOrg = new FragmentStudentOrg();
        Fragment fCourses = new FragmentCoursesOffered();
        FragmentTransaction t = getFragmentManager().beginTransaction();
        switch (position) {
            case 0: //search //todo
                break;
            case 1:
                t.replace(R.id.container, fhandbook);
                t.addToBackStack(null);
                t.commit();
                break;
            case 2: //my account //todo
                t.replace(R.id.container, fMap);
                t.addToBackStack(null);
                t.commit();
                break;
            case 3: //my account //todo
                t.replace(R.id.container, fCourses);
                t.addToBackStack(null);
                t.commit();
                break;
            case 5: //stud_org
                t.replace(R.id.container, fstudOrg);
                t.addToBackStack(null);
                t.commit();
                break;
            case 6: //ucuhymn
                t.replace(R.id.container, fHymn);
                t.addToBackStack(null);
                t.commit();
                break;
            case 7: //stats
                t.replace(R.id.container, fcontactUs);
                t.addToBackStack(null);
                t.commit();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }


}
