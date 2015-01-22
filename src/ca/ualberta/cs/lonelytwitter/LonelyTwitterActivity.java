package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.renderscript.Type;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<String> tweets;
	private ArrayAdapter<String> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				tweets.add(text);
				adapter.notifyDataSetChanged();
				saveInFile(text, new Date(System.currentTimeMillis()));
				//finish();
				//throw new RuntimeException("test error");

			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		
		super.onStart();
		
		User u = new Author(); // uses the default constructor to create a new object
		Friendship f = new Friend();
		
		/* could also do Object u = new Author(); for line above
		However the line below doesn't work anymore*/
		
		//type = an array of users 
		ArrayList<User> array = new ArrayList<User>();
		
		try {
			u.setUsername("joe"); // calls the set user-name code described in the author sub-class
		} catch (IOException e) { // could also use catch (exception e), but that's novice
			
		}
		
		tweets = loadFromFile();
		adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private ArrayList<String> loadFromFile() {
		Gson gson = new Gson();
		ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			//taken from http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html on 01/22/2015
			
			java.lang.reflect.Type ListType = new TypeToken<ArrayList<String>>() {}.getType();
			InputStreamReader isr = new InputStreamReader(fis);
			
			tweets = gson.fromJson(isr,ListType);
			fis.close();
			
//			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//			String line = in.readLine();
//			while (line != null) {
//				tweets.add(line);
//				line = in.readLine();
//			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tweets == null) {
			tweets = new ArrayList<String>();
		}
		return tweets;
				//tweets.toArray(new String[tweets.size()]);
	}
	
	private void saveInFile(String text, Date date) {
		Gson gson = new Gson();
		
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0); 	//this is the default mode --> will lose data --> replaced Context.MODE_APPEND);
			
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(tweets, osw); //replaces fos.write(new String(date.toString() + " | " + text)
							 		  //		.getBytes());
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}