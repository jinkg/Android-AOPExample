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

import java.util.concurrent.TimeUnit;

/**
 * @author jinyalin
 * @since 2017/3/31.
 */

public class StopWatch {
    private long mStartTime;
    private long mEndTime;
    private long mElapsedTime;

    public StopWatch() {
    }

    private void reset() {
        mStartTime = 0;
        mEndTime = 0;
        mElapsedTime = 0;
    }

    public void start() {
        reset();
        mStartTime = System.nanoTime();
    }

    public void stop() {
        if (mStartTime != 0) {
            mEndTime = System.nanoTime();
            mElapsedTime = mEndTime - mStartTime;
        } else {
            reset();
        }
    }

    public long getTotalTimeMillis() {
        return (mElapsedTime != 0) ? TimeUnit.NANOSECONDS.toMillis(mEndTime - mStartTime) : 0;
    }
}
