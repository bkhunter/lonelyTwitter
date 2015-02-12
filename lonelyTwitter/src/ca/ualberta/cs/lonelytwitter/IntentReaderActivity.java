package ca.ualberta.cs.lonelytwitter;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class IntentReaderActivity extends Activity {

	public static final String TEXT_KEY = "TEXT";
	public static final String TRANSFORM_KEY = "TRANSFORM";

	public static final int NORMAL = 1;
	public static final int REVERSE = 2;
	public static final int DOUBLE = 3;
	
	private String text;
	private int mode;
	
	public String getText() {
		return text;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_reader);
		Intent intent = getIntent();
		try {
			text = intent.getStringExtra(TEXT_KEY);
		} catch (RuntimeException e) {
		} 
		if (text == null) {
			mode = intent.getIntExtra(TRANSFORM_KEY,NORMAL);
			TextView view = (TextView) findViewById(R.id.intentText);
			view.setText(" Error No String Passed To Intent (ENSPTI)");
		} else {
		
			mode = intent.getIntExtra(TRANSFORM_KEY,NORMAL);
			text = transformText(text);
			TextView view = (TextView) findViewById(R.id.intentText);
			view.setText(text);
		}
	}
	
	public String transformText(String text) {
		switch (mode) {
			case REVERSE:
				char[] string =  text.toCharArray();
				for (int i = 0; i < string.length / 2; i++) {
					char tmp = string[i];
					string[i] = string[string.length - i -1];
					string[string.length - i - 1] = tmp;
				}
				return new String(string);
			case DOUBLE:
				return text + text;
		}
		return text;
	}
}