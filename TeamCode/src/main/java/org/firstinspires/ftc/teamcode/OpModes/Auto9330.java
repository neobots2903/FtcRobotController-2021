package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.Subsystems.Drive9330;
import org.firstinspires.ftc.teamcode.Subsystems.Intake9330;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter9330;

@Autonomous(name = "Auto9330", group = "Opmode")

public class Auto9330 extends LinearOpMode {

    Hardware9330 robot9330 = new Hardware9330();
    Shooter9330 shooter;
    Intake9330 intake;
    Drive9330 drive;

    private boolean isAHeld = false;
    @Override
    public void runOpMode() throws InterruptedException{

        robot9330.init(hardwareMap);
        drive = new Drive9330(robot9330);
        shooter = new Shooter9330(robot9330);
        intake = new Intake9330(robot9330);

        long startTime = 0;

        waitForStart();

        drive.gyroTurn(10);
        drive.driveForwardDistance(.5, 100);
        drive.driveForwardDistance(-.5, 56);
        drive.gyroTurn(-35);
        startTime = System.currentTimeMillis();
        while (startTime + 1000 > System.currentTimeMillis()) {
            shooter.shootForSpeed(3950);
        }
        startTime = System.currentTimeMillis();
        while (startTime + 10000 > System.currentTimeMillis()) {
            shooter.shootForSpeed(3950);
            intake.takeIn(1);
        }
        shooter.stop();
        intake.stop();
        drive.driveForwardDistance(.5, 18);


  }
}
