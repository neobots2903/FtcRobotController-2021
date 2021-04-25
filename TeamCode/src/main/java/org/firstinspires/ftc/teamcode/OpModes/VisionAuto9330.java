package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.Subsystems.Drive9330;
import org.firstinspires.ftc.teamcode.Subsystems.Intake9330;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter9330;

@Autonomous(name = "VisionAuto9330", group = "Opmode")

public class VisionAuto9330 extends LinearOpMode {

    Hardware9330 robot9330 = new Hardware9330();
    Shooter9330 shooter;
    Intake9330 intake;
    Drive9330 drive;
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";
    private static final String VUFORIA_KEY = "ASjf2X//////AAABmaSiOIN450JtjGH0ntCeFBgNkwjGNU1yNOiZY8eGur1KVC1DOt8P4DNdGgZz62Z1I3G0OGlRStjVSfAgVLlXgv9wYYZyDzpCttFZukWFOmyyeZ9C0Zu47XHXAeHhjAU1uEd4dx2YLzUnI4n/TUMVXClsRKocSSKhGXTVIBfp15hYw3D3eCwCag79c/hY9HyoEkaeRyzZQmfHdBn1p/BTPmC2ZFlMOhl9LcmKGZ1KJ/oVOGLtTA0coJpvqgrgHBEguhQkClmk7I+Q0sHALfi4A8jBpUb+dOroJ+ZNO72Hhn9tlHIMgun9cmYYH3alrrhheqLXOKCG7AX2LCfPmNQJ009a5qNZK6PzgdoPgTIB7eLP";


    private boolean isAHeld = false;
    private String elementSeen = "";
    long startTime = 0;
    long visionStartTime = 0;
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {

        robot9330.init(hardwareMap);
        drive = new Drive9330(robot9330);
        shooter = new Shooter9330(robot9330);
        intake = new Intake9330(robot9330);

        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();

            tfod.setZoom(2.5, 16.0 / 9.0);
        }

        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();
        double startAngle = drive.getGyro();
        if (opModeIsActive()) {
            visionStartTime = System.currentTimeMillis();
            while (opModeIsActive() && elementSeen == "") {
                if (System.currentTimeMillis() >= visionStartTime + 1000) {
                    elementSeen = "None";
                }
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            elementSeen = recognition.getLabel();
                            break;
                        }
                        telemetry.update();
                    }
                }
            }
            switch (elementSeen) {
                case "None":
                    drive.gyroTurn(10);
                    drive.driveForwardDistance(1, 48);
                    drive.driveForwardDistance(-1, 5);
                    drive.gyroTurn(startAngle);
                    drive.driveRightTime(-1, .5);
                    break;
                case "Single":
                    drive.gyroTurn(-5);
                    drive.driveForwardDistance(1, 70);
                    drive.driveForwardDistance(-1, 27);
                    break;
                case "Quad":
                    drive.gyroTurn(8);
                    drive.driveForwardDistance(1, 100);
                    drive.driveForwardDistance(-1, 56);
                    drive.gyroTurn(startAngle);
                    drive.driveRightTime(-1, .4);
                    break;
            }
            drive.gyroTurn(startAngle - 2);
            startTime = System.currentTimeMillis();
            while (startTime + 000 > System.currentTimeMillis()) {
                shooter.shootForSpeed(3950);
            }
            startTime = System.currentTimeMillis();
            while (startTime + 10000 > System.currentTimeMillis()) {
                shooter.shootForSpeed(3950);
                intake.takeIn(1);
            }
//            int count = 0;
//            double lastSpeed = shooter.getSpeed();
//            while (count < 3){
//                telemetry.addData("count yeaaah :): ", count);
//                telemetry.addData("Speed: ", lastSpeed);
//                telemetry.update();
//                if (shooter.getSpeed() <= 3300 && lastSpeed > 3300){
//                    count++;
//                }
//                shooter.shootForSpeed(3950);
//                intake.takeIn(1);
//                lastSpeed = shooter.getSpeed();
//            }
            shooter.stop();
            intake.stop();
            switch (elementSeen){
                case "None":
                case "Single":
                    drive.driveForwardDistance(1, 15);
                    break;
                case "Quad":
                    drive.driveForwardDistance(1, 20);
                    break;
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }
    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}
