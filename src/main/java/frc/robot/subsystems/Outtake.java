// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.Supplier;

import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;

public class Outtake extends SubsystemBase {
  SparkMax primerMotor;
  SparkMax shooterMotor;
  SparkMax pitchMotor;
  SparkMaxConfig pMC;
  ClosedLoopConfig pMCLC;
  Supplier<Double> speedSupplier;

  public Outtake(int primerId, int shooterId, int pitchId, Supplier<Double> speedSupplier) {
    primerMotor = new SparkMax(primerId, MotorType.kBrushed);
    shooterMotor = new SparkMax(shooterId, MotorType.kBrushed);
    pitchMotor = new SparkMax(pitchId, MotorType.kBrushless);
    pMCLC = new ClosedLoopConfig().pid(1,1,1);
    pMC = new SparkMaxConfig();

    pMC.voltageCompensation(10);
    pMC.smartCurrentLimit(60);
    pMC.idleMode(IdleMode.kCoast);
    pMC.apply(pMCLC);

    pitchMotor.configure(pMC, ResetMode.kResetSafeParameters,  PersistMode.kNoPersistParameters);

    this.speedSupplier = speedSupplier;
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

  public void pitchSetPosition(double pos) {
    pitchMotor.getEncoder().setPosition(pos);
  }

  public void pitchSetSpeed(double speed) {
    pitchMotor.set(speed);
  }

  public void stopShoot() {
    primerMotor.set(0);
    shooterMotor.set(0);
  }

  @Override
  public void periodic() {
    double position = pitchMotor.getEncoder().getPosition();
    
    // Check if pitch motor is outside bounds or within deadband
    if (((OperatorConstants.kPitchMotorLowerBound >= position) && pitchMotor.get() < 0) || 
        ((OperatorConstants.kPitchMotorUpperBound <= position) && pitchMotor.get() > 0) ||
        (Math.abs(speedSupplier.get()) <= OperatorConstants.kPitchDeadband)) {
      // If outside bounds, do not move motor
      pitchMotor.set(0);
    } else {
      // If within bounds, set motor speed to right stick Y axis
      pitchMotor.set(-speedSupplier.get());
    }
  }
}