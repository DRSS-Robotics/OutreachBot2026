// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.TankDrive;

import java.util.function.Function;
import java.util.function.Supplier;
import java.lang.Math;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Drive extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final TankDrive m_TankDrive;
  private final Supplier<Double> leftStick_x;
  private final Supplier<Double> leftStick_y;

  /**
   * Creates a command called Drive. This is a persistent command that will make the motors run tank drives.
   *
   * @param tankDrive The subsystem used by this command.
   */
  public Drive(TankDrive tankDrive, Supplier<Double> leftStickX, Supplier<Double> leftStickY) {
    m_TankDrive = tankDrive;
    leftStick_x = leftStickX;
    leftStick_y = leftStickY;
    // Use addRequirements() here to declare subsystem dependencies. this makes it so that no other subsytem can use this subsystem.
    addRequirements(tankDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_TankDrive.driveAndTurn(
      Math.abs(leftStick_x.get()) > OperatorConstants.kDriveDeadband? leftStick_x.get():0,
      Math.abs(leftStick_y.get()) > OperatorConstants.kDriveDeadband? leftStick_y.get():0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
