package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.MasterOp;

/**
 * Created by Zachary Clauson on 11/11/2017.
 */
@Autonomous(name = "Blue2", group ="Blue2")
public class Blue2 extends OpMode {
    MasterOp mo= new MasterOp();

    @Override
    public void init() {
        mo.init(hardwareMap);
    }

    @Override
    public void loop() {

        switch (mo.v_state){
            case 0:
                mo.resetEncoders();
                mo.shutdownAllMotors();
                mo.v_state++;
                break;
            case 1:
                mo.run_using_encoders();
                mo.motor7.setPower(.1);
                if ( mo.color1.blue() > 0 || mo.color1.red() > 0 ||mo.motor7.getCurrentPosition() > 450){
                    mo.shutdownAllMotors();
                    mo.resetEncoders();
                    mo.v_state++;
                }
                break;
            case 2:
                if (mo.color1.blue() > 0) {
                    mo.blueDetected=true;
                    mo.run_using_encoders();
                    mo.PowerForB(-.5, 100);
                } else if (mo.color1.red() > 0) {
                    mo.blueDetected=false;
                    mo.run_using_encoders();
                    mo.PowerForB(.5,100);
                }
                else if (mo.time > 12){
                    mo.v_state++;
                }
                else{
                    mo.time++;
                }

                break;
            case 3:
                mo.run_using_encoders();
                mo.motor7.setPower(-.2);
                if (mo.motor7.getCurrentPosition() < -300){
                    mo.shutdownAllMotors();
                    mo.resetEncoders();
                    mo.v_state++;
                }
                break;
            case 4:
                mo.run_using_encoders();
                if (mo.blueDetected){
                    mo.PowerForB(.5,1000);
                }
                else if(!mo.blueDetected) {
                    mo.PowerForB(.5, 500);
                }


                break;
            case 5:
                mo.run_using_encoders();
                mo.PowerR(.5,300);
                break;
            case 6:
                mo.run_using_encoders();
                mo.PowerForB(.5,200);
                break;


            default:


                break;
        }
        telemetry.addData("V-state: " ,mo.v_state);
        telemetry.addData("blue: ", mo.color1.blue());
        telemetry.addData("red: ",mo.color1.red());
        telemetry.addData("runtime: ", getRuntime());
        telemetry.addData("blueDetected: ",mo.blueDetected);
        telemetry.addData("motor1: ", mo.motor1.getCurrentPosition());
    }
}
