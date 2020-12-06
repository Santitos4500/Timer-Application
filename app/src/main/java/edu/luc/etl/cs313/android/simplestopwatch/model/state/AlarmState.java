package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;


class AlarmState implements StopwatchState {
    //Added a state AlarmState in order to add it to the state machine.

    public AlarmState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onCurrentStop() {
        sm.toStoppedState();
    }

    @Override
    public void onTick() {
        sm.actionPlaySound();
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {

        return R.string.ALARM;
    }
}