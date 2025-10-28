
package frc.robot;

import frc.robot.Constants.OperatorConstants;
//import frc.robot.commands.Autos;
import frc.robot.commands.Drive;
//import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeSpin;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Outtake;
import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final TankDrive m_TankDrive = new TankDrive(OperatorConstants.kLeftMotorDriveID, OperatorConstants.kRightMotorDriveID);
  private final Intake m_Intake = new Intake(OperatorConstants.kIntakeMotorID);
  private final Outtake m_Outtake = new Outtake(OperatorConstants.kPrimerMotorID,OperatorConstants.kShooterMotorID,OperatorConstants.kPitchMotorID);
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }

  
  private void configureBindings() {
    // Sets the command that runs when no other commands are using the tank drive
    m_TankDrive.setDefaultCommand(
      new Drive(
        m_TankDrive, 
        // This is how you get the value of a method in a class
        m_driverController::getLeftX, 
        m_driverController::getLeftY
      )
    );
    // Toggles the intake on and off when the user turns on and off the a button
    m_driverController.leftBumper().whileTrue(new IntakeSpin(m_Intake));
    m_driverController.rightTrigger(OperatorConstants.kShooterDeadband).whileTrue(
      new Shoot(m_Outtake,m_driverController::getRightTriggerAxis));
  }

  // public Command getAutonomousCommand() {
    // return Autos.exampleAuto(m_TankDrive);
  // }
}
