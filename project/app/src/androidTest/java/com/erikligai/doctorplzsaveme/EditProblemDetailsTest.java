// USE CASE 3
//RecyclerView not working yet
package com.erikligai.doctorplzsaveme;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

public class EditProblemDetailsTest {
    @Rule
    public IntentsTestRule<MainProblemActivity> intentsTestRule =
            new IntentsTestRule<>(MainProblemActivity.class);

    @Test
    public void testIntent() {
//        onView(ViewMatchers.withId(R.id.problems_recyclerview))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
}
