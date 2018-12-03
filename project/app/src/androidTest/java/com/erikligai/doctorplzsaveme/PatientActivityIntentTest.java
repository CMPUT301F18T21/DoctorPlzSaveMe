package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Activities.EditProfileActivity;
import com.erikligai.doctorplzsaveme.Activities.MainProblemActivity;
import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.Activities.UploadBodyLocationActivity;
import com.erikligai.doctorplzsaveme.Activities.ViewRecordLocationsActivity;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.backend.Backend;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class PatientActivityIntentTest {

    @Rule
    public ActivityTestRule<PatientActivity> mActivityRule
            = new ActivityTestRule<>(PatientActivity.class);

    @Test
    public void buttonTest() {
        // Type text and then press the button.
        onView(withId(R.id.viewProblemsButton)).perform(click());
        intended(hasComponent(MainProblemActivity.class.getName()));
        pressBack();

        onView(withId(R.id.editProfileButton)).perform(click());
        intended(hasComponent(EditProfileActivity.class.getName()));
        pressBack();

        onView(withId(R.id.viewLocationButton)).perform(click());
        intended(hasComponent(ViewRecordLocationsActivity.class.getName()));
        pressBack();

        onView(withId(R.id.uploadBodyLocationButton)).perform(click());
        intended(hasComponent(UploadBodyLocationActivity.class.getName()));
        pressBack();
    }
}



