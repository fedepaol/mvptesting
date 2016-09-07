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

package com.whiterabbit.mvptesting;


import com.whiterabbit.mvptesting.inject.ApplicationModule;
import com.whiterabbit.mvptesting.main.MainModule;
import com.whiterabbit.mvptesting.main.MainView;

public class TestMvpApplication extends MvpApplication {
    private MainModule mMainModule;
    private ApplicationModule mApplicationModule;

    // By usint this two method we can drive whatever module we want during the tests
    // (and with that, drive what classes inject)
    @Override
    public MainModule getMainModule(MainView view) {
        if (mMainModule == null)
            return super.getMainModule(view);
        return mMainModule;
    }

    @Override
    ApplicationModule getApplicationModule() {
        if (mApplicationModule == null)
            return super.getApplicationModule();
        return mApplicationModule;
    }

    public void setApplicationModule(ApplicationModule m) {
        mApplicationModule = m;
    }

    public void setMainModule(MainModule m) {
        mMainModule = m;
    }
}
