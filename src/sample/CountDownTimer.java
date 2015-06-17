package sample;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Пользователь on 17.06.2015.
 */
public abstract class CountDownTimer {

    private final long millisInFuture;
    private final long countDownInterval;

    private long stopTimeInFuture;

    private boolean cancelled = false;

    private Timer timer = new Timer("CountDownTimer");
    private TimerTask timerTask;

    public CountDownTimer(long millisInFuture, long countDownInterval) {

        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;

    }

    public abstract void onFinish();
    public abstract void onTick(long millisUntilFinished);


    public synchronized final void cancel() {
        cancelled = true;

        if (timer != null) {
            timer.cancel();
        }
        stopTimeInFuture = 0;
    }

    public synchronized final CountDownTimer start() {
        cancelled = false;

        stopTimeInFuture = millisInFuture;

        if (millisInFuture <= 0) {
            onFinish();
            return this;
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (cancelled) {
                    return;
                }

                if (stopTimeInFuture <= 0) {
                    onFinish();
                } else {

                    onTick(stopTimeInFuture);
                    stopTimeInFuture = stopTimeInFuture - countDownInterval;
                }

            }
        }, 0, countDownInterval);

        return this;
    }



}
