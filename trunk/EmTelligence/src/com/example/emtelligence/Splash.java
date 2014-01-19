package com.example.emtelligence;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
 
public class Splash extends Activity {
 
	private static String TAG = Splash.class.getName();
	private static long SLEEP_TIME = 5;    // Sleep for some time
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar
	 
	    setContentView(R.layout.splashscreen);
	    
	    TextView txt1 = (TextView)findViewById(R.id.title4);  
	    TextView txt2 = (TextView)findViewById(R.id.title3);  

		Typeface mFont = Typeface.createFromAsset(getAssets(), "Lucida Handwriting.ttf");		
		txt1.setTypeface(mFont);
		txt2.setTypeface(mFont);

		// Start timer and launch main activity
		IntentLauncher launcher = new IntentLauncher();
		launcher.start();
	}
 
   private class IntentLauncher extends Thread {
      @Override
      /**
       * Sleep for some time and than start new activity.
       */
      public void run() {
         try {
            // Sleeping
            Thread.sleep(SLEEP_TIME*1000);
         } catch (Exception e) {
            Log.e(TAG, e.getMessage());
         }
 
         // Start main activity
         Intent intent = new Intent(Splash.this, MainActivity.class);
         Splash.this.startActivity(intent);
         Splash.this.finish();
      }
   }
}