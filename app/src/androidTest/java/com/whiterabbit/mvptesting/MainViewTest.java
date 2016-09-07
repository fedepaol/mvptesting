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

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.whiterabbit.mvptesting.main.MainActivity;
import com.whiterabbit.mvptesting.main.MainModule;
import com.whiterabbit.mvptesting.main.MainPresenter;
import com.whiterabbit.mvptesting.main.MainView;
import com.whiterabbit.mvptesting.storage.KeyValueStorage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainViewTest {
    private MainPresenter mMockPresenter;

    @Rule
    public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() throws Exception {

        // a mock module with the mock presenter to be injected..
        MainModule m = mock(MainModule.class);
        mMockPresenter = mock(MainPresenter.class);

        when(m.provideMainView()).thenReturn(mock(MainView.class)); // this is needed to fool dagger
        when(m.provideMainPresenter(any(MainView.class), any(KeyValueStorage.class)))
                .thenReturn(mMockPresenter);

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        TestMvpApplication app
                = (TestMvpApplication) instrumentation.getTargetContext().getApplicationContext();

        // forced to the application object
        app.setMainModule(m);
    }

    @Test
    public void testButtonClick() {
        activity.launchActivity(new Intent());
        onView(withId(R.id.main_button)).perform(click());
        verify(mMockPresenter).onButtonClicked();
    }

    @Test
    public void testChangeData() {
        activity.launchActivity(new Intent());
        activity.getActivity().runOnUiThread(new Runnable() {
                                                 @Override
                                                 public void run() {
                                                     activity.getActivity().showValue("23");
                                                 }
                                             });

        onView(withId(R.id.main_text)).check(matches(withText("23")));
    }
}
