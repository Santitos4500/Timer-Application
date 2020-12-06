package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions

    void toRunningState();
    void toStoppedState();
    void toSetState();//added SetState removed lap running
    void toAlarmState();//added AlarmState removed lap stopped

    // actions

    //removed the Lap actions and added dec, play sound, and getRuntime
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionUpdateView();
    void actionInc();
    void actionDec();
    void actionPlaySound();
    int getRuntime();

    // state-dependent UI updates

    //removed update ui laptime
    void updateUIRuntime();

}