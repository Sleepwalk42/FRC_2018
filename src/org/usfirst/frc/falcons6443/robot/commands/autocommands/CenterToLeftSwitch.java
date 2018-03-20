package org.usfirst.frc.falcons6443.robot.commands.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.Delay;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.DriveToDistance;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.MoveElevator;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.MoveIntake;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.Crawl;
import org.usfirst.frc.falcons6443.robot.utilities.enums.ElevatorPosition;
import org.usfirst.frc.falcons6443.robot.utilities.enums.IntakePosition;

/**
 * Command to move to the left switch from the right starting position and place block
 *
 * @author Goirick Saha
 */

public class CenterToLeftSwitch extends CommandGroup {

    public CenterToLeftSwitch() {
        addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, false, false, true));
        addSequential(new Delay(.3));
        addSequential(new MoveElevator(ElevatorPosition.Switch));

        addSequential(new DriveToDistance(45));
        addSequential(new RotateToAngle(270)); //Turns 90 degrees left.
        addSequential(new DriveToDistance(110));
        addSequential(new RotateToAngle(90));
        addSequential(new DriveToDistance(60)); //56
        addSequential(new Crawl(true));

        addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, true, false, false));
        addSequential(new Delay(2));
        addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, false, true, false));
    }
}
