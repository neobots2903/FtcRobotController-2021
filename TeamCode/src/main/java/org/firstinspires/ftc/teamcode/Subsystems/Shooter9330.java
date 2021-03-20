package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;

public class Shooter9330 {

    Hardware9330 hwMap;

    public Shooter9330(Hardware9330 hwMap){
        this.hwMap = hwMap;
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

}
