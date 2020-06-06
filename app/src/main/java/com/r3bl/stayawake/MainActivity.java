/*
 * Copyright 2020 R3BL LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.r3bl.stayawake;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.util.LinkifyCompat;

import com.r3bl.stayawake.database.Task;
import com.r3bl.stayawake.database.ToDoDatabase;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "SA_MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyTileService.coldStart(this, false);
        TextView textTweetData = findViewById(R.id.tweet_data);
        AppExecutors appExecutors = new AppExecutors();
        appExecutors.diskIO().execute(() -> {

            List<Task> tasksList = ToDoDatabase.getInstance(MainActivity.this).taskDao().getTasks();
            if (tasksList.isEmpty()) {
                textTweetData.setText("Nothing fetched from the url");
            } else {

                for (Task task
                        : tasksList
                ) {
                    textTweetData.setText(task.getContent());
                }
            }


        });
    }


    public void buttonStartAwakeClicked(View view) {
        MyTileService.coldStart(this, true);
    }
}
