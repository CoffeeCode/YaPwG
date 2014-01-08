package info.ap.yapwg;

import info.ap.yapwg.model.*;
import info.ap.yapwg.R;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class AdvGeneratorFragment extends Fragment {
	
	View rootView;
	
	//UI
	Button generateButton;
	EditText passwordET2;
	NumberPicker pwLengthPicker;
	Switch charSwitch;
	Switch charUpperSwitch;
	Switch numberSwitch;
	Switch symbolSwitch;
	
	ClipData clip2;
	ClipboardManager clipboardManager2;
		
	//Generator and configuration
	PasswordGeneratorEngine engine;
	Boolean chars;
	Boolean charsUpper = false;
	Boolean numbers = false;
	Boolean symbols = false;
	int passwordLength = 8;	
	String passwordString;		
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_advgenerator, container, false);
		
		//Generator Engine
		engine = new PasswordGeneratorEngine();

		//initialize UI
		passwordET2 =(EditText) rootView.findViewById(R.id.password2);
		passwordET2.setText(String.format("%s", "foobar"));

		addListenerOnGenerateBtn();
		addListenerOnCharSwitch();
		addListenerOnCharUpperSwitch();
		addListenerOnNumbersSwitch();
		addListenerOnSymbolsSwitch();	
		addListenerOnNumberPicker();
		addListenerOnPasswordET();;
				
		startGenerator();  //create the first password	
		
		return rootView;
	}
	
	private void addListenerOnPasswordET() {
		passwordET2 = (EditText) rootView.findViewById(R.id.password2);
		passwordET2.setTextIsSelectable(true);
		passwordET2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				clip2 = ClipData.newPlainText("pwd2", passwordET2.getText());
				//clipboardManager2.setPrimaryClip(clip2);
				//Toast.makeText(getActivity().getApplicationContext(), 
                //        "copied to clipboard", Toast.LENGTH_LONG).show();
			}							
		});
	}
		
	
	private void addListenerOnNumberPicker() {
		pwLengthPicker = (NumberPicker) rootView.findViewById(R.id.numberPicker1);
		pwLengthPicker.setMaxValue(24);
		pwLengthPicker.setMinValue(1);
		pwLengthPicker.setValue(passwordLength);
		
		pwLengthPicker.setOnValueChangedListener(new OnValueChangeListener() {
			
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			// TODO Auto-generated method stub
			passwordLength = newVal;
		}
	});
	}
	
	
	private void addListenerOnSymbolsSwitch() {
		symbolSwitch = (Switch) rootView.findViewById(R.id.symbolsSwitch);
		symbolSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			   @Override
			   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				   symbols = isChecked;
			   }
		});
	}
	
	private void addListenerOnNumbersSwitch() {
		numberSwitch = (Switch) rootView.findViewById(R.id.numbersSwitch);
		numberSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			   @Override
			   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				   numbers = isChecked;
			   }
		});
	}

	private void addListenerOnCharUpperSwitch() {
		charUpperSwitch = (Switch) rootView.findViewById(R.id.charsUpperSwitch);
		charUpperSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			   @Override
			   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				   charsUpper = isChecked;
			   }
		});
	}

	private void addListenerOnCharSwitch() {
		charSwitch = (Switch) rootView.findViewById(R.id.charsSwitch);
		charSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			   @Override
			   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				   chars = isChecked;
			   }
		});
	}

	private void addListenerOnGenerateBtn() {
		generateButton = (Button) rootView.findViewById(R.id.advGenerateButton);			
		generateButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				startGenerator();			
			}							
		});
	
	}



	protected void startGenerator() {
		// TODO Auto-generated method stub
		
		passwordString = engine.simplePW(passwordLength, symbols, numbers, charsUpper);
		passwordET2.setText(String.format("%s", passwordString));		
	}	
	
}
