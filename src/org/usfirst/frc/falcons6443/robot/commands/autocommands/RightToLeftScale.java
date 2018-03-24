package org.usfirst.frc.falcons6443.robot.commands.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.Delay;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.DriveToDistance;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.MoveElevator;
import org.usfirst.frc.falcons6443.robot.commands.subcommands.MoveIntake;
import org.usfirst.frc.falcons6443.robot.subsystems.IntakeSystem;
import org.usfirst.frc.falcons6443.robot.utilities.enums.ElevatorPosition;
import org.usfirst.frc.falcons6443.robot.utilities.enums.IntakePosition;

/**
 * Command to get to leftside scale from second position
 *
 * @author Owen Engbretson, Goirick Saha
 */
public class RightToLeftScale extends CommandGroup {

        public RightToLeftScale(){
            addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, false, false, true));
            addSequential(new Delay(0.5));
            addSequential(new MoveElevator(ElevatorPosition.Scale));

            addSequential(new DriveToDistance(228));
            addSequential(new RotateToAngle(270));
            addSequential(new DriveToDistance(232));
            addSequential(new RotateToAngle(90));
            addSequential(new DriveToDistance(96));
            addSequential(new RotateToAngle(90));
            addSequential(new DriveToDistance(54));


            addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, false, false, false));
            addSequential(new Delay(2));
            addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, true, false, false));
            addSequential(new Delay(2));
            addSequential(new MoveIntake(IntakePosition.IntakeDownPosition, false, true, false));
        }

}
