package edu.luc.etl.cs313.android.simplestopwatch.model.clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An implementation of the internal clock.
 *
 * @author laufer
 */

public class DefaultClockModel implements ClockModel {

    private Timer timer;

    private OnTickListener listener;

    @Override

    public void setOnTickListener(final OnTickListener listener) {
        this.listener = listener;
    }

    @Override

    public void start() {
        timer = new Timer();

        // The clock model runs onTick every 1000 milliseconds
        timer.schedule(new TimerTask() {
            @Override public void run() {
                //don't need a new timer task since it won't be a stopwatch
                // fire event
                listener.onTick();
            }
        }, /*initial delay*/ 1000, /*periodic delay*/ 1000);
    }

    @Override

    public void stop() {
        //added a try and catch so that there are no exceptions
        try{timer.cancel();}
        catch(NullPointerException e){}
    }
}