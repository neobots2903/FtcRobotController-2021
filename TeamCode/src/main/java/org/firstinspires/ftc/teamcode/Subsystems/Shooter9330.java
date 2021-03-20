package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.Tools.PIDController;

public class Shooter9330 {

    Hardware9330 hwMap;
    PIDController pid;

    double lastPosition = -1;
    double lastTime = -1;
    final int TICKS_PER_REV = 28;

    public Shooter9330(Hardware9330 hwMap){
        this.hwMap = hwMap;
        pid = new PIDController(0, 0, 0);
        pid.setInputRange(0, 300);
        pid.setOutputRange(0, 1);
        pid.setSetpoint(0);
    }

    public void shootSpeed (double speed){
        pid.setSetpoint(speed);
        shoot(pid.performPID(getSpeed()));
    }

    public void shoot(double power){
        hwMap.shooter.setPower(power);


    }
    public void shooterTime(double power, double seconds){
        long targetTime = System.currentTimeMillis() + (long)(seconds * 1000);
        while (targetTime > System.currentTimeMillis()) {
            shoot(power);
        }
        stop();
    }

    public void stop() {
        hwMap.shooter.setPower(0);

    }
    public double getCurrentPosition() {
        return hwMap.shooter.getCurrentPosition();
    }
    public double getSpeed() {
        double revPerMin = 0;

        if (lastTime != -1){
            double changeTick = hwMap.shooter.getCurrentPosition() - lastPosition;
            double changeTime = (System.currentTimeMillis() - lastTime)/60000; //goes from millis to minutes lol
            double rev = changeTick/TICKS_PER_REV;
            revPerMin = rev/changeTime;
        }
        lastTime = System.currentTimeMillis();
        lastPosition = hwMap.shooter.getCurrentPosition();
        return revPerMin;
    }
}
