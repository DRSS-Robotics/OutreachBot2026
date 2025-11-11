// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Outtake;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Utils;

/** An example command that uses an example subsystem. */
public class ManualShoot extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    Outtake m_Outtake;
    Supplier<Double> speed;
    Supplier<Boolean> shouldFire;

    public ManualShoot(Outtake outtake, Supplier<Double> motorSpeed, Supplier<Boolean> isAPressed) {
        m_Outtake = outtake;
        speed = motorSpeed;
        shouldFire = isAPressed;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    /**
     * Does a thing as long as something is pressed.
     * 
     * @return nothing
     */
    @Override
    public void execute() {
        m_Outtake.shooterSpinUp(speed.get());
        if (shouldFire.get()) {
            m_Outtake.primerSpinUp(1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_Outtake.stopShoot();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
