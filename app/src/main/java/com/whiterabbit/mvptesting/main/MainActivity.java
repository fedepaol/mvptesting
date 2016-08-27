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

package com.whiterabbit.mvptesting.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.whiterabbit.mvptesting.MvpApplication;
import com.whiterabbit.mvptesting.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    @Inject
    MainPresenter mPresenter;

    @Bind(R.id.main_text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MvpApplication app = (MvpApplication) getApplication();

        DaggerMainComponent.builder()
                .applicationComponent(app.getComponent())
                .mainModule(app.getMainModule(this))
                .build().inject(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void showValue(String value) {
        mText.setText(value);
    }

    @OnClick(R.id.main_button)
    public void onButtonClicked() {
        mPresenter.onButtonClicked();
    }
}
