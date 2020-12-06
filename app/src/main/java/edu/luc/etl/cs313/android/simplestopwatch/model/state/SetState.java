package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class SetState implements StopwatchState {
    //Added a state SetState in order to add it to the state machine.
    public SetState(final StopwatchSMStateView sm) {
        this.sm = sm; ticks = 3;
    }

    private final StopwatchSMStateView sm;

    private int ticks;

    @Override
    public void onCurrentStop() {
        sm.actionInc();
        ticks = 3;
    }

    @Override
    public void onTick() {
        if(ticks <= 0){
            sm.toRunningState();
            sm.actionPlaySound();
        }
        else ticks--;
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {

        return R.string.SET;
    }
}