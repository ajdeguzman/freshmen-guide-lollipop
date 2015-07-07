package edu.ucuccs.ucufreshmenguide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import edu.gestureimageview.GestureImageView;

public class ChartViewing extends BaseActivity {
    Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewing);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.myPrimaryDarkColor));
        }

		int index;
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			index = bundle.getInt("index_key");

			if (index == 0) {
				GestureImageView imgChart = (GestureImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.academiccouncil));
			}
			if (index == 1) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.administrativeservices));
			}
			if (index == 2) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.osa));
			}
			if (index == 3) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.developmentcenter));
			}
			if (index == 4) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.hrmo));
			}
			if (index == 5) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.boardofregents));
			}
			if (index == 6) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.accounting));
			}
			if (index == 7) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.cashier));
			}
			if (index == 8) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.research));
			}
			if (index == 9) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.alumni));
			}
			if (index == 10) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.clinic));
			}
			if (index == 11) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.graduateschool));
			}
			if (index == 12) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.sportculturearts));
			}
			if (index == 13) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.ccs));
			}
			if (index == 14) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.blis));
			}
			if (index == 15) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.caba));
			}
			if (index == 16) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.artsandlanguages));
			}
			if (index == 17) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.nursing));
			}
			if (index == 18) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.pharmacy));
			}
			if (index == 19) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.scienceandmathematics));
			}
			if (index == 20) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.socialwork));
			}
			if (index == 21) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.criminology));
			}
			if (index == 22) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.education));
			}
			if (index == 23) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.engg));
			}
			if (index == 24) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.midwifery));
			}
			if (index == 25) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.polsci));
			}
			if (index == 26) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.psychology));
			}
			if (index == 27) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.tourism));
			}
			if (index == 28) {
				ImageView imgChart = (ImageView) findViewById(R.id.imgChart);
				imgChart.setImageDrawable(getResources().getDrawable(
						R.drawable.hrm));
			}

		}

	}

}
