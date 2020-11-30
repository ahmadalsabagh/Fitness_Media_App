package com.example.fitnessmediaapp;

import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class OpeningScreenTest {

    @Rule
    //Enables launching of the activity
    public ActivityTestRule<OpeningScreen> mActivityTestRule = new ActivityTestRule<OpeningScreen>(OpeningScreen.class);
    private OpeningScreen mActivity = null;

    @Before
    public void setUp() throws Exception {
        //gets the context of the launched activity
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    //Tests that the activity actually launches successfully by references a UI element and asserting that it is initialized
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.loginBtn);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        //we return the variable to Null state
        mActivity = null;
    }
}