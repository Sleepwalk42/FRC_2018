package org.usfirst.frc.falcons6443.robot.commands.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.Delay;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.DriveToDistance;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.MoveElevator;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.MoveIntake;
import org.usfirst.frc.falcons6443.robot.subsystems.Elevator;
import org.usfirst.frc.falcons6443.robot.subsystems.FlywheelSystem;
import org.usfirst.frc.falcons6443.robot.utilities.Enums.ElevatorPosition;
import org.usfirst.frc.falcons6443.robot.utilities.Enums.IntakePosition;

/**
 * Command to move to the right scale from the left starting position and place block
 *
 * @author Goirick Saha
 */

public class LeftToRightScale extends CommandGroup {


    public LeftToRightScale() {
        addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, false, false));
        addSequential(new Delay(1.5));
        addSequential(new MoveElevator(ElevatorPosition.Scale));

        addSequential(new DriveToDistance(228));
        addSequential(new RotateToAngle(270));
        addSequential(new DriveToDistance(232));
        addSequential(new RotateToAngle(90));
        addSequential(new DriveToDistance(96));
        addSequential(new RotateToAngle(90));
        addSequential(new DriveToDistance(54));


        addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, true, false));
        addSequential(new Delay(4));
        addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, false, true));
        }
}
