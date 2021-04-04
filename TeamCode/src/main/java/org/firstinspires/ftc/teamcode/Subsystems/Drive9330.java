package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;

public class Drive9330 {

    Hardware9330 hwMap;
    Gyro9330 gyro;
    double turnError = 1;

    final double TICKS_PER_ROTATION = 560;
    final double CIRCUMFERENCE = 3*(Math.PI);

    public Drive9330(Hardware9330 hwMap) {
        this.hwMap = hwMap;
        gyro = new Gyro9330(hwMap);
        gyro.init();

    }


    public void turnCounterClockwise(double power) {
        hwMap.rightFront.setPower(power);
        hwMap.leftFront.setPower(power);
        hwMap.rightBack.setPower(power);
        hwMap.leftBack.setPower(power);
    }

    public void turnClockwise(double power) {
        hwMap.rightFront.setPower(-power);
        hwMap.leftFront.setPower(-power);
        hwMap.rightBack.setPower(-power);
        hwMap.leftBack.setPower(-power);
    }

    public void turnClockwiseTime(double power, double seconds) {
        long targetTime = System.currentTimeMillis() + (long)(seconds * 1000);
        while (targetTime > System.currentTimeMillis()) {
            turnClockwise(power);
        }
        stop();

    }

    public double getGyro (){
        return gyro.getYaw();
    }

    public void gyroTurn(double targetAngle){
        double minAngle = targetAngle - turnError +  gyro.getYaw();
        double maxAngle = targetAngle + turnError +  gyro.getYaw();
        while (gyro.getYaw() < minAngle || gyro.getYaw() > maxAngle){
            double calcPower = Math.abs(maxAngle - gyro.getYaw()) / 45;
            if (calcPower < 0.1) calcPower = 0.1;
            if (gyro.getYaw() < minAngle) {
                turnClockwise(calcPower);
            } else if (gyro.getYaw() > maxAngle) {
                turnCounterClockwise(calcPower);
            }

        }
        stop();
    }

    public void gyroDriveForward (double power, double targetAngle){
        double turnOffset = (gyro.getYaw() - targetAngle) / 45;
        hwMap.rightFront.setPower(-power + turnOffset);
        hwMap.leftFront.setPower(power + turnOffset);
        hwMap.rightBack.setPower(power + turnOffset);
        hwMap.leftBack.setPower(-power + turnOffset);
    }


    public void driveForward(double power){
        hwMap.rightFront.setPower(-power);
        hwMap.leftFront.setPower(power);
        hwMap.rightBack.setPower(power);
        hwMap.leftBack.setPower(-power);
    }

    public void driveBackward(double power){
        hwMap.rightFront.setPower(power);
        hwMap.leftFront.setPower(-power);
        hwMap.rightBack.setPower(-power);
        hwMap.leftBack.setPower(power);
    }

    public void driveRight(double power){
        hwMap.rightFront.setPower(power);
        hwMap.leftFront.setPower(-power);
        hwMap.rightBack.setPower(-power);
        hwMap.leftBack.setPower(power);
    }
    public void driveLeft(double power){
        hwMap.rightFront.setPower(-power);
        hwMap.leftFront.setPower(power);
        hwMap.rightBack.setPower(power);
        hwMap.leftBack.setPower(-power);
    }
    public void mecanumDrive(double yPower, double xPower, double rightXPower){
        double flPower = -(yPower + (xPower * 1.25) + rightXPower); // uneven robot weight means we'll need to adjust values of 1 for proper strafing
        double blPower = -(yPower + (xPower * 1) - rightXPower);
        double frPower = yPower - (xPower * 1.25) - rightXPower;
        double brPower = yPower - (xPower * 1) + rightXPower;
        hwMap.rightFront.setPower(frPower);
        hwMap.leftFront.setPower(flPower);
        hwMap.rightBack.setPower(brPower);
        hwMap.leftBack.setPower(blPower);
    }
    public void driveForwardDistance(double power, double distance){ //distance in inches :)
        int startPos = hwMap.leftFront.getCurrentPosition();
        double startGyro = gyro.getYaw();
        int targetMove = (int)(TICKS_PER_ROTATION * distance / CIRCUMFERENCE);
        if (power < 1) {
            while (hwMap.leftFront.getCurrentPosition() > startPos - targetMove) {
                gyroDriveForward(power, startGyro);
            }
        }
        else {
            while (hwMap.leftFront.getCurrentPosition() < startPos + targetMove) {
                gyroDriveForward(power, startGyro);
            }
        }
        gyroTurn(startGyro);
        stop();
    }
//    public void directlyDriveRight(double power){
//        hwMap.rightFront.setPower(power);
//        hwMap.leftFront.setPower(power);
//        hwMap.rightBack.setPower(-power);
//        hwMap.leftBack.setPower(-power);
//    }

    public void driveRightTime(double power, double seconds){

        long targetTime = System.currentTimeMillis() + (long)(seconds * 1000);
        while (targetTime > System.currentTimeMillis()) {
            driveRight(power);
        }
        stop();
    }

    public void driveForwardTime(double power, double seconds){

        long targetTime = System.currentTimeMillis() + (long)(seconds * 1000);
        while (targetTime > System.currentTimeMillis()) {
            driveForward(power);
        }
        stop();
    }

//    public void driveBottomLeft(float power){
//        hwMap.leftFront.setPower(-power);
//        hwMap.rightBack.setPower(-power);
//    }
//
//    public void driveBottomRight(float power){
//        hwMap.rightFront.setPower(-power);
//        hwMap.leftBack.setPower(-power);
//    }

    public void driveTopRight(double power){
        hwMap.leftFront.setPower(-power);
        hwMap.rightBack.setPower(power);
    }

    public void driveTopLeft(double power){
        hwMap.rightFront.setPower(-power);
        hwMap.leftBack.setPower(power);
    }

    public void testRightFront(double power){
        hwMap.rightFront.setPower(power);
    }

    public void testRightBack(double power){
        hwMap.rightBack.setPower(power);
    }

    public void testLeftBack(double power){
        hwMap.leftBack.setPower(power);
    }

    public void testLeftFront(double power){
        hwMap.leftFront.setPower(power);
    }

    public void stop(){
        hwMap.rightFront.setPower(0);
        hwMap.leftFront.setPower(0);
        hwMap.rightBack.setPower(0);
        hwMap.leftBack.setPower(0);
    }

//    public double getYaw(){
//        return gyro.getYaw();
//    }




}
