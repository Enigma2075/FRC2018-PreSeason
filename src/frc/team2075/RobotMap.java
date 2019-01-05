/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2075;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
    //DRIVETRAIN OBJECT DECLARATION

    //Drivetrain talon declaration
    public static TalonSRX drivetrainTalonLeft;
    public static TalonSRX drivetrainTalonRight;
    public static VictorSPX drivetrainVictorLeftFront;
    public static VictorSPX drivetrainVictorLeftRear;
    public static VictorSPX drivetrainVictorRightFront;
    public static VictorSPX drivetrainVictorRightRear;

    //Drivetrain Gyro declaration
    public static PigeonIMU pigeonIMU;

    public static void init(){

        //CONFIG DATA FOR DRIVETRAIN

        //configuration for what port each respective talon is running off of on the CAN Bus
        drivetrainTalonRight = new TalonSRX(4);
        drivetrainTalonLeft = new TalonSRX(5);
        drivetrainVictorLeftFront = new VictorSPX(6);
        drivetrainVictorLeftRear = new VictorSPX (7);
        drivetrainVictorRightFront = new VictorSPX (8);
        drivetrainVictorRightRear = new VictorSPX (9);

        //Sets up all of the motors to brake when no power is put to them
        drivetrainTalonRight.setNeutralMode(NeutralMode.Brake);
        drivetrainTalonLeft.setNeutralMode(NeutralMode.Brake);
        drivetrainVictorRightRear.setNeutralMode(NeutralMode.Brake);
        drivetrainVictorRightFront.setNeutralMode(NeutralMode.Brake);
        drivetrainVictorLeftFront.setNeutralMode(NeutralMode.Brake);
        drivetrainVictorLeftRear.setNeutralMode(NeutralMode.Brake);

        //Flips some of the motors so they will all go the right direction
        drivetrainVictorLeftFront.setInverted(false);
        drivetrainTalonRight.setInverted(true);
        drivetrainVictorRightRear.setInverted(true);
        drivetrainVictorRightFront.setInverted(true);

        //sets the rear talons as slaves of the front
        drivetrainVictorLeftFront.follow(drivetrainTalonLeft);
        drivetrainVictorLeftRear.follow(drivetrainTalonLeft);

        drivetrainVictorRightFront.follow(drivetrainTalonRight);
        drivetrainVictorRightRear.follow(drivetrainTalonRight);

        //sets the starting motor control mode to percent output at 0 power
        drivetrainTalonLeft.set(ControlMode.PercentOutput, 0);
        drivetrainTalonRight.set(ControlMode.PercentOutput, 0);

        //flips the sensors direction
        drivetrainTalonLeft.setSensorPhase(true);
        drivetrainTalonRight.setSensorPhase(true);

        //Sets up a current limit to prevent brownouts
        drivetrainTalonRight.configPeakCurrentLimit(50);
        drivetrainTalonLeft.configPeakCurrentLimit(50);
        drivetrainTalonRight.configPeakCurrentDuration(50);
        drivetrainTalonLeft.configPeakCurrentDuration(50);
        drivetrainTalonRight.configContinuousCurrentLimit(40);
        drivetrainTalonLeft.configContinuousCurrentLimit(40);

        //Enables the current limit
        drivetrainTalonRight.enableCurrentLimit(true);
        drivetrainTalonLeft.enableCurrentLimit(true);

        //Sets up a ramp rate to prevent brownouts
        drivetrainTalonRight.configOpenloopRamp(.050);
        drivetrainTalonLeft.configOpenloopRamp(.050);
        drivetrainTalonLeft.configClosedloopRamp(.050);
        drivetrainTalonRight.configClosedloopRamp(.050);

        //Sets up the left talon to use its own encoder
        drivetrainTalonLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        //Sets up the left mag encoder as  remote sensor 0 for the right talon
        drivetrainTalonRight.configRemoteFeedbackFilter(5, RemoteSensorSource.TalonSRX_SelectedSensor, 0);

        //Sets up the pigeon as remote sensor 1 for the right talon
        drivetrainTalonRight.configRemoteFeedbackFilter(0,RemoteSensorSource.Pigeon_Yaw,1);

        //configures the feedback device SensorSum to be equal to the sum of remote sensor zero(left mag encoder) and the right mag encoder
        drivetrainTalonRight.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, 10);
        drivetrainTalonRight.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, 10);

        //Sets up the SensorSum as the selected feedback sensor for the right talon PID index 0
        drivetrainTalonRight.configSelectedFeedbackSensor(FeedbackDevice.SensorSum,0,10);

        //Sets up the Pigeon as the selected feedback sensor for the right talon PID index 1
        drivetrainTalonRight.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1,1,10);

        //Sets up the feedback sensor to take the average of the two mag encoders
        drivetrainTalonRight.configSelectedFeedbackCoefficient(.5,0,10);

        //Sets up the correct ratio for degrees for the pigeon
        drivetrainTalonRight.configSelectedFeedbackCoefficient(3600.0/8192.0,1,10);

        //Configures the PIDF values for the position loop
        drivetrainTalonRight.config_kF(0,0);
        drivetrainTalonRight.config_kP(0,0);
        drivetrainTalonRight.config_kI(0,0);
        drivetrainTalonRight.config_kD(0,0);


        //Configures the PIDF values for the motion profile turning loop
        drivetrainTalonRight.config_kF(1,0);
        drivetrainTalonRight.config_kP(1,0);
        drivetrainTalonRight.config_kI(1,0);
        drivetrainTalonRight.config_kD(1,0);

        //Configures the PIDF values for the position turning loop
        drivetrainTalonRight.config_kF(2,0);
        drivetrainTalonRight.config_kP(2,0);
        drivetrainTalonRight.config_kI(2,0);
        drivetrainTalonRight.config_kD(2,0);
    }
}
