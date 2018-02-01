package org.usfirst.frc.falcons6443.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.falcons6443.robot.subsystems.DriveTrainSystem;

/**
 * Command to rotate the robot to an angle specified in a constructor parameter.
 *
 * @author Christopher Medlin, Ivan Kenevich
 */
public class RotateToAngle extends SimpleCommand implements PIDOutput {

    private double pidOutput;
    private float angle;
    private double time;
    private PIDController pid;
    private Timer timer;
    private double targetAngle;
    private static final double AngleBuffer = 2;
    //target distance and distance buffer in DriveTrainSystem class


    /**
     * Constructor for RotateToAngle.
     *
     * @param angle the angle at which to rotate.
     */
    public RotateToAngle(float angle, double seconds) {
        super("Restricted PID Drive");
        requires(navigation);
        requires(driveTrain);
        this.angle = angle;
        time = seconds;
        timer = new Timer();
    }

    @Override
    public void initialize() {
        navigation.reset();
        initPIDController();
        setTimeout(time);
    }

    public void initPIDController() {
        pid = new PIDController(DriveTrainSystem.KP,
                DriveTrainSystem.KI,
                DriveTrainSystem.KD,
                DriveTrainSystem.KF,
                navigation.navx.ahrs(), this);
        pid.setInputRange(-180.0f, 180.0f);
        pid.setOutputRange(-0.6, 0.6);
        pid.setAbsoluteTolerance(2.0f);
        pid.setContinuous(true);
        pid.setSetpoint(angle);
        pid.enable();
    }

    @Override
    public void execute() {
        driveTrain.tankDrive(pidOutput, -pidOutput);
    }

    @Override
    public boolean isFinished() {
        if (pid.onTarget() && isTimedOut()) {
            driveTrain.tankDrive(0, 0);
            pid.disable();
            pid.free();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void pidWrite(double output) {
        pidOutput = output;
    }

    public void turnToAngle(double angle, double speed){
        targetAngle = angle;
        while (!isAtAngle()){
            int direction = navigation.getYaw() < angle ? -1 : 1;
            driveTrain.spin(speed * direction);
            timer.delay(.5);
        }
        driveTrain.tankDrive(0, 0);
    }

    public boolean isAtAngle(){
        if ((navigation.getYaw() + AngleBuffer) > targetAngle && (navigation.getYaw() - AngleBuffer) < targetAngle){
            return true;
        } else
            return false;
    }
}
