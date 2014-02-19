package edu.pitt.cs1635.mac250.postagecalculator;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class RegulationsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regulations);
		setupActionBar();
		
		TextView letter = (TextView) findViewById(R.id.letter_body);
		TextView envelope = (TextView) findViewById(R.id.envelope_body);
		TextView pckg = (TextView) findViewById(R.id.package_body);
		
		letter.setText("Must be rectangular.\nMinimum size is 5 inches long x 3 1/2 inches high x 0.007 inches thick.\n" +
				"Maximum size of 11 1/2 inches long x 6 1/8 inches high x 1/4 inch thick\n" +
				"Maximum weight is 3.5 oz \n" +
				"A letter will be charged a nonmachinable surcharge if it's a square letter, doesn't bend easilly, has clasps or similar closure devices, has anaddress parallel to the shorter dimension of the letter, is lumpy, orthe length divided by height is less than 1.3 or more than 2.5"
				);
		envelope.setText("Must be rectangular.\n" +
				"Minimum size is either more than 11 1/2 inches long x 6 1/8 inches high or 1/4 inch thick\n" +
				"maximum weight of 13 oz");
		pckg.setText("Maximum weight of 13 oz.\n" +
				"up to 108 inchesin combined length and girth.");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.regulations, menu);
		return true;
	}
	
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
