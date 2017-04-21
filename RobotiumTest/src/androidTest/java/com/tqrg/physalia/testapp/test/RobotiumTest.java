package com.tqrg.physalia.testapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.robotium.solo.Solo;


@SuppressWarnings("rawtypes")
public class RobotiumTest extends ActivityInstrumentationTestCase2 {
  	private Solo solo;
  	
  	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.tqrg.physalia.testapp.ScrollingActivity";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }
  	
  	@SuppressWarnings("unchecked")
    public RobotiumTest() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
		//Wait for activity: 'com.tqrg.physalia.testapp.ScrollingActivity'
		solo.waitForActivity("ScrollingActivity", 2000);

	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}

	public void testFindById() {
		for(int i = 0; i<40; i++){
			solo.getView("button_1");
			solo.getView("button_2");
			solo.getView("button_3");
			solo.getView("text_field");
			solo.getView("fab");
			solo.getView("paint");
			solo.getView("text_area");
		}
	}

	public void testFindByContent() {
		for(int i = 0; i<40; i++){
			solo.getButton("Button 1");
			solo.getButton("Button 2");
			solo.getButton("Button 3");
		}
	}

	public void testTap() {
		final View[] elements = new View[]{
				solo.getView("button_1"),
				solo.getView("button_2"),
				solo.getView("button_3"),
				solo.getView("fab")
		};
		for(int i = 0; i<10; i++){
			for(View element: elements){
				solo.clickOnView(element);
				solo.sleep(200);
			}
		}
		solo.sleep(1000);
	}

	public void testLongTap() {
		final View[] elements = new View[]{
				solo.getView("button_1"),
				solo.getView("button_2"),
				solo.getView("button_3"),
				solo.getView("fab")
		};
		for(int i = 0; i<10; i++){
			for(View element: elements){
				solo.clickLongOnView(element);
			}
		}
	}

	public void testDragndrop() {
		final View button1 = solo.getView("button_1");
		final View button2 = solo.getView("button_2");
		final View button3 = solo.getView("button_3");
		final View buttonFab = solo.getView("fab");
		final View textArea = solo.getView("text_area");
		final View paintArea = solo.getView("paint");
		final View[] moves = new View[]{
				button1, button2,
				button2, button3,
				buttonFab, button3,
				buttonFab, textArea,
		};
		final int moves_length = moves.length;
		final int steps = 40;
		View elementStart;
		View elementStop;
		int[] locationStart = new int[2];
		int[] locationStop = new int[2];
		int xStart, yStart, xStop, yStop;
		for(int i = 0; i<10; i++){
			for (int moveIndex = 0 ; (moveIndex+1) < moves_length; moveIndex+=2) {
				elementStart = moves[moveIndex];
				elementStop = moves[moveIndex+1];
				elementStart.getLocationOnScreen(locationStart);
				elementStop.getLocationOnScreen(locationStop);
				xStart = locationStart[0] + elementStart.getWidth()/2;
				yStart = locationStart[1] + elementStart.getHeight()/2;
				xStop = locationStop[0] + elementStop.getWidth()/2;
				yStop = locationStop[1] + elementStop.getHeight()/2;
				solo.drag(xStart, xStop,
						yStart, yStop,
						steps);
			}
		}
	}
	public void testSwipe() {
		final View paint = solo.getView("paint");
		int[] paintLocation = new int[2];
		paint.getLocationOnScreen(paintLocation);
		final int x_i = paintLocation[0]+paint.getWidth()/2;
		final int y_i = paintLocation[1];
		int x_f;
		int y_f;
		final int swipeDistance = 420;
		final int steps = 40;
		for(int i=0; i<40; i++) {
			final int offset_y = i*8;
			x_f = x_i-swipeDistance;
			y_f = y_i+offset_y+1;
			solo.drag(x_i, x_f, y_i+offset_y+1, y_f, steps);
			x_f = x_i+swipeDistance;
			y_f = y_i+offset_y;
			solo.drag(x_i, x_f, y_i+offset_y, y_f, steps);
		}
	}

	public void testBackButton() {
		for (int i = 0; i < 200; i++) {
			solo.goBack();
		}
	}

	public void testInputText(){
		final EditText textField = (EditText) solo.getView("text_field");
		solo.typeText(textField, "Physalia says hi!");
	}
}
