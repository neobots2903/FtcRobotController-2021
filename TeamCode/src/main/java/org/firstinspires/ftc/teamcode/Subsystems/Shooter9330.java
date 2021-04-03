package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.Tools.PIDController;

public class Shooter9330 {

    Hardware9330 hwMap;
    PIDController pid;

    double lastPosition = -1;
    double lastTime = -1;
    final int TICKS_PER_REV = 28;
    double lastPID = -1;

    public Shooter9330(Hardware9330 hwMap){
        this.hwMap = hwMap;
        pid = new PIDController(.001, 0, 0);
        pid.setInputRange(0, 100);
        pid.setOutputRange(0.0, 1.0);
        pid.setSetpoint(0);
        pid.enable();
    }

    public void shootSpeed (double speed) {
        pid.setSetpoint(speed);
        lastPID = pid.performPID(getSpeed());
        shoot(lastPID);
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
            double changeTime = (System.currentTimeMillis() - lastTime)/1000; //goes from millis to seconds lol
            double rev = changeTick/TICKS_PER_REV;
            revPerMin = rev/changeTime;
        }
        lastTime = System.currentTimeMillis();
        lastPosition = hwMap.shooter.getCurrentPosition();
        return revPerMin;
    }
    public double getSetpoint() {
      return pid.getSetpoint();
    }
    public double getPID() {
        return lastPID;
    }
}
