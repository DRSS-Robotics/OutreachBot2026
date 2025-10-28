// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  SparkMax intakeMotor;

  /**
   * Creates a new instance of the Intake subsystem
   * @param intake Specifies the motor ID of the intake
   */
  public Intake(int intake) {
    intakeMotor = new SparkMax(intake, MotorType.kBrushless);
  }


  /**
   * Changes the speed of the intake.
   * @param speed Specifies the new speed of the intake.
   */
  public void runIntakeIn(double speed) {
    intakeMotor.set(speed);
  }
}