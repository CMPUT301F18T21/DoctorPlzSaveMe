package com.erikligai.doctorplzsaveme;

import android.provider.MediaStore;

import com.erikligai.doctorplzsaveme.Activities.MainProblemActivity;
import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.Activities.UploadBodyLocationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class UploadBodyLocationActivityIntentTest {
    @Rule
    public ActivityTestRule<UploadBodyLocationActivity> mActivityRule
            = new ActivityTestRule<>(UploadBodyLocationActivity.class);

    @Test
    public void buttonTest() {
        onView(withId(R.id.viewProblemsButton)).perform(click());
        intended(hasAction(MediaStore.ACTION_IMAGE_CAPTURE));
        pressBack();
    }
}
