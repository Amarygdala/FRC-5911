/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.SPI;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class Robot extends TimedRobot {
  //driving
  private DifferentialDrive drive;
  private final XboxController stick1 = new XboxController(0);
  private final XboxController stick2 = new XboxController(1);
  SpeedControllerGroup leftDriveMotors = new SpeedControllerGroup(new PWMVictorSPX(0), new PWMVictorSPX(1));
  SpeedControllerGroup rightDriveMotors = new SpeedControllerGroup(new PWMVictorSPX(2), new PWMVictorSPX(3));
  //gyro
  Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
  private boolean gyroCheck=false;
  //pneumatics
  Compressor c;
  private final DoubleSolenoid colorSolenoid = new DoubleSolenoid(0, 1);
  private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(2, 3);
  //color wheel
  Victor wheelMotor = new Victor(6);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  String colorString;
  //camera
  UsbCamera camera1;
  //pickup
  Victor pickup = new Victor(5);
  //vision
  NetworkTableEntry xEntry;
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("getG");
  private final Timer timer = new Timer();

  @Override
  public void robotInit() {
    drive = new DifferentialDrive(leftDriveMotors,rightDriveMotors);
    gyro.calibrate();
    c = new Compressor();
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 
    camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    xEntry=table.getEntry("X");
  }
  @Override
  public void robotPeriodic() {
    /**
    

  

    Color detectedColor = m_colorSensor.getColor();

  
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
     */
  }
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    //move back across the line
    //turn towards goal
    //go towards goal
    //align with goal
    //open gates and shoot
    
    /*double turn = xEntry.getDouble(0.0);
    if(timer.get()<2.0){
      drive.tankDrive(-.3, -.3);
    }else{
      drive.stopMotor();
    }
    else if(timer.get()<6.0){
      drive.tankDrive(.3, .3);
    }
    System.out.println(turn);
  */
  }

  @Override
  public void teleopInit() {
    gyro.reset();
  }

  @Override
  public void teleopPeriodic() {

/* driving
    double error = -gyro.getAngle();
    if(stick1.getAButton()){
      if(!gyroCheck){
        gyro.reset();
        gyroCheck = true;
      }
      drive.tankDrive(stick1.getY(Hand.kLeft)+ 1.0 * error/30, stick1.getY(Hand.kLeft)- 1.0 * error/30);
    }else{
      drive.tankDrive(stick1.getY(Hand.kRight), stick1.getY(Hand.kLeft));
    }
    if(stick1.getAButtonReleased()){
      gyroCheck = false;
    }
    */
  /* pickup
    if(stick2.getTriggerAxis(Hand.kLeft)>0.8){
      //servo turns
    }else{
        //servo returns
    }
  
    if(stick2.getTriggerAxis(Hand.kRight)>0.8){
      pickup.set(1);
    }else{
      pickup.set(0);
    }
    */
        /* pneumatics
    c.setClosedLoopControl(true);

    if (stick2.getXButton()) {
      colorSolenoid.set(DoubleSolenoid.Value.kForward);
    } else if (stick2.getYButton()) {
      colorSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    */
    /*color wheel
    if(stick2.getAButton()){
      wheelMotor.set(.1);
    }else if(stick2.getBButton()){
      wheelMotor.set(.5);
    }else{
      wheelMotor.set(0);
    }
    */
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
