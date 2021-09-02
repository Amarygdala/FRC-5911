/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a sample program showing the use of the solenoid classes during operator control. Three
 * buttons from a joystick will be used to control two solenoids: One button to control the position
 * of a single solenoid and the other two buttons to control a double solenoid. Single solenoids can
 * either be on or off, such that the air diverted through them goes through either one channel or
 * the other. Double solenoids have three states: Off, Forward, and Reverse. Forward and Reverse
 * divert the air through the two channels and correspond to the on and off of a single solenoid,
 * but a double solenoid can also be "off", where the solenoid will remain in its default power off
 * state. Additionally, double solenoids take up two channels on your PCM whereas single solenoids
 * only take a single channel.
 */

public class Robot extends TimedRobot {

  Victor v1;
  Victor v2;
  Compressor c;



  @Override
  public void robotInit() {
    v1 = new Victor(0);
    v2 = new Victor(2);
    c = new Compressor();
  }

  private final Joystick m_stick = new Joystick(0);


  // DoubleSolenoid corresponds to a double solenoid.
  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(0, 1);
  private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(2, 3);
  private static final int kDoubleSolenoidForward = 1;
  private static final int kDoubleSolenoidReverse = 2;

  @Override
  public void teleopPeriodic() {
    c.setClosedLoopControl(true);
    /*
     * In order to set the double solenoid, if just one button
     * is pressed, set the solenoid to correspond to that button.
     * If both are pressed, set the solenoid will be set to Forwards.
     */
    v1.set(m_stick.getRawAxis(5));
    if (m_stick.getRawButton(kDoubleSolenoidForward)) {
      m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
      doubleSolenoid.set(DoubleSolenoid.Value.kForward);
      //v2.set(.4);
    } else if (m_stick.getRawButton(kDoubleSolenoidReverse)) {
      m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
      doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
      //v1.set(0);
      //v2.set(0);
    }
  }
}
