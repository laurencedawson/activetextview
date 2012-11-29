package com.laurencedawson.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import com.laurencedawson.activetextview.ActiveTextView;
import com.laurencedawson.activetextview.ActiveTextView.OnLinkClickedListener;
import com.laurencedawson.activetextview.ActiveTextView.OnLongPressedLinkListener;

public class SampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Create a new ActiveTextView programatically
		// You can also add it to a layout file by adding <com.laurencedawson.ActiveTextView />
		ActiveTextView activeTextView = new ActiveTextView(this);
		activeTextView.setText(Html.fromHtml("<a href=\"http://google.com\">Link to click</a> more text to follow."));

		// Set a link clicked listener (not required)
		activeTextView.setLinkClickedListener(new OnLinkClickedListener() {
			@Override
			public void onClick(String url) {
				// Decide what to do when a link is clicked.
				// (This is useful if you want to open an in app-browser)
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
		
		// Set a long pressed link listener (required if you want to show the additional 
		// options menu when links are long pressed)
		activeTextView.setLongPressedLinkListener(new OnLongPressedLinkListener() {
			@Override
			public void onLongPressed() {
				Toast.makeText(SampleActivity.this, "Long pressed", Toast.LENGTH_SHORT).show();
			}
		}, false);
		
		// Set the ActiveTextView as the content view
		setContentView(activeTextView);
	}
}
