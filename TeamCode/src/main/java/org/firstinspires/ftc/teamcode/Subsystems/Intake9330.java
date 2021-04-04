package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;

public class Intake9330 {

    Hardware9330 hwMap;

    public Intake9330(Hardware9330 hwMap){
        this.hwMap = hwMap;
    }

    public void takeIn(double power){
        hwMap.intakeBottom.setPower(-power);

    }
    public void takeInTime(double power, double seconds){
        long targetTime = System.currentTimeMillis() + (long)(seconds * 1000);
        while (targetTime > System.currentTimeMillis()) {
            takeIn(power);
        }
        stop();
    }

    public void stop() {
        hwMap.intakeBottom.setPower(0);
    }

}
