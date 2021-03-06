package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.ConcreteStopwatchModelFacade;
import edu.luc.etl.cs313.android.simplestopwatch.model.StopwatchModelFacade;

/**
 * A thin adapter component for the stopwatch.
 *
 * @author laufer
 */

public class StopwatchAdapter extends Activity implements StopwatchUIUpdateListener {

    private static String TAG = "stopwatch-android-activity";

    /**
     * The state-based dynamic model.
     */
    private StopwatchModelFacade model;

    protected void setModel(final StopwatchModelFacade model) {
        this.model = model;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_main);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteStopwatchModelFacade(getApplicationContext()));
        model.setUIUpdateListener(this);
        //added if-else statement so we can get timer to set a time and to be able to count down
        if(savedInstanceState != null){
            model.getTimeModel().setRuntime(savedInstanceState.getInt("Runtime"));
            model.getStateMachine().setState(savedInstanceState.getInt("State"));
            model.getStateMachine().actionUpdateView();
        }
        else{
            model.getStateMachine().actionInit();
        }

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override

    protected void onStart() {
        super.onStart();
        model.onStart();
    }

    /**
     * Updates the seconds and minutes in the UI.
     * @param time
     */

    public void updateTime(final int time) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            //Don't need parameters for anything other than seconds
            final TextView tvS = (TextView) findViewById(R.id.seconds);
            final int seconds = time;
            tvS.setText(Integer.toString(seconds / 10) + Integer.toString(seconds % 10));
        });
    }

    /**
     * Updates the state name in the UI.
     * @param stateId
     */

    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView stateName = (TextView) findViewById(R.id.stateName);
            stateName.setText(getString(stateId));
        });
    }

    // forward event listener methods to the model
    //the following command will reset timer when stopped or finishes after hitting button
    public void onSetStop(final View view) {
        model.onCurrentStop();
    }

}