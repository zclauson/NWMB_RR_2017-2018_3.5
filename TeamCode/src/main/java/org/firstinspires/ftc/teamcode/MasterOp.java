package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.SyncdDevice;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

/**
 * Created by Zachary Clauson on 6/11/2017.
 */
@Disabled
public class MasterOp {
    public DcMotor motor1 = null;
    public DcMotor motor2 = null;
    public DcMotor motor3 = null;
    public DcMotor motor4 = null;
    public DcMotor motor5 = null;
    public DcMotor motor7 = null;

    public Servo servo1 = null;
    public Servo servo2 = null;

    public ColorSensor color1 = null;

    public int v_state=0;
    public int time=0;
    public boolean blueDetected;
    public boolean redDetected;



    public HardwareMap HM = null;

    /*
    robot goes 5.43 inches every 280 impulses


                        Front
motor3   _______________________________  motor4
        |                               |
        |                               |
        |                               |
        |               center          |
        |                               |
        |                               |
        |               back            |
motor1  |_______________________________| motor2

    */

    public void init(HardwareMap hm) {
        /*this is saying the Hardware map, made at the top equals any new hardware map
         entered*/
        HM = hm;
        //configuring motors

        //name of motors in configuration files
        //motor1 = leftRear
        motor1 = HM.dcMotor.get("motor1");
        //Whether the motors will track the encoder counts or not
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //initializing the motor power when the init button is pushed on the phone
        motor1.setPower(0);
        //set motors direction
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);

        //motor2 = rightRear
        motor2 = HM.dcMotor.get("motor2");
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setPower(0);
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);

        //motor3 = leftFront
        motor3 = HM.dcMotor.get("motor3");
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setPower(0);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);

        //motor4 = rightFront
        motor4 = HM.dcMotor.get("motor4");
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setPower(0);
        motor4.setDirection(DcMotorSimple.Direction.FORWARD);
//       motor5= bottomArm
        motor5 = HM.dcMotor.get("motor5");
        motor5.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor5.setPower(0);
        motor5.setDirection(DcMotorSimple.Direction.FORWARD);

        //motor7 = sensorArm
        motor7 = HM.dcMotor.get("motor7");
        motor7.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor7.setPower(0);
        motor7.setDirection(DcMotorSimple.Direction.FORWARD);

        //servo1=leftClaw
        servo1 = HM.servo.get("servo1");
        servo1.setPosition(.30);

//        servo2= rightClaw
        servo2 = HM.servo.get("servo2");
        servo2.setPosition(.50);

        //color1 = colorSensor
        color1 = HM.colorSensor.get("color1");
        color1.enableLed(true);
    }

    public void shutdownAllMotors() {
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
        motor5.setPower(0);
        motor7.setPower(0);
    }

    public void resetEncoders() {
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor5.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor7.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public void run_using_encoders(){
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor5.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor7.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void PowerForB(double motorspeed, int motorcount) {
        run_using_encoders();
        motor1.setPower(motorspeed);
        motor2.setPower(motorspeed);
        motor3.setPower(motorspeed);
        motor4.setPower(motorspeed);
        if (Math.abs(motor3.getCurrentPosition()) > motorcount) {
            shutdownAllMotors();
            resetEncoders();
            v_state++;
        }

    }
    public void ColorSensorBlue(){
        if (color1.blue() > 0){
            PowerForB(-1 , 200);
        }
        else if (color1.red() > 0){
            PowerForB(1 , 200);
        }
    }
    public void ColorServo (double Set){
        servo2.setPosition(Set);
    }
    public void Arm(int count){
        run_using_encoders();
        motor7.setPower(.2);
        if (motor7.getCurrentPosition() > count){
            resetEncoders();
            shutdownAllMotors();
        }
    }
    public void PowerL(double motorspeed, int motorcount) {
        run_using_encoders();
        motor1.setPower(motorspeed);
        motor2.setPower(-motorspeed);
        motor3.setPower(-motorspeed);
        motor4.setPower(motorspeed);
        if (Math.abs(motor3.getCurrentPosition()) > motorcount) {
            shutdownAllMotors();
            resetEncoders();
            v_state++;
        }
    }

    public void PowerR(double motorspeed, int motorcount) {
        run_using_encoders();
        motor1.setPower(-motorspeed);
        motor2.setPower(motorspeed);
        motor3.setPower(motorspeed);
        motor4.setPower(-motorspeed);
        if (Math.abs(motor3.getCurrentPosition()) > motorcount) {
            shutdownAllMotors();
            resetEncoders();
            v_state++;

        }
    }
    public void zeroTurnR(double motorspeed, int motorcount){
        run_using_encoders();
        motor1.setPower(motorspeed);
        motor2.setPower(-motorspeed);
        motor3.setPower(motorspeed);
        motor4.setPower(-motorspeed);
        if (Math.abs(motor3.getCurrentPosition())> motorcount){
            shutdownAllMotors();
            resetEncoders();
            v_state++;
        }
    }
    public void zeroTurnL(double motorspeed, int motorcount){
        run_using_encoders();
        motor1.setPower(-motorspeed);
        motor2.setPower(motorspeed);
        motor3.setPower(-motorspeed);
        motor4.setPower(motorspeed);
        if (Math.abs(motor3.getCurrentPosition())> motorcount){
            shutdownAllMotors();
            resetEncoders();
            v_state++;
        }
    }




}