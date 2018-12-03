package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Activities.EditProfileActivity;
import com.erikligai.doctorplzsaveme.Activities.MainProblemActivity;
import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.Activities.UploadBodyLocationActivity;
import com.erikligai.doctorplzsaveme.Activities.ViewRecordLocationsActivity;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.StartAppActivities.MainActivity;
import com.erikligai.doctorplzsaveme.backend.Backend;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PatientProcedureIntentTest {

    //Backend.getInstance().setPatientProfile(new Patient("testID","testEmail","123-456-7890"));
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void selectPatientTest() {
        // Type text and then press the button.
        onView(withId(R.id.patient_button)).perform(click());
        intended(hasComponent(PatientActivity.class.getName()));
    }

    @Test
    public void testLogin(){

    }

    @Test
    public void patientActivityTest() {
        // Test the 4 buttons
        onView(withId(R.id.patient_button)).perform(click());

        onView(withId(R.id.viewProblemsButton)).perform(click());
        intended(hasComponent(MainProblemActivity.class.getName()));
        pressBack();

        onView(withId(R.id.viewLocationButton)).perform(click());
        intended(hasComponent(ViewRecordLocationsActivity.class.getName()));
        pressBack();

        onView(withId(R.id.editProfileButton)).perform(click());
        intended(hasComponent(EditProfileActivity.class.getName()));
        pressBack();

        onView(withId(R.id.uploadBodyLocationButton)).perform(click());
        intended(hasComponent(UploadBodyLocationActivity.class.getName()));
        pressBack();
    }
}
