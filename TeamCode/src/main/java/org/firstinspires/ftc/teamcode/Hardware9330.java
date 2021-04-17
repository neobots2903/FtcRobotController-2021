package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware9330 {
    HardwareMap hwMap = null;

    public static DcMotor rightFront;
    public static DcMotor leftFront;
    public static DcMotor rightBack;
    public static DcMotor leftBack;
    public static DcMotor intakeBottom;
    public static DcMotor intakeMiddle;
    public static DcMotor shooter;
    public static DcMotor harvester;

    public static BNO055IMU gyro;



    public Hardware9330(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap){
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors

        rightFront = hwMap.dcMotor.get("rightFront");
        leftFront = hwMap.dcMotor.get("leftFront");

        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightBack = hwMap.dcMotor.get("rightBack");
        leftBack = hwMap.dcMotor.get("leftBack");

        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        gyro = hwMap.get(BNO055IMU.class, "gyro");


        intakeBottom = hwMap.dcMotor.get("intakeBottom");
        intakeBottom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        harvester = hwMap.dcMotor.get("harvester");
        harvester.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//      intakeMiddle = hwMap.dcMotor.get("intakeMiddle");
        shooter = hwMap.dcMotor.get("shooter");
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

}

