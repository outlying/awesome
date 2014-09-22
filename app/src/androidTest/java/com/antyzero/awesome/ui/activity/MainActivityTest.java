package com.antyzero.awesome.ui.activity;

import android.test.ActivityInstrumentationTestCase2;

import static org.fest.assertions.api.ANDROID.assertThat;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super( MainActivity.class );
    }

    public void testCreation() throws Exception {
        assertThat(getActivity()).isNotNull();
    }
}