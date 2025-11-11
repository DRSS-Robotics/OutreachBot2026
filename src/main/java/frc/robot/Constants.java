// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    // TODO: Find the right motor IDs
    public static final int kRightMotorDriveID = 0;
    public static final int kLeftMotorDriveID = 1;
    public static final int kIntakeMotorID = 2;
    public static final int kPrimerMotorID = 3;
    public static final int kShooterMotorID = 4;
    public static final int kPitchMotorID = 5;

    public static final double kPitchMotorLowerBound = 0;
    public static final double kPitchMotorUpperBound = 30;

    public static final double kDriveDeadband = 0.1;
    public static final double kShooterDeadband = 0.1;
    public static final double kPitchDeadband = 0.1;

    public static final double kSecondsDelay = 1.5;

    public static final int outtakeBufferSize = 25;
    public static final int outtakeBufferIncrement = 1;
    public static final double outtakeBufferThreshold = 0.067; // ironically 0.067 works pretty well as the threshold
  }
}
