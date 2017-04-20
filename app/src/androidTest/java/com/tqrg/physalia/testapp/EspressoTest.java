package com.tqrg.physalia.testapp;


import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {

    //private String mStringToBetyped;
    @Rule
    public ActivityTestRule<ScrollingActivity> mActivityRule = new ActivityTestRule<>(ScrollingActivity.class);

//    @Before
//    public void initValidString() {
//        // Specify a valid string.
//        mStringToBetyped = "Espresso";
//    }
//
//    @Test
//    public void changeText_sameActivity() {
//        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
//        onView(withId(R.id.changeTextBt)).perform(click());
//
//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged))
//                .check(matches(withText(mStringToBetyped)));
//    }

    @Test
    public void findById() {
        for(int i=0; i<40; i++){
            onView(withId(R.id.button_1));
            onView(withId(R.id.button_2));
            onView(withId(R.id.button_3));
            onView(withId(R.id.text_field));
            onView(withId(R.id.fab));
            onView(withId(R.id.paint));
            onView(withId(R.id.text_area));
        }
    }

    @Test
    public void findByDescription(){
        for(int i=0; i<40; i++){
            onView(withContentDescription("Button One"));
            onView(withContentDescription("Button Two"));
            onView(withContentDescription("Button Three"));
            onView(withContentDescription("Button Fab"));
            onView(withContentDescription("Text Field"));
            onView(withContentDescription("Paint"));
            onView(withContentDescription("Text Area"));
        }
    }

    @Test
    public void findByContent(){
        for(int i=0; i<40; i++){
            onView(withText("Button 1")).perform(click());
            onView(withText("Button 2"));
            onView(withText("Button 3"));
        }
    }

    @Test
    public void tap(){
        ViewInteraction[] elements = {
                onView(withId(R.id.button_1)),
                onView(withId(R.id.button_2)),
                onView(withId(R.id.button_3)),
                onView(withId(R.id.fab)),
        };

        for(int i=0; i<10; i++) {
            for (ViewInteraction element : elements) {
                element.perform(click());
            }
        }
    }

    @Test
    public void longTap(){
        ViewInteraction[] elements = {
                onView(withId(R.id.button_1)),
                onView(withId(R.id.button_2)),
                onView(withId(R.id.button_3)),
                onView(withId(R.id.fab)),
        };

        for(int i=0; i<10; i++) {
            for (ViewInteraction element : elements) {
                element.perform(longClick());
            }
        }
    }

    @Test
    public void multiFingerTap(){
        // No multi finger tap
    }

    @Test
    public void dragndrop(){
        // No drag and drop
    }

    @Test
    public void swipe(){
        ViewInteraction paint = onView(withId(R.id.paint));
        for(int i=0; i<40; i++) {
            int offset_y = i*8;
            paint.perform(swipeTopLeft(offset_y));
            paint.perform(swipeTopRight(offset_y));
        }
    }
    private static ViewAction swipeTopLeft(final float offsetY) {
        CoordinatesProvider translatedTopCenter = new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view) {
                float[] coordinates =  GeneralLocation.TOP_CENTER.calculateCoordinates(view);
                coordinates[1] += offsetY;
                return coordinates;
            }
        };
        CoordinatesProvider translatedTopLeft = new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view) {
                float[] coordinates =  GeneralLocation.TOP_LEFT.calculateCoordinates(view);
                coordinates[1] += offsetY;
                return coordinates;
            }
        };
        return new GeneralSwipeAction(Swipe.FAST, translatedTopCenter,
                translatedTopLeft, Press.FINGER);
    }
    private static ViewAction swipeTopRight(final float offsetY) {
        CoordinatesProvider translatedTopCenter = new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view) {
                float[] coordinates =  GeneralLocation.TOP_CENTER.calculateCoordinates(view);
                coordinates[1] += offsetY;
                return coordinates;
            }
        };
        CoordinatesProvider translatedTopRight= new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view) {
                float[] coordinates =  GeneralLocation.TOP_RIGHT.calculateCoordinates(view);
                coordinates[1] += offsetY;
                return coordinates;
            }
        };
        return new GeneralSwipeAction(
                Swipe.FAST,
                translatedTopCenter,
                translatedTopRight, Press.FINGER);
    }

    @Test
    public void backButton(){
        for(int i=0; i<200; i++) {
            pressBack();
        }
    }

    @Test
    public void inputText(){
        ViewInteraction textField = onView(withId(R.id.text_field));

        //for(int i=0; i<40; i++) {
            textField.perform(click()).perform(typeText("Physalia says hi!"));
        //}
    }

}
