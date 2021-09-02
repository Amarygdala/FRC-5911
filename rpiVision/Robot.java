/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  NetworkTableEntry xEntry;
  NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
  NetworkTable table = ntinst.getTable("orangePos");
  Servo s1 = new Servo(0);
  Servo s2 = new Servo(1);
  UsbCamera camera1;
  private final XboxController stick = new XboxController(0);
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    xEntry = table.getEntry("X");
    camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
  }
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    System.out.println(xEntry.getDouble(0.0)); 
    double angle = xEntry.getDouble(0.0);
    s1.setAngle(angle);
    s2.setAngle(angle);
    if(stick.getBButton()){
      s1.close();
      s2.close();
    }
  }
}
