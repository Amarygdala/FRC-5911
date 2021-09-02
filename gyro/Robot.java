/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.SPI;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Victor v1 = new Victor(0);
  Victor v2 = new Victor(1);
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  DifferentialDrive drive = new DifferentialDrive(v1, v2);
  private final XboxController stick = new XboxController(0);
  private boolean gyroCheck=false;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    gyro.calibrate();

    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    System.out.println(gyro.getAngle());
    
  }
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  @Override
  public void autonomousPeriodic() {
    
   
    double error = -gyro.getAngle();
    drive.tankDrive(-.5 + 1.0 * error/30, -.5 - 1.0 * error/30);

  }
  @Override
  public void teleopInit() {
    gyro.reset();
  }
  @Override
  public void teleopPeriodic() {
    double error = -gyro.getAngle();
    if(stick.getAButton()){
      if(!gyroCheck){
        gyro.reset();
        gyroCheck = true;
      }
    drive.tankDrive(stick.getY(Hand.kLeft)+ 1.0 * error/30, stick.getY(Hand.kLeft)- 1.0 * error/30);
  }else{
    drive.tankDrive(stick.getY(Hand.kRight), stick.getY(Hand.kLeft));
  }
  if(stick.getAButtonReleased()){
    gyroCheck = false;
  }
  }

}
