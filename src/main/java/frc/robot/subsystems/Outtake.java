// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.Supplier;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants;

public class Outtake extends SubsystemBase {
  SparkMax primerMotor;
  SparkMax shooterMotor;
  SparkMax pitchMotor;

  public Outtake(int primerId, int shooterId, int pitchId) {
    primerMotor = new SparkMax(primerId, MotorType.kBrushed);
    shooterMotor = new SparkMax(shooterId, MotorType.kBrushed);
    pitchMotor = new SparkMax(pitchId, MotorType.kBrushless);
  }
  // We are using Judah's solution before Charlie's
  // Judah wanted a preset delay to fire when the user starts pressing the trigger.
  // Charlie wanted a button that fires the tennis ball when pressed
  // Both solutions used R2 to set the power of the shot. This could be either gates or the direct analogue input.

  public void primerSpinUp(double speed) {
    primerMotor.set(speed);
  }
  
  public void shooterSpinUp(double speed) {
    shooterMotor.set(speed);
  }

  public void stopShoot() {
    primerMotor.set(0);
    shooterMotor.set(0);
    
  }
}
