package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MasterOp;

/**
 * Created by Zachary Clauson on 11/11/2017.
 */
@Autonomous(name = "LinearDriveFor", group = "LinearDriveFor")
public class LinearDriveFor extends LinearOpMode {

    MasterOp mo =new MasterOp();
    @Override
    public void runOpMode()  {

        mo.init(hardwareMap);

        waitForStart();
//        mo.servo1.setPosition(.5);
        mo.PowerForB(1,2000);
        sleep(2000);

    }
}
