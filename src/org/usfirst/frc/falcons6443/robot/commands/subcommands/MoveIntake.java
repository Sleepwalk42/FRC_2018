package org.usfirst.frc.falcons6443.robot.commands.subcommands;

import org.usfirst.frc.falcons6443.robot.commands.SimpleCommand;
import org.usfirst.frc.falcons6443.robot.utilities.enums.IntakePosition;

public class MoveIntake extends SimpleCommand {

    private IntakePosition m_position;
    private boolean m_output;
    private boolean m_stop;
    private boolean m_reset;
    private boolean m_off;

    public MoveIntake(IntakePosition position, boolean output, boolean stop, boolean reset){
        super("Move Elevator System");
        requires(intake);
        m_position = position;
        m_off = false;
        m_output = output;
        m_stop = stop;
        m_reset = reset;
    }

    @Override
    public void initialize() {
        intake.setIntakePosition(m_position);
        if(m_reset){
            intake.reset();
        }
    }

    @Override
    public void execute() {
        if (m_output){
            intake.output();
        }
        if(m_stop){
            intake.stop();
        }
        m_off = true;
    }

    @Override
    public boolean isFinished() {
        return m_off;
    }
}