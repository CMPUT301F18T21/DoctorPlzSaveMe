package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Activities.AddProblemActivity;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.SmallTest;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@SmallTest
public class AddProblemUseCaseTest {
    @Rule
    public IntentsTestRule<AddProblemActivity> intentsTestRule = new IntentsTestRule<>(AddProblemActivity.class);

    @Test
    public void testAddProblem(){
        onView(withId(R.id.editProblemTitle)).perform(typeText("Mole on my hand"),closeSoftKeyboard());
    }
}
