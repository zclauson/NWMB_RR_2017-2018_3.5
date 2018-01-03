package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.MasterOp;

/**
 * Created by Anneliese on 11/25/2017.
 */
@Autonomous(name = "Red1", group = "Red1_Ken")
public class Red1 extends OpMode {
    MasterOp mo = new MasterOp();

    @Override
    public void init() {
        mo.init(hardwareMap);
        mo.color1.setI2cAddress(mo.color1.getI2cAddress());
    }

    @Override
    public void loop() {

        switch (mo.v_state) {
            case 0:
                mo.resetEncoders();
                mo.shutdownAllMotors();
                mo.v_state++;

                break;
            case 1:
                mo.run_using_encoders();
                mo.motor7.setPower(.1);
                if ( mo.color1.blue() > 0 || mo.color1.red() > 0||mo.motor7.getCurrentPosition() > 450) {
                    mo.resetEncoders();
                    mo.shutdownAllMotors();
                    mo.v_state++;
                }
                break;

            case 2:
                if (mo.color1.red() > 0) {
                    mo.redDetected = true;
                    mo.run_using_encoders();
                    mo.PowerForB(-.5, 100);
                } else if (mo.color1.blue() > 0) {
                    mo.redDetected = false;
                    mo.run_using_encoders();
                    mo.PowerForB(.5, 25);
                } else if(mo.time > 300){
                    mo.v_state++;
                }
                else{
                    mo.time++;
                }
                break;

            case 3:
                mo.run_using_encoders();
                mo.motor7.setPower(-.2);
                if (mo.motor7.getCurrentPosition() < -300) {
                    mo.shutdownAllMotors();
                    mo.resetEncoders();
                    mo.v_state++;
                }
                break;
            case 4:
                mo.run_using_encoders();
                if (mo.redDetected) {
                    mo.PowerForB(-.5, 600);
                } else if (!mo.redDetected) {
                    mo.PowerForB(-.5,1900);
                }
                break;
            case 5:
                if (!mo.redDetected) {
                    mo.run_using_encoders();
                    mo.zeroTurnR(.5, 1200);
                }
                else if (mo.redDetected){
                    mo.run_using_encoders();
                    mo.zeroTurnR(.5,1400);
                }
                break;
            case 6:
                mo.run_using_encoders();
                mo.PowerForB(.5,500);
                break;



            default:

                break;
        }

            telemetry.addData("v_state: " , mo.v_state);
            telemetry.addData("blue: ", mo.color1.blue());
            telemetry.addData("red: ", mo.color1.red());
            telemetry.addData("runtime: ", getRuntime());
            telemetry.addData("blueDetected: ", mo.redDetected);
        }
    }

