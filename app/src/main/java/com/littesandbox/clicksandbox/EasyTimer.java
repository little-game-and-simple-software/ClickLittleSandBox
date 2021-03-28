package com.littesandbox.clicksandbox;

import java.util.Timer;
import java.util.TimerTask;

public class EasyTimer extends TimerTask
{
    Timer timer;
    TimerTask EasyTimer_task;
    boolean loop=false;
    /**TimerTask 匿名类**/
    long wait_time;
    public void init(TimerTask task,long in_wait_time)
    {
        timer=new Timer();
        EasyTimer_task=task;
        wait_time=in_wait_time;
    }
    public void start(boolean is_loop)
    {
        loop=is_loop;
        if(loop)
        {
            execute_at_fixed_rate();
        }
        else {
             timer.schedule(EasyTimer_task,wait_time);
            }
    }
    /**周期**/
    public void execute_at_fixed_rate()
    {
      timer.scheduleAtFixedRate(EasyTimer_task,1000,0);
    }
    @Override
    public void run()
    {

    }
}
