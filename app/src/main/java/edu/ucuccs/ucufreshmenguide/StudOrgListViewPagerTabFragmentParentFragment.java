/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.ucuccs.ucufreshmenguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.github.ksoichiro.android.observablescrollview.Scrollable;
import com.github.ksoichiro.android.observablescrollview.TouchInterceptionFrameLayout;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

import edu.google.apps.iosched.ui.widget.SlidingTabLayout;

/**
 * This fragment manages ViewPager and its child Fragments.
 * Scrolling techniques are basically the same as ViewPagerTab2Activity.
 */
public class StudOrgListViewPagerTabFragmentParentFragment extends BaseFragment implements ObservableScrollViewCallbacks {
    public static final String FRAGMENT_TAG = "fragment";

    private TouchInterceptionFrameLayout mInterceptionLayout;
    private ViewPager mPager;
    private NavigationAdapter mPagerAdapter;
    private int mSlop;
    private boolean mScrolled;
    private ScrollState mLastScrollState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpagertabfragment_parent, container, false);

        AppCompatActivity parentActivity = (AppCompatActivity) getActivity();
        mPagerAdapter = new NavigationAdapter(getChildFragmentManager());
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.myAccentColor));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mPager);

        ViewConfiguration vc = ViewConfiguration.get(parentActivity);
        mSlop = vc.getScaledTouchSlop();
        mInterceptionLayout = (TouchInterceptionFrameLayout) view.findViewById(R.id.container);
        mInterceptionLayout.setScrollInterceptionListener(mInterceptionListener);

        return view;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        if (!mScrolled) {
            adjustToolbar(scrollState);
        }
    }

    private TouchInterceptionFrameLayout.TouchInterceptionListener mInterceptionListener = new TouchInterceptionFrameLayout.TouchInterceptionListener() {
        @Override
        public boolean shouldInterceptTouchEvent(MotionEvent ev, boolean moving, float diffX, float diffY) {
            if (!mScrolled && mSlop < Math.abs(diffX) && Math.abs(diffY) < Math.abs(diffX)) {
                // Horizontal scroll is maybe handled by ViewPager
                return false;
            }

            Scrollable scrollable = getCurrentScrollable();
            if (scrollable == null) {
                mScrolled = false;
                return false;
            }

            // If interceptionLayout can move, it should intercept.
            // And once it begins to move, horizontal scroll shouldn't work any longer.
            View toolbarView = getActivity().findViewById(R.id.toolbar);
            int toolbarHeight = toolbarView.getHeight();
            int translationY = (int) ViewHelper.getTranslationY(mInterceptionLayout);
            boolean scrollingUp = 0 < diffY;
            boolean scrollingDown = diffY < 0;
            if (scrollingUp) {
                if (translationY < 0) {
                    mScrolled = true;
                    mLastScrollState = ScrollState.UP;
                    return true;
                }
            } else if (scrollingDown) {
                if (-toolbarHeight < translationY) {
                    mScrolled = true;
                    mLastScrollState = ScrollState.DOWN;
                    return true;
                }
            }
            mScrolled = false;
            return false;
        }

        @Override
        public void onDownMotionEvent(MotionEvent ev) {
        }

        @Override
        public void onMoveMotionEvent(MotionEvent ev, float diffX, float diffY) {
            View toolbarView = getActivity().findViewById(R.id.toolbar);
            float translationY = ScrollUtils.getFloat(ViewHelper.getTranslationY(mInterceptionLayout) + diffY, -toolbarView.getHeight(), 0);
            ViewHelper.setTranslationY(mInterceptionLayout, translationY);
            ViewHelper.setTranslationY(toolbarView, translationY);
            if (translationY < 0) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mInterceptionLayout.getLayoutParams();
                lp.height = (int) (-translationY + getScreenHeight());
                mInterceptionLayout.requestLayout();
            }
        }

        @Override
        public void onUpOrCancelMotionEvent(MotionEvent ev) {
            mScrolled = false;
            adjustToolbar(mLastScrollState);
        }
    };

    private Scrollable getCurrentScrollable() {
        Fragment fragment = getCurrentFragment();
        if (fragment == null) {
            return null;
        }
        View view = fragment.getView();
        if (view == null) {
            return null;
        }
        return (Scrollable) view.findViewById(R.id.scroll);
    }

    private void adjustToolbar(ScrollState scrollState) {
        View toolbarView = getActivity().findViewById(R.id.toolbar);
        int toolbarHeight = toolbarView.getHeight();
        final Scrollable scrollable = getCurrentScrollable();
        if (scrollable == null) {
            return;
        }
        int scrollY = scrollable.getCurrentScrollY();
        if (scrollState == ScrollState.DOWN) {
            showToolbar();
        } else if (scrollState == ScrollState.UP) {
            if (toolbarHeight <= scrollY) {
                hideToolbar();
            } else {
                showToolbar();
            }
        } else if (!toolbarIsShown() && !toolbarIsHidden()) {
            // Toolbar is moving but doesn't know which to move:
            // you can change this to hideToolbar()
            showToolbar();
        }
    }

    private Fragment getCurrentFragment() {
        return mPagerAdapter.getItemAt(mPager.getCurrentItem());
    }

    private boolean toolbarIsShown() {
        return ViewHelper.getTranslationY(mInterceptionLayout) == 0;
    }

    private boolean toolbarIsHidden() {
        View view = getView();
        if (view == null) {
            return false;
        }
        View toolbarView = getActivity().findViewById(R.id.toolbar);
        return ViewHelper.getTranslationY(mInterceptionLayout) == -toolbarView.getHeight();
    }

    private void showToolbar() {
        animateToolbar(0);
    }

    private void hideToolbar() {
        View toolbarView = getActivity().findViewById(R.id.toolbar);
        animateToolbar(-toolbarView.getHeight());
    }

    private void animateToolbar(final float toY) {
        float layoutTranslationY = ViewHelper.getTranslationY(mInterceptionLayout);
        if (layoutTranslationY != toY) {
            ValueAnimator animator = ValueAnimator.ofFloat(ViewHelper.getTranslationY(mInterceptionLayout), toY).setDuration(200);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float translationY = (float) animation.getAnimatedValue();
                    View toolbarView = getActivity().findViewById(R.id.toolbar);
                    ViewHelper.setTranslationY(mInterceptionLayout, translationY);
                    ViewHelper.setTranslationY(toolbarView, translationY);
                    if (translationY < 0) {
                        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mInterceptionLayout.getLayoutParams();
                        lp.height = (int) (-translationY + getScreenHeight());
                        mInterceptionLayout.requestLayout();
                    }
                }
            });
            animator.start();
        }
    }

    /**
     * This adapter provides two types of fragments as an example.
     * {@linkplain #createItem(int)} should be modifiSturOrgListAcademicViewPagered if you use this example for your app.
     */
    private static class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        private static final String[] TITLES = new String[]{"ACADEMIC", "SPORTS", "CULTURAL", "SOCIO-CIVIC", "SPIRITUAL"};

        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment createItem(int position) {
            Fragment f;
            final int pattern = position % 5;
            switch (pattern) {
                case 0:
                    f = new SturOrgListAcademicViewPager();
                    break;
                case 1:
                    f = new SturOrgListSportsViewPager();
                    break;
                case 2:
                    f = new SturOrgListCulturalViewPager();
                    break;
                case 3:
                    f = new SturOrgListSocioCivicViewPager();
                    break;
                case 4:
                    f = new SturOrgListSpiritualViewPager();
                default:
                    f = new SturOrgListSpiritualViewPager();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

    public static class SturOrgListAcademicViewPager extends BaseFragment {

        private static final String[] ACADEMICS_LIST = new String[]{
                "Supreme Student Council",
                "Graduate School Student Government",
                "University Scribe",
                "Honors Society",
                "Junior Philippine Institute Of Accountants",
                "United Management Accounting And Finance League",
                "Junior Information Technology Society",
                "Association Of Computer Technology Students",
                "Hotel And Restaurant Management Organization",
                "Hotel And Restaurant Services Organization",
                "Circle Of Student Assistants",
                "Apothecary Society"
        };

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_listview, container, false);

            final ObservableListView listView = (ObservableListView) view.findViewById(R.id.scroll);
            setData(listView);

            Fragment parentFragment = getParentFragment();
            ViewGroup viewGroup = (ViewGroup) parentFragment.getView();
            if (viewGroup != null) {
                listView.setTouchInterceptionViewGroup((ViewGroup) viewGroup.findViewById(R.id.container));
                if (parentFragment instanceof ObservableScrollViewCallbacks) {
                    listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentFragment);
                }
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myIntent = new Intent(getActivity(), ViewingIndividualOrg.class);
                    myIntent.putExtra("org",0);
                    myIntent.putExtra("position",position);
                    startActivity(myIntent);
                }
            });
            return view;
        }

        protected void setData(ListView listView) {
            listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, ACADEMICS_LIST));
        }
    }
    public static class SturOrgListSportsViewPager extends BaseFragment {
        private static final String[] SPORTS_LIST = new String[]{
                "Chess Club",
                "Table Tennis Club",
                "Dart Club",
                "Volleyball Club",
                "Badminton Club",
                "Track And Field Club",
                "Basketball Club"
        };
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_listview, container, false);

            final ObservableListView listView = (ObservableListView) view.findViewById(R.id.scroll);
            setData(listView);

            Fragment parentFragment = getParentFragment();
            ViewGroup viewGroup = (ViewGroup) parentFragment.getView();
            if (viewGroup != null) {
                listView.setTouchInterceptionViewGroup((ViewGroup) viewGroup.findViewById(R.id.container));
                if (parentFragment instanceof ObservableScrollViewCallbacks) {
                    listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentFragment);
                }
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myIntent5 = new Intent(getActivity(), ViewingIndividualOrg.class);
                    myIntent5.putExtra("org",1);
                    myIntent5.putExtra("position",position);
                    startActivity(myIntent5);
                }
            });
            return view;
        }

        protected void setData(ListView listView) {
            listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, SPORTS_LIST));
        }
    }

    public static class SturOrgListCulturalViewPager extends BaseFragment {

        private static final String[] CULTURAL_LIST = new String[]{
                "Music Ensemble",
                "Baguntao Theater Guild"
        };

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_listview, container, false);

            final ObservableListView listView = (ObservableListView) view.findViewById(R.id.scroll);
            setData(listView);

            Fragment parentFragment = getParentFragment();
            ViewGroup viewGroup = (ViewGroup) parentFragment.getView();
            if (viewGroup != null) {
                listView.setTouchInterceptionViewGroup((ViewGroup) viewGroup.findViewById(R.id.container));
                if (parentFragment instanceof ObservableScrollViewCallbacks) {
                    listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentFragment);
                }
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myIntent2 = new Intent(getActivity(), ViewingIndividualOrg.class);
                    myIntent2.putExtra("org",2);
                    myIntent2.putExtra("position",position);
                    startActivity(myIntent2);
                }
            });
            return view;
        }

        protected void setData(ListView listView) {
            listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, CULTURAL_LIST));
        }
    }

    public static class SturOrgListSocioCivicViewPager extends BaseFragment {

        private static final String[] SOCIO_CIVIC_LIST = new String[]{
                "Junior Circle Responsible Owners Of Motorcycle",
                "Yoga Science Club"
        };

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_listview, container, false);

            final ObservableListView listView = (ObservableListView) view.findViewById(R.id.scroll);
            setData(listView);

            Fragment parentFragment = getParentFragment();
            ViewGroup viewGroup = (ViewGroup) parentFragment.getView();
            if (viewGroup != null) {
                listView.setTouchInterceptionViewGroup((ViewGroup) viewGroup.findViewById(R.id.container));
                if (parentFragment instanceof ObservableScrollViewCallbacks) {
                    listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentFragment);
                }
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myIntent3 = new Intent(getActivity(), ViewingIndividualOrg.class);
                    myIntent3.putExtra("org",3);
                    myIntent3.putExtra("position",position);
                    startActivity(myIntent3);
                }
            });
            return view;
        }

        protected void setData(ListView listView) {
            listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, SOCIO_CIVIC_LIST));
        }
    }
    public static class SturOrgListSpiritualViewPager extends BaseFragment {

        private static final String[] SPIRITUAL_LIST = new String[]{
                "Kristianong Kabataan Para Sa Bayan",
                "Every Nation Campus"
        };
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_listview, container, false);

            final ObservableListView listView = (ObservableListView) view.findViewById(R.id.scroll);
            setData(listView);

            Fragment parentFragment = getParentFragment();
            ViewGroup viewGroup = (ViewGroup) parentFragment.getView();
            if (viewGroup != null) {
                listView.setTouchInterceptionViewGroup((ViewGroup) viewGroup.findViewById(R.id.container));
                if (parentFragment instanceof ObservableScrollViewCallbacks) {
                    listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentFragment);
                }
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myIntent4 = new Intent(getActivity(), ViewingIndividualOrg.class);
                    myIntent4.putExtra("org",4);
                    myIntent4.putExtra("position",position);
                    startActivity(myIntent4);
                }
            });
            return view;
        }

        protected void setData(ListView listView) {
            listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, SPIRITUAL_LIST));
        }
    }
}