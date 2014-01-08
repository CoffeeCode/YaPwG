package info.ap.yapwg;

import info.ap.yapwg.model.*;
import info.ap.yapwg.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestFragment extends Fragment {
	
	RelativeLayout layout;
	
	View rootViewTest;
	
	PasswordGeneratorEngine engine;
	int passwordStrength = 0;
	
	EditText testPasswordET;
	String stringToTest;
	TextView score;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		rootViewTest = inflater.inflate(R.layout.fragment_test, container, false);
		//rootViewTest.getRootView().setBackgroundColor(Color.parseColor("#636161"));
		layout=(RelativeLayout) rootViewTest.findViewById(R.layout.fragment_test);
		
		//Generator Engine
		engine = new PasswordGeneratorEngine();
		
		score = (TextView) rootViewTest.findViewById(R.id.score);
		
		addListenerOnPasswordET();
		
		return rootViewTest;
	}
	
	
	
	private void addListenerOnPasswordET() {
		testPasswordET = (EditText) rootViewTest.findViewById(R.id.enterPwdET);
		//testPasswordET.setTextIsSelectable(true);
		//testPasswordET.setTextIsSelectable(false);
		testPasswordET.setFocusable(true);
		//testPasswordET.clearFocus();
		//testPasswordET.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		testPasswordET.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				startPasswordTester();
			
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub				
			
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}		
			
		});
		
		
		testPasswordET.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
			}							
		});		
	}

	protected void startPasswordTester() {
		
		stringToTest = testPasswordET.getText().toString();	
		if(stringToTest.length() > 0){
			passwordStrength = engine.pwEntropy(stringToTest);
		}
		else{
			passwordStrength = 0;
		}
			
			score.setText(String.valueOf(passwordStrength));
			if(passwordStrength == 0)
				rootViewTest.getRootView().setBackgroundColor(Color.WHITE);		
			else if(passwordStrength < 28)
				rootViewTest.getRootView().setBackgroundColor(Color.RED);
			else if(passwordStrength < 40 && passwordStrength >=28)
				rootViewTest.getRootView().setBackgroundColor(Color.MAGENTA);
			else if(passwordStrength < 60 && passwordStrength >= 45)
				rootViewTest.getRootView().setBackgroundColor(Color.YELLOW);
			else if(passwordStrength>=75)
				rootViewTest.getRootView().setBackgroundColor(Color.GREEN);
		}

}
