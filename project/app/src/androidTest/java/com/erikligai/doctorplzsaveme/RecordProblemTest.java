// USE CASE 1
// Date is set to 'now' for now

package com.erikligai.doctorplzsaveme;

import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class RecordProblemTest {
    String title = "Test Problem";
    String desc = "Test Desc";
    @Rule
    public IntentsTestRule<MainProblemActivity> intentsTestRule =
            new IntentsTestRule<>(MainProblemActivity.class);

    @Test
    public void testIntent() {
        onView(withId(R.id.fab)).perform(click());
        intended(hasComponent(AddProblemActivity.class.getName()));
    }

    @Test
    public void recordProblem() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.editProblemTitle)).perform(typeText(title));
        onView(withId(R.id.editProblemDescription)).perform(typeText(desc),closeSoftKeyboard());
        //onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
    }

    //code from https://stackoverflow.com/questions/24748303/selecting-child-view-at-index-using-espresso/30073528#30073528
 /*   public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with "+childPosition+" child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }*/
}
