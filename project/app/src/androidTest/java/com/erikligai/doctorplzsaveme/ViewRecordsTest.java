// USE CASE 7
//RecyclerView not working yet

package com.erikligai.doctorplzsaveme;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ViewRecordsTest {
    @Rule
    public IntentsTestRule<MainProblemActivity> intentsTestRule =
            new IntentsTestRule<>(MainProblemActivity.class);

    @Test
    public void testIntent() {

    }
}
