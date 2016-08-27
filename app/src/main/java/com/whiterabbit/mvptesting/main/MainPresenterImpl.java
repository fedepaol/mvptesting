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

import com.whiterabbit.mvptesting.storage.KeyValueStorage;

public class MainPresenterImpl implements MainPresenter {
    private final MainView mView;
    private final KeyValueStorage mStorage;

    public MainPresenterImpl(MainView view, KeyValueStorage storage) {
        mView = view;
        mStorage = storage;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        updateSequence();
    }

    @Override
    public void onButtonClicked() {
        long value = mStorage.getSequence() + 1;
        mStorage.saveSequence(value);
        updateSequence();
    }

    private void updateSequence() {
        mView.showValue(String.valueOf(mStorage.getSequence()));
    }
}
