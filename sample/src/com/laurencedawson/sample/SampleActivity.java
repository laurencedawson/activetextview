/*
 * Copyright 2012 Laurence Dawson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
