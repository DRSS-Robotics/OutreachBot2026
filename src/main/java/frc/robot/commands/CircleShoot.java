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
public class CircleShoot extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    Outtake m_Outtake;
    Supplier<Double> speed;
    int bufferPosition;
    double[] circularBuffer = new double[OperatorConstants.outtakeBufferSize];

    public CircleShoot(Outtake outtake, Supplier<Double> motorSpeed) {
        m_Outtake = outtake;
        speed = motorSpeed;
        bufferPosition = 0;
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
        // Sets speed of shooter motor
        m_Outtake.shooterSpinUp(speed.get());
        // Puts the latest speed into circular buffer
        circularBuffer[bufferPosition] = speed.get();
        // Checks to see if Util range of circular buffer array is withen threshold,
        // meaning data is consistent
        if (Utils.range(circularBuffer) <= OperatorConstants.outtakeBufferThreshold) {
            // Motor go spin
            m_Outtake.primerSpinUp(1);
        }
        // Increment buffer position, using modulo to wrap the value back to 10 if it is
        // higher than 10
        bufferPosition = (bufferPosition + OperatorConstants.outtakeBufferIncrement)
                % OperatorConstants.outtakeBufferSize;
    }

    // Called once the command ends or is interrupted.
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
