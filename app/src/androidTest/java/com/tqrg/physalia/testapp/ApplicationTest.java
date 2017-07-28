package com.tqrg.physalia.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Basic sample for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class ApplicationTest {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.tqrg.physalia.testapp";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        // mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void findById() {
        for(int i=0; i<40; i++){
            mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_1"));
            mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_2"));
            mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_3"));
            mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "text_field"));
            mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "fab"));
            mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "paint"));
            mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "text_area"));
        }
    }

    @Test
    public void findByDescription(){
        for(int i=0; i<40; i++){
            mDevice.findObject(By.desc("Button One"));
            mDevice.findObject(By.desc("Button Two"));
            mDevice.findObject(By.desc("Button Three"));
            mDevice.findObject(By.desc("Button Fab"));
            mDevice.findObject(By.desc("Text Field"));
            mDevice.findObject(By.desc("Paint"));
            mDevice.findObject(By.desc("Text Area"));
        }
    }

    @Test
    public void findByContent(){
        for(int i=0; i<40; i++){
            mDevice.findObject(By.text("Button 1"));
            mDevice.findObject(By.text("Button 2"));
            mDevice.findObject(By.text("Button 3"));
        }
    }


    @Test
    public void tap(){
        UiObject2[] elements = {
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_1")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_2")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_3")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "fab")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "text_area"))
        };

        for(int i=0; i<10; i++) {
            for (UiObject2 element : elements) {
                element.click();
            }
        }
    }

    @Test
    public void longTap(){
        UiObject2[] elements = {
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_1")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_2")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_3")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "fab")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "text_area"))
        };

        for(int i=0; i<10; i++) {
            for (UiObject2 element : elements) {
                element.longClick();
            }
        }
    }


    @Test
    public void multiFingerTap(){
        UiObject2[] elements = {
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_1")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_2")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_3")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "fab")),
                mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "text_area"))
        };

        for(int i=0; i<10; i++) {

            UiObject2 prevElement = elements[elements.length - 1];
            for (UiObject2 element : elements) {
//                mDevice.getAutomatorBridge().getInteractionController().performTwoPointerGesture(element.getVisibleCenter(),
//                        prevElement.getVisibleCenter(),
//                        element.getVisibleCenter(),
//                        prevElement.getVisibleCenter(),
//                        10
//                );
//                ToDo
                prevElement = element;
            }
        }
    }

    @Test
    public void dragndrop(){
        final UiObject2 button1 = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_1"));
        final UiObject2 button2 = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_2"));
        final UiObject2 button3 = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button_3"));
        final UiObject2 buttonFab = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "fab"));
        final UiObject2 textArea = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "text_area"));

        final UiObject2 moves[] = {
            button1, button2,
            button2, button3,
            buttonFab, button3,
            buttonFab, textArea,
        };
        final int moves_length = moves.length;

        for(int i=0; i<10; i++) {
            UiObject2 elementStart;
            UiObject2 elementStop;
            for (int moveIndex = 0 ; (moveIndex+1)< moves_length; moveIndex+=2) {
                elementStart = moves[moveIndex];
                elementStop = moves[moveIndex+1];
                elementStart.drag(elementStop.getVisibleCenter());
                mDevice.waitForIdle();
            }
        }
    }


    @Test
    public void swipe(){
        UiObject2 paint = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "paint"));
        int x_i = paint.getVisibleCenter().x;
        int y_i = paint.getVisibleBounds().top;
        int x_f;
        int y_f;
        int swipeDistance = 420;
        int steps = 40;
        for(int i=0; i<40; i++) {
            int offset_y = i*8;
            x_f = x_i-swipeDistance;
            y_f = y_i+offset_y+1;
            mDevice.swipe(x_i, y_i+offset_y+1, x_f, y_f, steps);
            x_f = x_i+swipeDistance;
            y_f = y_i+offset_y;
            mDevice.swipe(x_i, y_i+offset_y, x_f, y_f, steps);
        }
    }

    @Test
    public void pinchAndSpread(){
        UiObject2 paint = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "paint"));
        for(int i=0; i<40; i++) {
            //pinch
            paint.pinchOpen(0.5f);
            //spread
            paint.pinchClose(0.5f);
        }
        //FIXME
    }

    @Test
    public void backButton(){
        for(int i=0; i<200; i++) {
            mDevice.pressBack();
        }
    }

    @Test
    public void inputText(){
        UiObject2 textField = mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "text_field"));
        for(int i=0; i<10; i++) {
            textField.setText("Physalia says hi!");
            textField.clear();
        }
    }

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
