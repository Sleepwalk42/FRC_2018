package org.usfirst.frc.falcons6443.robot.commands;

import org.usfirst.frc.falcons6443.robot.utilities.PID;

/**
 * Command to rotate the robot to an angle specified in a constructor parameter.
 *
 * @author Christopher Medlin, Ivan Kenevich
 */
public class RotateToAngle extends SimpleCommand {

    public static final double P = 0;
    public static final double I = 0;
    public static final double D = 0;
    public static final double Eps = 0; //weakest applied power

    private static final double buffer = 1; //degrees

    private PID pid;
    private double targetAngle;


    /**
     * Constructor for RotateToAngle.
     *
     * @param angle the angle at which to rotate.
     */
    public RotateToAngle(double angle) {
        super("Restricted PID Drive");
        requires(navigation);
        requires(driveTrain);
        pid = new PID(P, I, D, Eps);
        pid.setMaxOutput(1);
        pid.setMinDoneCycles(5);
        pid.setDoneRange(buffer);
        targetAngle = angle;
    }

    public void turnToAngle(){
        int direction = navigation.getYaw() < targetAngle ? -1 : 1;
        double power = pid.calcPID(navigation.getYaw());
        driveTrain.spin(power * direction);
    }

    public void setAngle(){
        pid.setDesiredValue(targetAngle);
    }
    public boolean isAtAngle(){
        return pid.isDone();
    }

    @Override
    public void initialize() {
        navigation.reset();
    }

    @Override
    public void execute() {
        setAngle();
        turnToAngle();
    }

    @Override
    public boolean isFinished() {
        return isAtAngle();
    }
}
