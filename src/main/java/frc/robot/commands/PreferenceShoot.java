// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;

/** An example command that uses an example subsystem. */
public class PreferenceShoot extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

    Command selectedCommand;
    Command[] allCommands;

    public PreferenceShoot(Command[] commands) {
        allCommands = commands;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println(SmartDashboard.getBoolean("useCharlieInput", OperatorConstants.kUseCharlieInput));
        selectedCommand = SmartDashboard.getBoolean("useCharlieInput", OperatorConstants.kUseCharlieInput) ? 
                        allCommands[0] : allCommands[1];
        selectedCommand.initialize();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        selectedCommand.execute();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        selectedCommand.end(interrupted);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return selectedCommand.isFinished();
    }
}
