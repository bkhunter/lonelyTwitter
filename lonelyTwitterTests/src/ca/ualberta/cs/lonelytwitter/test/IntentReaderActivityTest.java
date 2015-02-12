package ca.ualberta.cs.lonelytwitter.test;

import ca.ualberta.cs.lonelytwitter.IntentReaderActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.lonelytwitter.R;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;




public class IntentReaderActivityTest extends
		ActivityInstrumentationTestCase2<IntentReaderActivity>
{

	public IntentReaderActivityTest()
	{

		super(IntentReaderActivity.class);
	}

	protected void setUp() throws Exception
	{

		super.setUp();
	}
	
	public void testSendText() {
		String text = "Hello world";
		IntentReaderActivity activity = startWithText(text, IntentReaderActivity.NORMAL);
		assertEquals("got the text?", text, activity.getText());
	}
	
	public void testDoubleText() {
		String s = "Hello World";
		IntentReaderActivity activity = startWithText(s, IntentReaderActivity.DOUBLE);
		assertEquals("double text?", s+s, activity.getText());
	}
	
	public void testDisplayText() {
		String text = "Hello";
		IntentReaderActivity activity = startWithText(text, IntentReaderActivity.NORMAL);
		
		TextView view = (TextView) activity.findViewById(R.id.intentText);
		assertEquals("Correct Text?", text, view.getText());
		
	}
	
	public void testReverseTest() {
		String forwards = "Hello";
		String backwards = "olleH";
		
		IntentReaderActivity activity = startWithText(forwards, IntentReaderActivity.REVERSE);

		//IntentReaderActivity activity2 = startWithText(backwards, IntentReaderActivity.REVERSE);
		
		assertEquals("Not the reversed string", backwards, activity.getText());
		//assertEquals("Not the original string", forwards, activity2.getText());

	}
	
	//tests that the creation of an activity with no text
	public void testNoText() {
		IntentReaderActivity activity = startWithoutText(IntentReaderActivity.NORMAL);
		assertEquals("Uses defualt text", null, activity.getText());
	}
	//test that the message is displayed
	public void testErrorMessage() {
		IntentReaderActivity activity = startWithoutText(IntentReaderActivity.NORMAL);
		View view = activity.getWindow().getDecorView();
		//TextView view1 = f(R.id.intentText);
		//assertOnScreen
	}
	
	public IntentReaderActivity startWithoutText(int mode) {
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, mode);
		setActivityIntent(intent);
		return (IntentReaderActivity) getActivity();
	}
	
	private IntentReaderActivity startWithText(String text, int mode) {
		
		Intent intent = new Intent();
		intent.putExtra(IntentReaderActivity.TEXT_KEY, text);
		intent.putExtra(IntentReaderActivity.TRANSFORM_KEY, mode);
		setActivityIntent(intent);
		return (IntentReaderActivity) getActivity();
	}

}
