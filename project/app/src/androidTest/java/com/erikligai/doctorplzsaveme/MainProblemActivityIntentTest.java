package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Activities.MainProblemActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainProblemActivityIntentTest {

    @Rule
    public ActivityTestRule<MainProblemActivity> mActivityRule
            = new ActivityTestRule<>(MainProblemActivity.class);


}
