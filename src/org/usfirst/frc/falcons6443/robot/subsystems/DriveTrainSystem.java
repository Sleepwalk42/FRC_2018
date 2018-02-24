package org.usfirst.frc.falcons6443.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.usfirst.frc.falcons6443.robot.RobotMap;
import org.usfirst.frc.falcons6443.robot.hardware.SpeedControllerGroup;
import org.usfirst.frc.falcons6443.robot.hardware.DriveEncoders;

/**
 * Subsystem for the robot's drive train.
 * <p>
 * Contains 2 SpeedControllerGroups which are controlled by an instance of RobotDrive.
 * This class is meant to fix some of the shortcomings of the original DriveTrainSystem
 * class as well as make it more simple and readable.
 *
 * @author Christopher Medlin, Ivan Kenevich, Shivashriganesh Mahato
 */
public class DriveTrainSystem extends Subsystem {

    // PID: proportional–integral–derivative controller
    // more info at https://en.wikipedia.org/wiki/PID_controller
    public static final double KP = 0.04;  //.04
    public static final double KI = 0.001; //.001
    public static final double KD = 0.00;  //.00
    public static final double KF = 0.00;

    private SpeedControllerGroup leftMotors;
    private SpeedControllerGroup rightMotors;

    private DriveEncoders encoders;

    private boolean reversed;
    private static final double WheelDiameter = 6;

    // A [nice] class in the wpilib that provides numerous driving capabilities.
    // Use it whenever you want your robot to move.
    private DifferentialDrive drive;

    /**
     * Constructor for DriveTrainSystem.
     */
    public DriveTrainSystem() {
        leftMotors = new SpeedControllerGroup(new Spark(RobotMap.FrontLeftMotor),
                new Spark(RobotMap.BackLeftMotor));
        rightMotors = new SpeedControllerGroup(new Spark(RobotMap.FrontRightMotor),
                new Spark(RobotMap.BackRightMotor));
        drive = new DifferentialDrive(leftMotors, rightMotors);
        leftMotors.setInverted(true);
        encoders = new DriveEncoders();
        // the driver station will complain for some reason if this isn't set so it's pretty necessary.
        // [FOR SCIENCE!]
        drive.setSafetyEnabled(false);
        reversed = false;
        drive.setMaxOutput(1);
    }

    @Override
    public void initDefaultCommand() {
    }

    /**
     * Allows for custom setting of motor power level.
     *
     * @param left  the power for the left motors.
     * @param right the power for the right motors.
     */
    public void tankDrive(double left, double right) {
        if (reversed) {
            drive.tankDrive(-left - .05, -right); //.024 //+ +, -
        } else {
            drive.tankDrive(left + .05, right);//- -, +
        }
    }

    /**
     * Spins the robot.
     * <p>
     * A negative speed spins the robot clockwise and a positive speed
     * spins it counter-clockwise.
     * [I know that some of you math nerds will be annoyed by this choice of sign]
     *
     * @param speed the speed at which the robot spins.
     */
    public void spin(double speed) {
        drive.tankDrive(speed, -speed);
    }

    /**
     * Toggles the motors to go in reverse.
     */
    public void reverse() {
        reversed = !reversed;
    }

    public boolean isReversed() {
        return reversed;
    }

    public double getLeftDistance(){
        // Encoder clicks per rotation = 850
        return -encoders.getLeftDistance() * WheelDiameter * Math.PI / 850; // In inches
    }

    public double getRightDistance(){
        return encoders.getRightDistance() * WheelDiameter * Math.PI / 850; // In inches
    }

    public double getLinearDistance(){
        return (getLeftDistance() + getRightDistance()) / 2;
    }

    public void reset(){
        encoders.reset();
    }
}