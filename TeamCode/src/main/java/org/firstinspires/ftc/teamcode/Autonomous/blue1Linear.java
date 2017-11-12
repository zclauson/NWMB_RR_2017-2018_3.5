package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.MasterOp;

/**
 * Created by Zachary Clauson on 11/11/2017.
 */
@Autonomous(name = "LinearBlue", group ="LinearBlue")
public class blue1Linear extends LinearOpMode {
    MasterOp mo= new MasterOp();

    @Override
    public void runOpMode() {

        mo.init(hardwareMap);

         waitForStart();
        mo.servo1.setPosition(.5);
        sleep(2000);
        mo.ColorServo(.40);
        sleep(2000);
        mo.Arm(55);
        sleep(2000);
        mo.ColorSensorBlue();
        sleep(2000);
        mo.PowerForB(1,2000);
        sleep(2000);





    }
}
