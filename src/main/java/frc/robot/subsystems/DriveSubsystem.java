// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  public static class Hardware {
    public WPI_TalonSRX lFrontMasterMotor;
    public WPI_TalonSRX lMiddleSlaveMotor;
    public WPI_TalonSRX lBackSlaveMotor;
    public WPI_TalonSRX rFrontMasterMotor;
    public WPI_TalonSRX rMiddleSlaveMotor;
    public WPI_TalonSRX rBackSlaveMotor;

    public Hardware(WPI_TalonSRX lFrontMasterMotor, WPI_TalonSRX lMiddleSlaveMotor, WPI_TalonSRX lBackSlaveMotor, WPI_TalonSRX rFrontMasterMotor, WPI_TalonSRX rMiddleSlaveMotor, WPI_TalonSRX rBackSlaveMotor ) {
     this.lFrontMasterMotor = lFrontMasterMotor;
     this.lMiddleSlaveMotor = lMiddleSlaveMotor;
     this.lBackSlaveMotor = lBackSlaveMotor;
     this.rFrontMasterMotor = rFrontMasterMotor;
     this.rMiddleSlaveMotor = rMiddleSlaveMotor;
     this.rBackSlaveMotor = rBackSlaveMotor;
    }
  }
    public WPI_TalonSRX m_lFrontMasterMotor;
    public WPI_TalonSRX m_lMiddleSlaveMotor;
    public WPI_TalonSRX m_lBackSlaveMotor;
    public WPI_TalonSRX m_rFrontMasterMotor;
    public WPI_TalonSRX m_rMiddleSlaveMotor;
    public WPI_TalonSRX m_rBackSlaveMotor;


   private DifferentialDrive m_driveTrain;


  /** Creates a new TankSubsystem. */
  public DriveSubsystem(Hardware driveHardware) {
      this.m_lFrontMasterMotor = driveHardware.lFrontMasterMotor;
      this.m_lMiddleSlaveMotor = driveHardware.lMiddleSlaveMotor;
      this.m_lBackSlaveMotor = driveHardware.lBackSlaveMotor;
      this.m_rFrontMasterMotor = driveHardware.rFrontMasterMotor;
      this.m_rMiddleSlaveMotor = driveHardware.rMiddleSlaveMotor;
      this.m_rBackSlaveMotor = driveHardware.rBackSlaveMotor;

      m_driveTrain = new DifferentialDrive(m_lFrontMasterMotor, m_rFrontMasterMotor);
      m_rFrontMasterMotor.setInverted(true);
      m_rMiddleSlaveMotor.setInverted(true);
      m_rBackSlaveMotor.setInverted(true);
      m_rMiddleSlaveMotor.follow(m_rFrontMasterMotor);
      m_rBackSlaveMotor.follow(m_rFrontMasterMotor);
      m_lMiddleSlaveMotor.follow(m_lFrontMasterMotor);
      m_lBackSlaveMotor.follow(m_lFrontMasterMotor);
  }

  public static Hardware initializeHardware() {
    return new Hardware(
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_FRONT_MASTER_MOTOR_ID), 
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_MIDDLE_SLAVE_MOTOR_ID), 
      new WPI_TalonSRX(Constants.DriveHardware.LEFT_REAR_SLAVE_MOTOR_ID), 
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_FRONT_MASTER_MOTOR_ID), 
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_MIDDLE_SLAVE_MOTOR_ID), 
      new WPI_TalonSRX(Constants.DriveHardware.RIGHT_REAR_SLAVE_MOTOR_ID));
  }

  public void set(double speed, double rotation) {
    m_driveTrain.arcadeDrive(speed, rotation);
  }
  public void stop() {
    m_driveTrain.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
