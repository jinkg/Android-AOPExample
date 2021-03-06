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

package com.yalin.aspect.internal;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.yalin.aspect.App;

/**
 * @author jinyalin
 * @since 2017/3/31.
 */

public class DebugLog {
    private DebugLog() {
    }

    public static void log(String tag, String message) {
        Log.d(tag, message);

        TextView console = App.getConsole();
        if (console == null) {
            Toast.makeText(App.getContext(), message, Toast.LENGTH_SHORT).show();
        } else {
            console.append(message);
            console.append("\n");
        }
    }
}
