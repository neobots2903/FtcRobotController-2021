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
        double power = 10;
        double flPower = 0;
        double blPower = 0;
        double frPower = 0;
        double brPower = 0;

        sleep(250);
        //Go Forward
         flPower = power;
         blPower = power;
         frPower = -power;
         brPower = -power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Go Back
         flPower = -power;
         blPower = -power;
         frPower = power;
         brPower = power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Twist Left
         flPower = power;
         blPower = power;
         frPower = power;
         brPower = power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Twist Right
         flPower = -power;
         blPower = -power;
         frPower = -power;
         brPower = -power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Twist Right
        flPower = -power;
        blPower = -power;
        frPower = -power;
        brPower = -power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Twist Left
        flPower = power;
        blPower = power;
        frPower = power;
        brPower = power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Strafe Left
         flPower = -power;
         blPower = power;
         frPower = power;
         brPower = -power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Strafe Right
         flPower = power;
         blPower = -power;
         frPower = -power;
         brPower = power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Strafe Right
        flPower = power;
        blPower = -power;
        frPower = -power;
        brPower = power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Stop
        flPower = 0;
        blPower = 0;
        frPower = 0;
        brPower = 0;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        //Strafe Left
        flPower = -power;
        blPower = power;
        frPower = power;
        brPower = -power;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
        sleep(250);

        stop();
    }
}
