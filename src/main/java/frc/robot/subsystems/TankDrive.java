// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;;

public class TankDrive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  SparkMax leftDrive;
  SparkMax rightDrive;
  
  public TankDrive(int left, int right) {
    leftDrive = new SparkMax(left, MotorType.kBrushed);
    rightDrive = new SparkMax(right, MotorType.kBrushed);
  }
    
  public void driveAndTurn(double left_x, double right_y){
    leftDrive.set(left_x+right_y);
    rightDrive.set(left_x-right_y);
  }
}
