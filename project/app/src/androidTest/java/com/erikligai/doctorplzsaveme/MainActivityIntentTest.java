package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Activities.CareProviderActivity;
import com.erikligai.doctorplzsaveme.StartAppActivities.MainActivity;
import com.erikligai.doctorplzsaveme.StartAppActivities.NewProfileActivity;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.intent.rule.IntentsTestRule;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class MainActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void checkPatientButton() {
        onView(withId(R.id.patient_button)).perform(click());
        intended(hasComponent(NewProfileActivity.class.getName()));
    }
    @Test
    public void checkCareProviderButton() {
        onView(withId(R.id.care_provider_button)).perform(click());
        intended(hasComponent(CareProviderActivity.class.getName()));
    }
}