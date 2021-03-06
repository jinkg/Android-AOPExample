/*
 * Copyright 2017 Yalin Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yalin.aopexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yalin.aspect.App;
import com.yalin.aspect.annotation.DebugTrace;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aop_example_activity_main);

        App.setConsole((TextView) findViewById(R.id.console));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        App.removeConsole();
    }

    public void refresh(View view) {
        findViewById(R.id.invalidate).invalidate();
    }

    public void test(View view) {
        testCalculate();
    }

    @DebugTrace
    private void testCalculate() {
        long total = 0;
        for (int i = 0; i < 100000; i++) {
            total += i;
        }
        Log.d(TAG, "testCalculate: " + total);
    }
}
