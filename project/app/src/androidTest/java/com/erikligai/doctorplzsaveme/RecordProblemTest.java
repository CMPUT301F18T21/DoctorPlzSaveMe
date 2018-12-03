// USE CASE 1
// Date is set to 'now' for now

package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Activities.AddProblemActivity;
import com.erikligai.doctorplzsaveme.Activities.MainProblemActivity;
import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemDescException;
import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemTitleException;
import com.erikligai.doctorplzsaveme.Models.Problem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecordProblemTest {
    private String title = "Test Problem";
    private String desc = "Test Desc";
    private String long_title = "This is a long long long long long long string that is longer than 30 characters";
    private String long_desc = "Long Long Long Long Long Long Long Long Long Long \n" +
            "Long Long Long Long Long Long Long Long Long Long \n" +
            "Long Long Long Long Long Long Long Long Long Long \n" +
            "Long Long Long Long Long Long Long Long Long Long \n" +
            "Long Long Long Long Long Long Long Long Long Long \n" +
            "Long Long Long Long Long Long Long Long Long Long \n" +
            "Long Long Long Description";
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
//        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        //onView(withId(R.id.problems_recyclerview)).check(matches(isDisplayed()));
//        onView(ViewMatchers.withId(R.id.problems_recyclerview))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//        onView(withId(R.id.editProblemTitle)).check(matches(withText(title)));
//        onView(nthChildOf(withId(R.id.problems_recyclerview), 0))
//                .check(matches(hasDescendant(withText(title))));
    }
    @Rule
    public ActivityTestRule<AddProblemActivity> mActivityRule =
            new ActivityTestRule<>(AddProblemActivity.class);

    @Test
    public void testTooLongProblemTitleException() {
//        onView(withId(R.id.fab)).perform(click());
//        onView(withId(R.id.editProblemTitle)).perform(typeText(long_title));
//        onView(withId(R.id.editProblemDescription)).perform(typeText(desc),closeSoftKeyboard());
//        onView(withId(R.id.saveButton)).perform(click());
        try {
            Problem newProblem = new Problem(title, desc, Calendar.getInstance().getTime());
            newProblem.setTitle(long_title);
        } catch (TooLongProblemTitleException e) {

        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTooLongDescriptionTitleException() {
//        onView(withId(R.id.fab)).perform(click());
//        onView(withId(R.id.editProblemTitle)).perform(typeText(long_title));
//        onView(withId(R.id.editProblemDescription)).perform(typeText(desc),closeSoftKeyboard());
//        onView(withId(R.id.saveButton)).perform(click());
        try {
            Problem newProblem = new Problem(title, desc, Calendar.getInstance().getTime());
            newProblem.setTitle(long_desc);
        } catch (TooLongProblemTitleException e) {

        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
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
