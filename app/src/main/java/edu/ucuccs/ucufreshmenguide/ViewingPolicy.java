package edu.ucuccs.ucufreshmenguide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ViewingPolicy extends ActionBarActivity {
	TextView txtPolicy;
    Toolbar mToolbar;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.policy_viewing);
        mToolbar = (Toolbar) findViewById(R.id.toolbar1);
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
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.primir)));
			}
			if (index == 1) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.vmgo)));
			}
			if (index == 2) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.seal)));
			}
			if (index == 3) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.programs)));
			}
			if (index == 4) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.admission)));
			}
			if (index == 5) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.retention)));
			}
			if (index == 6) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.registrationProcedures)));
			}
			if (index == 7) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy
						.setText(Html
								.fromHtml(getString(R.string.curriculumRevisionAndImplementation)));
			}
			if (index == 8) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy
						.setText(Html
								.fromHtml(getString(R.string.classificationOfstudents)));
			}
			if (index == 9) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.scholarshipAndGrants)));
			}
			if (index == 10) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.schoolTerms)));
			}
			if (index == 11) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy
						.setText(Html.fromHtml(getString(R.string.classHours)));
			}
			if (index == 12) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.academicLoad)));
			}
			if (index == 13) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.gradingSystem)));
			}
			if (index == 14) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.graduationRequirements)));
			}
			if (index == 15) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.citationsAwards)));
			}
			if (index == 16) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.schoolCridentials)));
			}
			if (index == 17) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy
						.setText(Html
								.fromHtml(getString(R.string.tuitionAndMiscellaneousFees)));
			}
			if (index == 18) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy
						.setText(Html
								.fromHtml(getString(R.string.academicFreedomAsTheRightOfAnIndividualStudent)));
			}
			if (index == 19) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy
						.setText(Html
								.fromHtml(getString(R.string.dutiesAndResponsibilitiesOfStudents)));
			}
			if (index == 20) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.osa)));
			}
			if (index == 21) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.guidance)));
			}
			if (index == 22) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.library)));
			}
			if (index == 23) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.multimediaLibrary)));
			}
			if (index == 24) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.audioVisualRoom)));
			}
			if (index == 25) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.laboratories)));
			}
			if (index == 26) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.enhancementServices)));
			}
			if (index == 27) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.sportsDevelopment)));
			}
			if (index == 28) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.medical)));
			}
			if (index == 29) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.security)));
			}
			if (index == 30) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy
						.setText(Html.fromHtml(getString(R.string.janitorial)));
			}
			if (index == 31) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.canteen)));
			}
			if (index == 32) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy
						.setText(Html.fromHtml(getString(R.string.definition)));
			}
			if (index == 33) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.recognition)));
			}
			if (index == 34) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.printingAndCirculation)));
			}
			if (index == 35) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html.fromHtml(getString(R.string.editorial)));
			}
			if (index == 36) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.generalPolicies)));
			}
			if (index == 37) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.specificPolicies)));
			}
			if (index == 38) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.generalPolicy)));
			}
			if (index == 39) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.studentDiscipline)));
			}
			if (index == 40) {
				TextView txtPolicy = (TextView) findViewById(R.id.txtpolicy);
				txtPolicy.setText(Html
						.fromHtml(getString(R.string.typesOfOffenses)));
			}

		}

	}

}
