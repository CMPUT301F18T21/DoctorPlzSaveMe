package com.erikligai.doctorplzsaveme;



import com.erikligai.doctorplzsaveme.Activities.PatientActivity;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@SmallTest
public class PatientActivityTest {

    @Rule
    public ActivityTestRule<PatientActivity> PatientActivityRule
            = new ActivityTestRule<>(PatientActivity.class);

    @Test
    public void buttonIsClickable() {
        onView(withId(R.id.viewLocationButton)).check(matches(isClickable()));
        onView(withId(R.id.viewProblemsButton)).check(matches(isClickable()));
        onView(withId(R.id.editProfileButton)).check(matches(isClickable()));
        onView(withId(R.id.uploadBodyLocationButton)).check(matches(isClickable()));
    }

    @Test
    public void buttonClick(){
        onView(withId(R.id.viewLocationButton)).perform(click());
        pressBack();
        onView(withId(R.id.viewProblemsButton)).perform(click());
        pressBack();
        onView(withId(R.id.editProfileButton)).perform(click());
        pressBack();
        //onView(withId(R.id.uploadBodyLocationButton)).perform(click());
        //pressBack();
    }

    public static void pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
    }





}
