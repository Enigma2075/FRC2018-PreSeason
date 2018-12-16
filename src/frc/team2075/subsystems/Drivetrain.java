package frc.team2075.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2075.Commands.TeleopDrive;
import frc.team2075.ExternalClasses.CheesyDriveHelper;
import frc.team2075.ExternalClasses.DriveSignal;
import frc.team2075.RobotMap;

public class Drivetrain extends Subsystem {

private TalonSRX motorL = RobotMap.drivetrainTalonLeftFront;
private TalonSRX motorR = RobotMap.drivetrainTalonRightFront;

private DoubleSolenoid leftShift = RobotMap.drivetrainDoubleSolenoidLeftShift;
private DoubleSolenoid rightShift = RobotMap.drivetrainDoubleSolenoidRightShift;

private CheesyDriveHelper cheesyDriveHelper = new CheesyDriveHelper();
private PigeonIMU gyro = RobotMap.pigeonIMU;
    public Drivetrain(){
        super("Drivetrain");
    }

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopDrive());
    }
    public void cheezyDrive(double throttle, double turn){
        DriveSignal driveSignal = cheesyDriveHelper.cheesyDrive(throttle, turn, false);
        motorL.set(ControlMode.PercentOutput, driveSignal.leftMotor);
        motorR.set(ControlMode.PercentOutput, driveSignal.rightMotor);
    }
    public void driveTwo(int distance)
    {
        motorL.selectProfileSlot(0,0);
        motorR.selectProfileSlot(0,0);
        double totalDistanceL = calculateCount(distance)+motorL.getSelectedSensorPosition();
        double totalDistanceR = calculateCount(distance) + motorR.getSelectedSensorPosition();
        motorL.set(ControlMode.MotionMagic, totalDistanceL);
        motorR.set(ControlMode.MotionMagic, totalDistanceR);
    }

    public double calculateCount(double distance)
    {
        double output = 6.25*Math.PI;
        output = (output/distance)*1023;
        return output;
    }
}


