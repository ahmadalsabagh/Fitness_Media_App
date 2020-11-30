package com.example.fitnessmediaapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountSettingsTest {

    @Rule
    //Enables launching of the activity
    public ActivityTestRule<AccountSettings> mActivityTestRule = new ActivityTestRule<AccountSettings>(AccountSettings.class);

    // a reference to the activity
    private AccountSettings mActivity = null;

    @Before
    public void setUp() throws Exception {
        //gets the context of the launched activity
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    //Tests that the activity actually launches successfully by references a UI element and asserting that it is initialized
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.updateFirstNameBtn);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        //we return the variable to Null state
        mActivity = null;
    }
}