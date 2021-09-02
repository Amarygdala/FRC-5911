/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private final XboxController stick = new XboxController(0);
  private Servo s;
  private Servo s2;
  @Override
  public void robotInit() {
    //m_myRobot = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(1));
    s = new Servo(0);
    s2 = new Servo(1);



  }

  @Override
  public void teleopPeriodic() {
    if(stick.getAButton()){
      s.setAngle(180);
      s2.setAngle(180);
    }else if(stick.getBButton()){
      s.close();
      s2.close();
    }else{
      s.set(0);
      s2.set(0);
    }
 
  System.out.println(s.getAngle());
    m_myRobot.tankDrive(stick.getY(Hand.kLeft), stick.getY(Hand.kLeft));
    
  }

}
