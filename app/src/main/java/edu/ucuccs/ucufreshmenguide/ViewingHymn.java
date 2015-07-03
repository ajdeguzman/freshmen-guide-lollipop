package edu.ucuccs.ucufreshmenguide;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class ViewingHymn extends Activity {
	//This is a sample comment for Git Tutorial
	//Another changes example
	VideoView video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hymn_viewing);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.myPrimaryDarkColor));
        }

		video = (VideoView) findViewById(R.id.video);
		video.setVideoPath("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.hymn);
		video.setKeepScreenOn(true);
		video.start();
		MediaController myController = new MediaController(this);

		video.setMediaController(myController);
		myController.show();
	}

}
