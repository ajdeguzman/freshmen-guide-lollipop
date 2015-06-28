package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.getbase.floatingactionbutton.FloatingActionButton;
import edu.getbase.floatingactionbutton.FloatingActionsMenu;

public  class FragmentCoursesOffered extends Fragment {
    private Toolbar mToolbar;
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_fragment_courses_offered, container, false);

        rootView.findViewById(R.id.pink_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton button = (FloatingActionButton) rootView.findViewById(R.id.setter);
        button.setSize(FloatingActionButton.SIZE_MINI);
        button.setColorNormalResId(R.color.pink);
        button.setColorPressedResId(R.color.pink_pressed);
        button.setIcon(R.drawable.ic_fab_star);
        button.setStrokeVisible(false);

        final View actionB = rootView.findViewById(R.id.action_b);

        FloatingActionButton actionC = new FloatingActionButton(getActivity());
        actionC.setTitle("Hide/Show Action above");
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        ((FloatingActionsMenu) rootView.findViewById(R.id.multiple_actions)).addButton(actionC);

        final FloatingActionButton removeAction = (FloatingActionButton) rootView.findViewById(R.id.button_remove);
        removeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FloatingActionsMenu) rootView.findViewById(R.id.multiple_actions_down)).removeButton(removeAction);
            }
        });

        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));
        ((FloatingActionButton) rootView.findViewById(R.id.setter_drawable)).setIconDrawable(drawable);

        final FloatingActionButton actionA = (FloatingActionButton) rootView.findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionA.setTitle("Action A clicked");
            }
        });

        // Test that FAMs containing FABs with visibility GONE do not cause crashes
        rootView.findViewById(R.id.button_gone).setVisibility(View.GONE);

        FloatingActionsMenu rightLabels = (FloatingActionsMenu) rootView.findViewById(R.id.right_labels);
        FloatingActionButton addedOnce = new FloatingActionButton(getActivity());
        addedOnce.setTitle("Added once");
        rightLabels.addButton(addedOnce);

        FloatingActionButton addedTwice = new FloatingActionButton(getActivity());
        addedTwice.setTitle("Added twice");
        rightLabels.addButton(addedTwice);
        rightLabels.removeButton(addedTwice);
        rightLabels.addButton(addedTwice);
        return rootView;
    }


}
