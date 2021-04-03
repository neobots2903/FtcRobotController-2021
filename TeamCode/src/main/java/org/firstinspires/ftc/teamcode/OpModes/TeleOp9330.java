package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.Subsystems.Drive9330;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter9330;

@TeleOp(name = "TeleOp9330", group = "Opmode")

public class TeleOp9330 extends OpMode {

    Hardware9330 robot9330 = new Hardware9330();

    Drive9330 drive;
    Shooter9330 shooter;

    private boolean isAHeld = false;
    private boolean isAHeld1 = false;
    private boolean isBHeld = false;


    @Override
    public void init() {
        robot9330.init(hardwareMap);

        drive = new Drive9330(robot9330);
        shooter = new Shooter9330(robot9330);
        shooter.getSpeed();
    }

    @Override
    public void loop() {
        telemetry.addData("Current position: ", shooter.getCurrentPosition());
        telemetry.addData("Current speed: ", shooter.getSpeed());
        telemetry.addData("Push Shoot Button: ", gamepad2.x);
        telemetry.addData("Setpoint: ", shooter.getSetpoint());
        telemetry.addData("PID value", shooter.getPID());
        if (gamepad2.a && !isAHeld) {

            telemetry.addData("Program: ", "A2 is tapped");

            isAHeld = true;

        } else if (!gamepad2.a) {

            isAHeld = false;
            telemetry.addData("Program: ", "A2 isn't tapped");
        }

        if (gamepad1.a && !isAHeld1) {

            telemetry.addData("Program: ", "A1 is tapped");

            isAHeld1 = true;

        } else if (!gamepad1.a) {

            isAHeld1 = false;
            telemetry.addData("Program: ", "A1 isn't tapped");
        }

        if (gamepad2.x) {
            shooter.shootSpeed(1);
        }
        else {
            shooter.shootSpeed(0);
        }

        if (gamepad2.b && !isBHeld) {


            isBHeld = true;

        } else if (!gamepad2.b) {

            isBHeld = false;

        }


//        float yPower = -gamepad1.left_stick_y;
//        float xPower = gamepad1.left_stick_x;
//        float averagePower = (Math.abs(gamepad1.left_stick_y) + Math.abs(gamepad1.left_stick_x)) / 2;
//
//        if (Math.abs(yPower) > 0.1) {
//            drive.driveForward(-yPower);
//        } else if (Math.abs(xPower) > 0.1) {
//            drive.driveRight(xPower);
//        }
//        drive.turnClockwise(-gamepad1.right_stick_x);
        double yPower = -gamepad1.left_stick_y;
        double xPower = gamepad1.right_stick_x;
        double rightXPower = gamepad1.left_stick_x;
        double flPower = -(yPower + (xPower * 1.25) + rightXPower); // uneven robot weight means we'll need to adjust values of 1 for proper strafing
        double blPower = -(yPower + (xPower * 1) - rightXPower);
        double frPower = yPower - (xPower * 1.25) - rightXPower;
        double brPower = yPower - (xPower * 1) + rightXPower;
        drive.mecanumDrive(frPower, flPower, brPower, blPower);
    }
}
