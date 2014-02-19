package edu.pitt.cs1635.mac250.postagecalculator;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity {
	
	public boolean radioButtonChecked = false;
	public boolean letterIsChecked = false;
	public boolean envelopeIsChecked = false;
	public boolean packageIsChecked = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button calculate = (Button) findViewById(R.id.button_calculate);
		calculate.setEnabled(false);
		
		calculate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calculatePostage(v);
			}
		});
		
		EditText text = (EditText) findViewById(R.id.textfield_weight);
		text.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE ) {
					calculate.setEnabled(true);
				}
				return false;
			}
		});
		
		Button regulations = (Button) findViewById(R.id.button_regulations);
		regulations.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showRegulations(v);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void calculatePostage(View view) {
		if (checkRadioButtonSelected() && checkWeightInput()) {
			double postage = calculatePostageValue();
			displayResultAlert(postage);
		}
	}
	
	private boolean checkRadioButtonSelected() {
		boolean result = false;
		
		RadioButton letter = (RadioButton) findViewById(R.id.radiobutton_letter);
		RadioButton envelope = (RadioButton) findViewById(R.id.radiobutton_envelope);
		RadioButton pckg = (RadioButton) findViewById(R.id.radiobutton_package);
		
		if (letter.isChecked() || envelope.isChecked() || pckg.isChecked()) {
			result = true;
		}
		
		if (letter.isChecked()) {
			letterIsChecked = true; envelopeIsChecked = false; packageIsChecked =false;
		} else if (envelope.isChecked()) {
			letterIsChecked = false; envelopeIsChecked = true; packageIsChecked =false;
		} else if (pckg.isChecked()) {
			letterIsChecked = false; envelopeIsChecked = false; packageIsChecked =true;
		}
		return result;
	}
	
	private boolean checkWeightInput() {
		boolean result = false;
		EditText weight = (EditText) findViewById(R.id.textfield_weight);
		String weightString = weight.getText().toString();
		
		double weightDouble = Double.parseDouble(weightString);
		if(weightDouble < 13.1 && weightDouble > 0) {
			result = true;
		} else {
			displayInputAlert();
			result = false;
		}
		
		if (letterIsChecked && weightDouble > 3.5) {
			displayLetterInputAlert();
			result = false;
		}
		return result;
	}
	
	private void displayInputAlert() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Weight Issue");
		alert.setMessage("The weight you entered is not valid. \n\n Please enter a weight between 0 and 13 ounces.");
		alert.setNeutralButton("got it", null);
		alert.show();
	}
	
	private double calculatePostageValue() {
		double result = 0;
		EditText weight = (EditText) findViewById(R.id.textfield_weight);
		String weightString = weight.getText().toString();
		double weightDouble = Double.parseDouble(weightString);
		int weightInt = (int)(weightDouble - 1);
		if (letterIsChecked) {
			if (weightDouble > 3) {
				weightInt = 3;
			}
			double[] price = {0.46, 0.66, 0.86, 1.06};
			result = price[weightInt];
		} else if (envelopeIsChecked){
			double[] price = {0.92, 1.12, 1.32, 1.52, 1.72, 1.92, 2.12, 2.32, 2.52, 2.72, 2.92, 3.12, 3.32};
			result = price[weightInt];
		} else if (packageIsChecked){
			double[] price = {2.07, 2.07, 2.07, 2.24, 2.41, 2.58, 2.75, 2.92, 3.09, 3.26, 3.43, 3.6, 3.77};
			result = price[weightInt];
		}
		return result;
	}
	
	private void displayResultAlert(Double price) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Cost to Send Package");
		alert.setMessage("Price: $" + price.toString());
		alert.setNeutralButton("cool, thanks", null);
		alert.show();
	}
	
	private void displayLetterInputAlert() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Letter too Heavy");
		alert.setMessage("Letter must be less than 3.5 Oz.");
		alert.setNeutralButton("got it", null);
		alert.show();
	}
	
	private void showRegulations(View v) {
		Intent intent = new Intent(this, RegulationsActivity.class);
		startActivity(intent);
	}
}
