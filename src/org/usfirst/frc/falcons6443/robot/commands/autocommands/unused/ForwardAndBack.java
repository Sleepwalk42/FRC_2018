package org.usfirst.frc.falcons6443.robot.commands.autocommands.unused;

import org.usfirst.frc.falcons6443.robot.commands.SimpleCommand;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.DriveForTime;

/**
 * Moves the robot forward based one time, then backwards the same distance
 * Utilizes the MoveByTime class for movement
 *
 * @author Owen Engbretson
 */
public class ForwardAndBack extends SimpleCommand {

    DriveForTime moveTime;

    double stopTime;

    public ForwardAndBack(double time){
        super("ForwardAndBack");
        stopTime = time;
        requires(driveTrain);
    }

    @Override
    public void initialize() {
        super.initialize();
        setTimeout(stopTime);

    }

    @Override
    public void execute() {
        super.execute();
        moveTime =  new DriveForTime(stopTime, .4,.4);
        driveTrain.reverse();
        moveTime =  new DriveForTime(stopTime, .4,.4);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}

