package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.Tools.PIDController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Shooter9330 implements Runnable {

    Hardware9330 hwMap;
    PIDController pid;

    double lastPosition = -1;
    double lastTime = -1;
    final int TICKS_PER_REV = 28;
    double lastPID = -1;
    double newRev = 0;
    boolean shoot = false;

    ScheduledExecutorService speedThread;

    public Shooter9330(Hardware9330 hwMap){
        this.hwMap = hwMap;
        pid = new PIDController(0.0011, 0.00004, 0.001);
        pid.setInputRange(0, 6000);
        pid.setOutputRange(.0, 1.0);
        pid.setSetpoint(0);
        pid.enable();

        speedThread = Executors.newSingleThreadScheduledExecutor();

        speedThread.scheduleWithFixedDelay(this, 50, 50, TimeUnit.MILLISECONDS);
    }

    public void shootForSpeed (double targetRevPerMin) {
        pid.setSetpoint(targetRevPerMin);
        shoot = true;
    }

    public void shoot(double power){
        hwMap.shooter.setPower(power);


    }
    public void shootForTime(double power, double seconds){
        long targetTime = System.currentTimeMillis() + (long)(seconds * 1000);
        while (targetTime > System.currentTimeMillis()) {
            shoot(power);
        }
        stop();
    }

    public void stop() {
        shoot = false;
        hwMap.shooter.setPower(0);
    }

    public double getCurrentPosition() {
        return hwMap.shooter.getCurrentPosition();
    }

    public double getSpeed() {
        double revPerMin = 0;

        if (lastTime != -1){
            double changeTick = hwMap.shooter.getCurrentPosition() - lastPosition;
            double changeTime = (System.currentTimeMillis() - lastTime)/1000/60; //goes from millis to minutes lol
            double rev = changeTick/TICKS_PER_REV;
            revPerMin = rev/changeTime;
        }
        lastTime = System.currentTimeMillis();
        lastPosition = hwMap.shooter.getCurrentPosition();

        newRev = revPerMin;
        return revPerMin;

    }
    public double getNewRev() {return newRev;}
    public double getSetpoint() {
      return pid.getSetpoint();
    }
    public double getPID() {
        return lastPID;
    }

    @Override
    public void run() {
        lastPID = pid.performPID(getSpeed());
        if (shoot)
        shoot(lastPID);
    }
}
