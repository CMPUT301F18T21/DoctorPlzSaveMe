// USE CASE 2
//RecyclerView not working yet
package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Activities.MainProblemActivity;

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

public class ViewProblemsListTest {
    @Rule
    public IntentsTestRule<PatientActivity> intentsTestRule =
            new IntentsTestRule<>(PatientActivity.class);

    @Test
    public void testIntent() {
        onView(withId(R.id.viewProblemsButton)).perform(click());
        intended(hasComponent(MainProblemActivity.class.getName()));
        onView(withId(R.id.problems_recyclerview)).check(matches(isDisplayed()));
    }
}
