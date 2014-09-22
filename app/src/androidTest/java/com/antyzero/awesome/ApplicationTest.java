package com.antyzero.awesome;

import android.test.ApplicationTestCase;

/**
 *
 */
public class ApplicationTest extends ApplicationTestCase<AwesomeApplication> {

    public ApplicationTest() {
        super(AwesomeApplication.class);
    }

    public void testCreation(){
        createApplication();
    }
}