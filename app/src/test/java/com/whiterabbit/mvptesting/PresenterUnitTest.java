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


import com.whiterabbit.mvptesting.main.MainPresenterImpl;
import com.whiterabbit.mvptesting.main.MainView;
import com.whiterabbit.mvptesting.storage.KeyValueStorage;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PresenterUnitTest {
    private MainView mMockView;
    private KeyValueStorage mMockStorage;
    private MainPresenterImpl mToTest;

    @Before
    public void setup() {
        mMockView = mock(MainView.class);
        mMockStorage = mock(KeyValueStorage.class);
        mToTest = new MainPresenterImpl(mMockView, mMockStorage);
    }

    @Test
    public void testSequence() {
        when(mMockStorage.getSequence()).thenReturn(Long.valueOf(2));
        mToTest.updateAndStoreSequence();
        verify(mMockStorage).saveSequence(3);
    }

    @Test
    public void testResume() {
        when(mMockStorage.getSequence()).thenReturn(Long.valueOf(2));
        mToTest.onResume();
        verify(mMockView).showValue("2");
    }

    @Test
    public void testButtonClick() {
        when(mMockStorage.getSequence()).thenReturn(Long.valueOf(23));
        mToTest.onButtonClicked();
        verify(mMockView).showValue("24");
        verify(mMockStorage).saveSequence(24L);
    }

}
