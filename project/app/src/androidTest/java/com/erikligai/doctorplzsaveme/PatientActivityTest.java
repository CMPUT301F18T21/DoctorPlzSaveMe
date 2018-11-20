package com.erikligai.doctorplzsaveme;



import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
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
    




}
