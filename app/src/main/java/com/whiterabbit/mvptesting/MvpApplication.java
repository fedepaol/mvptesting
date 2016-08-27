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

import android.app.Application;

import com.whiterabbit.mvptesting.inject.ApplicationComponent;
import com.whiterabbit.mvptesting.inject.ApplicationModule;
import com.whiterabbit.mvptesting.inject.DaggerApplicationComponent;
import com.whiterabbit.mvptesting.main.MainModule;
import com.whiterabbit.mvptesting.main.MainView;

public class MvpApplication extends Application {
    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    ApplicationModule getApplicationModule() {
        return new ApplicationModule(this);
    }

    void initComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(getApplicationModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

    public MainModule getMainModule(MainView view) {
        return new MainModule(view);
    }
}
