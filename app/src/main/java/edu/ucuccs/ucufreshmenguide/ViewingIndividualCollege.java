package edu.ucuccs.ucufreshmenguide;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ViewingIndividualCollege extends BaseActivity implements ObservableScrollViewCallbacks {

    private View mImageView;
    private View mToolbarView;
    private TextView mDescription, mName;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_individual_college);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mImageView = findViewById(R.id.imageCover);
        mDescription = (TextView) findViewById(R.id.description);
        mName = (TextView) findViewById(R.id.name);
        mToolbarView = findViewById(R.id.toolbar);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.myPrimaryColor)));

        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.myPrimaryDarkColor));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.myPrimaryColor);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(mImageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onStart(){
        Intent myIntent = getIntent();
        int groupPosition = myIntent.getIntExtra("groupPosition", 0);
        int childPosition= myIntent.getIntExtra("childPosition", 0);
        InputStream is = null;
        BufferedReader br;


            String line = "";
            StringBuilder finalstring = new StringBuilder();

            is = getResources().openRawResource(R.raw.colleges);
            getSupportActionBar().setTitle("Colleges");

            br  = new BufferedReader(new InputStreamReader(is));
            try {
            while ((line = br.readLine()) != null) {
                finalstring.append(line);
            }
            JSONObject orgObject = new JSONObject(String.valueOf(finalstring));
            for(int i = 0; i < orgObject.length(); i++) {
                JSONObject individualOrgObject = orgObject.getJSONObject(String.valueOf(childPosition));

                String imgStr = individualOrgObject.getString("img").toString();
                String nameStr = individualOrgObject.getString("name").toString();
                String descriptionStr = getStringResourceByName(individualOrgObject.getString("description").toString());

                int imgPath = getResources().getIdentifier(imgStr, "drawable", getPackageName());
                Drawable image = getResources().getDrawable(imgPath == 0 ? R.mipmap.ucu_header : imgPath);
                mImageView.setBackground(image);
                mDescription.setText(Html.fromHtml(descriptionStr));
                mName.setText(nameStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onStart();
    }private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources()
                .getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return aString;
        } else {
            return getString(resId);
        }
    }
}
