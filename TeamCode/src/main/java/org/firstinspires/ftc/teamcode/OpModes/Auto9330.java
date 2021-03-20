package org.firstinspires.ftc.teamcode.OpModes;

import android.os.PowerManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.Subsystems.Drive9330;

@Autonomous(name = "Auto9330", group = "Opmode")

public class Auto9330 extends LinearOpMode {

    Hardware9330 robot9330 = new Hardware9330();

    Drive9330 drive;

    private boolean isAHeld = false;

    @Override
    public void runOpMode() throws InterruptedException{

        robot9330.init(hardwareMap);

        drive = new Drive9330(robot9330);

        if (gamepad2.a && !isAHeld) {

            telemetry.addData("Program: ", "A2 is tapped");

            stop();

        }
        double power = .5;

        //Strafe Left
//        flPower = power;
//        blPower = -power;
//        frPower = power;
//        brPower = -power;
        //Go Forward
//         flPower = -power;
//         blPower = -power;
//         frPower = power;
//         brPower = power;
        drive.driveForward(power);
        drive.turnCounterClockwise(power);
        drive.turnClockwise(power);
        drive.driveBackward(power);
        drive.driveLeft(power);
        drive.driveRight(power);
        drive.stop();
        sleep(200000);
        sleep(250);

//        //Go Back
//         flPower = power;
//         blPower = power;
//         frPower = -power;
//         brPower = -power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Twist Left
//         flPower = power;
//         blPower = power;
//         frPower = power;
//         brPower = power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Twist Right
//         flPower = -power;
//         blPower = -power;
//         frPower = -power;
//         brPower = -power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Twist Right
//        flPower = -power;
//        blPower = -power;
//        frPower = -power;
//        brPower = -power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Twist Left
//        flPower = power;
//        blPower = power;
//        frPower = power;
//        brPower = power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        power = .5;
//        //Strafe Left
//        flPower = power;
//        blPower = -power;
//        frPower = -power;
//        brPower = power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(2000);
//
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Strafe Right
//        flPower = -power;
//        blPower = power;
//        frPower = power;
//        brPower = -power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(2000);
//
//
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Strafe Right
//        flPower = power;
//        blPower = -power;
//        frPower = -power;
//        brPower = power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Strafe Left
//        flPower = -power;
//        blPower = power;
//        frPower = power;
//        brPower = -power;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(250);
//
//        //Stop
//        flPower = -.5;
//        blPower = 0;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(2500);
//        //Stop
//        flPower = 0;
//        blPower = -.5;
//        frPower = 0;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(2500);
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = -.5;
//        brPower = 0;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(2500);
//        //Stop
//        flPower = 0;
//        blPower = 0;
//        frPower = 0;
//        brPower = -.5;
//        drive.mecanumDrive(frPower, flPower, brPower, blPower);
//        sleep(2500);
//        stop();
  }
}
