package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.teamcode.MasterOp;

/**
 * Created by Zachary Clauson on 10/28/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Blue1", group = "Blue1")
//@Disabled
public class Blue1 extends OpMode{
    MasterOp mo = new MasterOp();
    @Override
    public void init() {
        mo.init(hardwareMap);
        mo.color1.setI2cAddress(mo.color1.getI2cAddress());
    }

    @Override
    public void loop() {
//        if (getRuntime()==30){
//            mo.shutdownAllMotors();
//            mo.v_state=0;
//        }

        switch (mo.v_state){
            case 0:
                mo.resetEncoders();
                mo.shutdownAllMotors();
                mo.v_state++;
                break;

            case 1:
                mo.run_using_encoders();
                mo.motor7.setPower(.2);
                //this means whether the motor goes 200 impulses or blue is detected or red is detected
                if (mo.motor7.getCurrentPosition() > 450||mo.color1.blue() > 0 || mo.color1.red() > 0){
                    mo.shutdownAllMotors();
                    mo.resetEncoders();
                    mo.v_state++;
                }
                break;
            case 2:
                    if (mo.color1.blue() > 0) {
                        mo.blueDetected=true;
                        mo.run_using_encoders();
                        mo.PowerForB(-1, 100);
                    } else if (mo.color1.red() > 0) {
                        mo.blueDetected=false;
                        mo.run_using_encoders();
                        mo.PowerForB(1,100);
                    }

                break;
            case 3:
                mo.run_using_encoders();
                mo.motor7.setPower(-.2);
                if (mo.motor7.getCurrentPosition() < -700){
                    mo.shutdownAllMotors();
                    mo.resetEncoders();
                    mo.v_state++;
                }
            case 4:
                mo.run_using_encoders();
                if (mo.blueDetected){
                    mo.PowerForB(1,600);
                }
                else if(!mo.blueDetected) {
                    mo.PowerForB(1, 200);
                }
                break;
            case 5:
                mo.run_using_encoders();
                mo.zeroTurnRorL(1,300);
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
