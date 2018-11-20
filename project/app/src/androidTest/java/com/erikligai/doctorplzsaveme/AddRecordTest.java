// USE CASE 5
//RecyclerView not working yet
package com.erikligai.doctorplzsaveme;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AddRecordTest {
    @Rule
    public IntentsTestRule<MainRecordActivity> intentsTestRule =
            new IntentsTestRule<>(MainRecordActivity.class);

    @Test
    public void testIntent() {
        onView(withId(R.id.record_fab)).perform(click());
        intended(hasComponent(AddRecordActivity.class.getName()));
    }
}
