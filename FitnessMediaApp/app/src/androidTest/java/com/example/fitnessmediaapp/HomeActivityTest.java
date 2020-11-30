package com.example.fitnessmediaapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class HomeActivityTest {

    @Rule
    //Enables launching of the activity
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);
    private HomeActivity mActivity = null;
    Instrumentation.ActivityMonitor monitorSteps = getInstrumentation().addMonitor(StepCounter.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorPosts = getInstrumentation().addMonitor(PostsActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorSettings = getInstrumentation().addMonitor(AccountSettings.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorExercises = getInstrumentation().addMonitor(ExerciseListMain.class.getName(), null, false);



    @Before
    public void setUp() throws Exception {
        //gets the context of the launched activity
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfStepsFromHomeActivity(){
        assertNotNull(mActivity.findViewById(R.id.stepsBtn));
        onView(withId(R.id.stepsBtn)).perform(click());
        Activity stepsActivity = getInstrumentation().waitForMonitorWithTimeout(monitorSteps, 5000);
        assertNotNull(stepsActivity);
    }

    @Test
    public void testLaunchOfPostsFromHomeActivity(){
        assertNotNull(mActivity.findViewById(R.id.socialBtn));
        onView(withId(R.id.socialBtn)).perform(click());
        Activity postActivityTest = getInstrumentation().waitForMonitorWithTimeout(monitorPosts, 5000);
        assertNotNull(postActivityTest);
    }

    @Test
    public void testLaunchOfAccountSettingsFromHomeActivity(){
        assertNotNull(mActivity.findViewById(R.id.accountBtn));
        onView(withId(R.id.accountBtn)).perform(click());
        Activity AccountActivityTest = getInstrumentation().waitForMonitorWithTimeout(monitorSettings, 5000);
        assertNotNull(AccountActivityTest);
    }

    @Test
    public void testLaunchOfExerciseListFromHomeActivity(){
        assertNotNull(mActivity.findViewById(R.id.listBtn));
        onView(withId(R.id.listBtn)).perform(click());
        Activity ExercisesActivityTest = getInstrumentation().waitForMonitorWithTimeout(monitorExercises, 5000);
        assertNotNull(ExercisesActivityTest);
    }



    @Test
    //Tests that the activity actually launches successfully by references a UI element and asserting that it is initialized
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.stepsBtn);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        //we return the variable to Null state
        mActivity = null;
    }
}