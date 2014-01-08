package info.ap.yapwg;


import info.ap.yapwg.model.*;
import info.ap.yapwg.R;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class GeneratorFragment extends Fragment {
	
	View rootView;
	
	//UI	
	Button generateButton;
	EditText passwordET;
	RatingBar pwRating;
	TextView strengthResponse;
	
	ClipData clip;
	ClipboardManager clipboardManager;
	
	//Generator
	PasswordGeneratorEngine engine;
	int pwlength = 6;
	Boolean readeble = false;
	Boolean singleHand = false;
	Boolean specialChar = false;
	Boolean legible = false;	
	int pwStrength = 2;		

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_generator, container, false);
		
		clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
		
		//Generator Engine
		engine = new PasswordGeneratorEngine();
				
		//initialize UI
		addListenerOnPasswordET();		
		addListenerOnGenerateBtn();
		addListenerOnRatingBar();
		strengthResponse = (TextView) rootView.findViewById(R.id.strengthResopnse);

		startGenerator();  //create the first password
		
		return rootView;
	}
	
	private void addListenerOnPasswordET() {
		passwordET = (EditText) rootView.findViewById(R.id.password);
		passwordET.setTextIsSelectable(true);
		passwordET.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				clip = ClipData.newPlainText("pwd", passwordET.getText());
				clipboardManager.setPrimaryClip(clip);
				Toast.makeText(getActivity().getApplicationContext(), 
                       "copied to clipboard", Toast.LENGTH_LONG).show();
			}							
		});		
	}

	public void addListenerOnGenerateBtn() {
		
		generateButton = (Button) rootView.findViewById(R.id.generateButton);		
		generateButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				startGenerator();			
			}							
		});
		
	}
	
	public void addListenerOnRatingBar(){
		pwRating = (RatingBar) rootView.findViewById(R.id.ratingBar);
		pwRating.setRating(pwStrength);
		
		pwRating.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				
				pwStrength = Math.round(rating);								
				startGenerator();				
			}			
		});
		
	}	
	
	public void startGenerator(){
		String passwordString = null;
		
		switch(pwStrength)
		{
		case 0:
			passwordString = engine.legiblePW(6, false, false, false);
			strengthResponse.setText(getString(R.string.strength0));			
			break;
		case 1:
			passwordString = engine.legiblePW(8, false, false, true);
			strengthResponse.setText(getString(R.string.strength1));	
			break;
		case 2:
			passwordString = engine.legiblePW(8, false, true, true);
			strengthResponse.setText(getString(R.string.strength2));	
			break;
		case 3:
			passwordString = engine.legiblePW(10, false, true, true);
			strengthResponse.setText(getString(R.string.strength3));	
			break;
		case 4:
			passwordString = engine.simplePW(10, true, true, true);
			strengthResponse.setText(getString(R.string.strength4));	
			break;
		case 5:
			passwordString = engine.simplePW(12, true, true, true);
			strengthResponse.setText(getString(R.string.strength5));	
			break;
		default:
			passwordString ="foobar";
			strengthResponse.setText("foobar");			
		}
		passwordET.setText(String.format("%s", passwordString));
		
	}	
	
}
