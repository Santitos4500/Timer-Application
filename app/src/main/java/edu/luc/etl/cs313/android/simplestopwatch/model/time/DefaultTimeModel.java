package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {
    //no functions in the program to run a lap, so it was expelled from the program

    private int runningTime = 0;
    //removed  private int lapTime
    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    public void setRuntime(int time){
        runningTime = time;
    }
    //incRuntime allows for the runtime to be increase only if the running time is less than 99
    @Override
    public void incRuntime() {
        if(runningTime < 99 )runningTime = getRuntime()+1;
    }
    //decRuntime decrease the time for each SEC_PER_TICK
    @Override
    public void decRuntime(){
        runningTime = (runningTime - SEC_PER_TICK);
    }
    //removed getLaptime
    @Override
    public int getRuntime() {
        return runningTime;
    }


}