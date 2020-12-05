package edu.luc.etl.cs313.android.simplestopwatch.model;


import android.os.Bundle;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateSource;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.StopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;


/**
 * A thin model facade. Following the Facade pattern,
 * this isolates the complexity of the model from its clients (usually the adapter).
 *
 * @author laufer
 */
public interface StopwatchModelFacade extends StopwatchUIListener, StopwatchUIUpdateSource {
    void onStart();
    void onCurrentStop();
    void saveInstanceState(Bundle instanceState);
    TimeModel getTimeModel();
    StopwatchStateMachine getStateMachine();
}