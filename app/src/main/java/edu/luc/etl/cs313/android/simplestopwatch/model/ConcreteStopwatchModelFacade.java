package edu.luc.etl.cs313.android.simplestopwatch.model;

import android.content.Context;
import android.os.Bundle;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.DefaultClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.DefaultStopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.StopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.DefaultTimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the model facade.
 *
 * @author laufer
 */
public class ConcreteStopwatchModelFacade implements StopwatchModelFacade {

    private StopwatchStateMachine stateMachine;

    private ClockModel clockModel;

    private TimeModel timeModel;

    public ConcreteStopwatchModelFacade(Context c) {
        timeModel = new DefaultTimeModel();
        clockModel = new DefaultClockModel();
        stateMachine = new DefaultStopwatchStateMachine(timeModel, clockModel, c);
        clockModel.setOnTickListener(stateMachine);
    }

    @Override
    public void onStart() {
        stateMachine.actionInit();
    }

    @Override
    public void setUIUpdateListener(final StopwatchUIUpdateListener listener) {
        stateMachine.setUIUpdateListener(listener);
    }

    //got rid of onLapReset because there are no laps in the program
    public TimeModel getTimeModel(){ return timeModel;}
    public StopwatchStateMachine getStateMachine(){return stateMachine;}

    @Override
    public void onCurrentStop() {
        stateMachine.onCurrentStop();
    }

    @Override
    public void saveInstanceState(Bundle instanceState) {
        instanceState.putInt("Runtime",timeModel.getRuntime());
        instanceState.putInt("State",stateMachine.getState());
    }

}