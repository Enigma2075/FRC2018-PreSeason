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
    public static TalonSRX drivetrainTalonLeftFront;
    public static TalonSRX drivetrainTalonLeftRear;
    public static TalonSRX drivetrainTalonRightFront;
    public static TalonSRX drivetrainTalonRightRear;
    //Drivetrain Gyro declaration
    public static PigeonIMU pigeonIMU;

    public static void init(){

        //CONFIG DATA FOR DRIVETRAIN

        //configuration for what port each respective talon is running off of on the CAN Bus
        drivetrainTalonLeftFront = new TalonSRX(4);
        drivetrainTalonLeftRear = new TalonSRX(5);
        drivetrainTalonRightFront = new TalonSRX(6);
        drivetrainTalonRightRear = new TalonSRX(7);
        //sets the rear talons as slaves of the front
        drivetrainTalonLeftRear.set(ControlMode.Follower, 4);
        drivetrainTalonRightRear.set(ControlMode.Follower, 6);

        //configures the channel for the solenoids
        drivetrainDoubleSolenoidRightShift = new DoubleSolenoid(3, 0, 1);
        drivetrainDoubleSolenoidLeftShift = new DoubleSolenoid(3, 2, 3);

        //puts the rear drivetrain talons into follower mode which will allow them to follow their respective front talons
        drivetrainTalonLeftFront.setInverted(true);
        drivetrainTalonLeftRear.setInverted(true);
        drivetrainTalonRightFront.setInverted(false);
        drivetrainTalonRightRear.setInverted(false);

        //configs the voltage saturation
        drivetrainTalonLeftFront.configVoltageCompSaturation(24, 64);
        drivetrainTalonRightRear.configVoltageCompSaturation(24, 64);

        //sets the sensor to a quad encoder
        drivetrainTalonLeftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        drivetrainTalonRightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        //sets the starting motor control mode to percent output at 0 power
        drivetrainTalonLeftFront.set(ControlMode.PercentOutput, 0);
        drivetrainTalonRightFront.set(ControlMode.PercentOutput, 0);

        //sets the ramp rate from idle to full power
        drivetrainTalonLeftFront.configClosedloopRamp(0, 0);
        drivetrainTalonRightFront.configClosedloopRamp(0, 0);
        drivetrainTalonLeftFront.configOpenloopRamp(0, 0);
        drivetrainTalonRightFront.configOpenloopRamp(0, 0);

        drivetrainTalonLeftFront.configPeakOutputForward(1,0);
        drivetrainTalonLeftRear.configPeakOutputForward(1,0);
        drivetrainTalonRightFront.configPeakOutputForward(1,0);
        drivetrainTalonRightRear.configPeakOutputForward(1,0);

        drivetrainTalonLeftFront.configPeakOutputReverse(-1,0);
        drivetrainTalonLeftRear.configPeakOutputReverse(-1,0);
        drivetrainTalonRightFront.configPeakOutputReverse(-1,0);
        drivetrainTalonRightRear.configPeakOutputReverse(-1,0);

        drivetrainTalonLeftFront.configNominalOutputForward(0,0);
        drivetrainTalonLeftRear.configNominalOutputForward(0,0);
        drivetrainTalonRightFront.configNominalOutputForward(0,0);
        drivetrainTalonRightRear.configNominalOutputForward(0,0);

        drivetrainTalonLeftFront.configNominalOutputReverse(0,0);
        drivetrainTalonLeftRear.configNominalOutputReverse(0,0);
        drivetrainTalonRightFront.configNominalOutputReverse(0,0);
        drivetrainTalonRightRear.configNominalOutputReverse(0,0);



        //sets the default action if the talons are idle
        drivetrainTalonLeftFront.setNeutralMode(NeutralMode.Brake);
        drivetrainTalonRightFront.setNeutralMode(NeutralMode.Brake);
        drivetrainTalonRightRear.setNeutralMode(NeutralMode.Coast);
        drivetrainTalonLeftRear.setNeutralMode(NeutralMode.Coast);

        //configs the max velocity of the drivtrain in motion magic
        drivetrainTalonLeftFront.configMotionCruiseVelocity(750, 64);
        drivetrainTalonRightFront.configMotionCruiseVelocity(750, 64);

        drivetrainTalonLeftFront.configMotionAcceleration(600, 64);
        drivetrainTalonRightFront.configMotionAcceleration(600, 64);

        drivetrainTalonLeftFront.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_20Ms, 0);
        drivetrainTalonRightFront.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_20Ms, 0);
        drivetrainTalonLeftFront.configVelocityMeasurementWindow(4, 0);
        drivetrainTalonRightFront.configVelocityMeasurementWindow(4, 0);

        //sets the direction of the sensor on the motors
        drivetrainTalonRightFront.setSensorPhase(false);
        drivetrainTalonLeftFront.setSensorPhase(false);

        //disables the soft limits for the motors
        drivetrainTalonLeftFront.configForwardSoftLimitEnable(false, 0);
        drivetrainTalonRightFront.configForwardSoftLimitEnable(false, 0);
        drivetrainTalonLeftFront.configReverseSoftLimitEnable(false, 0);
        drivetrainTalonRightFront.configReverseSoftLimitEnable(false, 0);

        drivetrainTalonLeftFront.getSensorCollection().setQuadraturePosition(0,0);
        drivetrainTalonRightFront.getSensorCollection().setQuadraturePosition(0,0);

    }
}
