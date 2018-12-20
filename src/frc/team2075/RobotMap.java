/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2075;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
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

        drivetrainTalonRight.setNeutralMode(NeutralMode.Brake);
        drivetrainTalonLeft.setNeutralMode(NeutralMode.Brake);
        drivetrainVictorRightRear.setNeutralMode(NeutralMode.Brake);
        drivetrainVictorRightFront.setNeutralMode(NeutralMode.Brake);
        drivetrainVictorLeftFront.setNeutralMode(NeutralMode.Brake);
        drivetrainVictorLeftRear.setNeutralMode(NeutralMode.Brake);

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

        drivetrainTalonLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        drivetrainTalonRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        drivetrainTalonLeft.setSensorPhase(true);
        drivetrainTalonRight.setSensorPhase(true);



    }
}
