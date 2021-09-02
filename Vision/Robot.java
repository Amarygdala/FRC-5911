/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.vision.VisionThread;

import org.opencv.core.Mat;
/**
 * This is a demo program showing the use of OpenCV to do vision processing. The
 * image is acquired from the USB camera, then a rectangle is put on the image
 * and sent to the dashboard. OpenCV has many methods for different types of
 * processing.
 */

public class Robot extends TimedRobot {
  NetworkTableEntry xEntry;
  NetworkTableEntry yEntry;
  Victor left;
  Victor right;
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("testTable");

  @Override
  public void robotInit() {

    xEntry=table.getEntry("X");
    yEntry=table.getEntry("Y");



    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(640, 480);
      VisionThread visionThread = new VisionThread(camera, new OrangePipeline(), pipeline -> {
      if(!pipeline.filterContoursOutput().isEmpty()){
        Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
        xEntry.setDouble(r.x + (r.width / 2));
        //r.y + (r.height/2) 
        //System.out.println(xEntry.getDouble(0.0)+", "+yEntry.getDouble(0.0));
      }else{
        xEntry.setDouble(0);
        //System.out.println("False");
      }

    });visionThread.start();

  } //ip Rio 169.254.203.160
  @Override
  public void autonomousPeriodic() {
      double turn = xEntry.getDouble(0.0);
      System.out.println(turn);
    

      if(turn<340&&turn>300){
        left.set(-0.3);
        right.set(0.3);
      }else if(turn<=300){
        left.set(-.3);
        right.set(0);
      }else if(turn>=340){
        left.set(0);
        right.set(.5);
      }else{
        left.set(0);
        right.set(0);
      }
     // drive.tankDrive(turn, turn*-1);
  }
  @Override
  public void robotPeriodic() {
  
  }
}
