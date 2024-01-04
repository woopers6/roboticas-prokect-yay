// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.DriveHardware;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public final DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem(DriveSubsystem.initializeHardware());

  private static final SendableChooser<SequentialCommandGroup> m_autoModeChooser = new SendableChooser<SequentialCommandGroup>();

   // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController xboxController = new CommandXboxController(Constants.OperatorConstants.kDriverConntrollerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    DRIVE_SUBSYSTEM.setDefaultCommand(new RunCommand(() -> DRIVE_SUBSYSTEM.set(xboxController.getLeftX(), xboxController.getRightX()), DRIVE_SUBSYSTEM));
       
    // Configure the button bindings 
    configureBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureBindings() {}

  private void m_autoModeChooser() {
    m_autoModeChooser.addOption("Do Nothing", new SequentialCommandGroup());                
    m_autoModeChooser.addOption("Drive Forward", new SequentialCommandGroup(DRIVE_SUBSYSTEM.run(() -> DRIVE_SUBSYSTEM.set(0.5, 0.0)).withTimeOut(5).andThen(() -> DRIVE_SUBSYSTEM.stop())));
    m_autoModeChooser.addOption("Drive Right", new SequentialCommandGroup(DRIVE_SUBSYSTEM.run(() -> DRIVE_SUBSYSTEM.set(0.5, -0.5)).withTimeOut(5).andThen(() -> DRIVE_SUBSYSTEM.stop())));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoModeChooser.getSelected();
  }
}
