package edu.luc.etl.cs313.android.simplestopwatch.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.TextView;
import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;


/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *
 * TODO move this and the other tests to src/test once Android Studio supports
 * non-instrumentation unit tests properly.
 */
public abstract class AbstractStopwatchActivityTest {

    /**
     * Verifies that the activity under test can be launched.
     */
    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }

    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioInit() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
        }});
    }

    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds, expect time 5.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioRun() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
            assertTrue(getStartStopButton().performClick());
        }});
        Thread.sleep(1000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(1, getDisplayedValue());
            assertTrue(getStartStopButton().performClick());
        }});
    }
    @Test

    public void testActivityScenarioRotation() {
        assertTrue(getStartStopButton().performClick());
        assertEquals(1, getDisplayedValue());
        assertEquals(1, getDisplayedValue());
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        assertEquals(1, getDisplayedValue());
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        assertEquals(1, getDisplayedValue());

    }
    /**
     * Verifies the following scenario: time is 0, press start, wait 5+ seconds,
     * expect time 5, press lap, wait 4 seconds, expect time 5, press start,
     * expect time 5, press lap, expect time 9, press lap, expect time 0.
     *
     * @throws Throwable
     */

    protected abstract StopwatchAdapter getActivity();

    protected int tvToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
        return tvToInt(ts);
    }

    protected Button getStartStopButton() {
        return (Button) getActivity().findViewById(R.id.setStop);
    }

    /**
     * Explicitly runs tasks scheduled to run on the UI thread in case this is required
     * by the testing framework, e.g., Robolectric.
     */
    protected void runUiThreadTasks() { }
}