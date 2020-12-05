package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.view.SoundEffectConstants;

import java.io.IOException;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;

public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel clockModel,Context c) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        beep = RingtoneManager.getRingtone(c, defaultRingtoneUri);
    }

    private Ringtone beep;

    private final TimeModel timeModel;

    private final ClockModel clockModel;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private StopwatchState state;

    public int getState(){

        if(state == SET)return 1;
        if(state == RUNNING)return 2;
        if(state == ALARM)return 3;
        return 0;
    }

    public void setState(int id){
        switch(id){
            case 0:toStoppedState();break;
            case 1:toSetState();actionStart();break;
            case 2:toRunningState();actionStart();break;
            case 3:toAlarmState();actionStart();break;
        }
    }

    protected void setState(final StopwatchState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    private StopwatchUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final StopwatchUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    // forward event uiUpdateListener methods to the current state
    @Override public void onCurrentStop() { state.onCurrentStop(); }
    @Override public void onTick()      { state.onTick(); }

    @Override public void updateUIRuntime() { uiUpdateListener.updateTime(timeModel.getRuntime()); }

    // known states
    private final StopwatchState STOPPED     = new StoppedState(this);
    private final StopwatchState RUNNING     = new RunningState(this);
    private final StopwatchState SET         = new SetState(this);
    private final StopwatchState ALARM       = new AlarmState(this);

    // transitions
    @Override public void toRunningState()    { setState(RUNNING); }
    @Override public void toStoppedState()    { setState(STOPPED); }
    @Override public void toSetState()        { setState(SET);     }
    @Override public void toAlarmState()      { setState(ALARM);   }

    // actions
    @Override public void actionInit()       {
        toStoppedState();
        actionReset();
    }
    @Override public void actionReset()      { timeModel.resetRuntime(); actionUpdateView(); }
    @Override public void actionStart()      { clockModel.start(); }
    @Override public void actionStop()       { clockModel.stop(); }

    public void actionPlaySound(){
        beep.play();
    }

    @Override
    public void actionInc() {
        timeModel.incRuntime();actionUpdateView();
    }

    @Override public void actionDec()        {
        timeModel.decRuntime();
        actionUpdateView();
    }
    @Override public void actionUpdateView() { state.updateView(); }

    public int getRuntime(){return timeModel.getRuntime();}

}