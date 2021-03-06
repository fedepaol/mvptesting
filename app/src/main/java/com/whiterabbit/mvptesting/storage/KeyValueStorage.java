/*
 * Copyright (C) 2016  Federico Paolinelli.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.whiterabbit.mvptesting.storage;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class KeyValueStorage {
    @Inject
    SharedPreferences mPreferences;

    @Inject
    public KeyValueStorage() {}

    private static String PREFS_SEQUENCE = "com.whiterabbit.mvp.sequence";

    public void saveSequence(long number) {
        mPreferences.edit().putLong(PREFS_SEQUENCE, number).apply();
    }

    public long getSequence() {
        return mPreferences.getLong(PREFS_SEQUENCE, 0);
    }
}
