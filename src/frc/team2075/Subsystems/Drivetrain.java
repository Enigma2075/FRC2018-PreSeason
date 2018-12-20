package frc.team2075.Subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2075.Commands.DrivetrainCommands.TeleDrive;
import frc.team2075.ExternalClasses.CheesyDriveHelper;
import frc.team2075.ExternalClasses.DriveSignal;
import frc.team2075.RobotMap;

public class Drivetrain extends Subsystem {

    private TalonSRX talonLeft = RobotMap.drivetrainTalonLeft;
    private TalonSRX talonRight = RobotMap.drivetrainTalonRight;


    private CheesyDriveHelper cheesyHelper = new CheesyDriveHelper();

    public Drivetrain(){
        super("Drivetrain");
    }

    public void cheesyDrive(double throttle, double wheel, boolean quickTurn){
        //checks to see if the robot should be doing a zero point turn
        if (throttle < .05) {
            quickTurn = true;
        }

        //uses the cheesy drive helper to get the correct speeds based on joystick values
        DriveSignal signal = cheesyHelper.cheesyDrive(throttle * throttle * Math.signum(throttle), wheel * wheel * Math.signum(wheel), quickTurn);

        if (signal.rightMotor == 0) {
            talonRight.set(ControlMode.PercentOutput, 0);
        } else {
            talonRight.set(ControlMode.PercentOutput, signal.rightMotor /** maxVelocity*/);
        }

        if (signal.leftMotor == 0) {
            talonLeft.set(ControlMode.PercentOutput, 0);
        } else {
            talonLeft.set(ControlMode.PercentOutput, signal.leftMotor /** /*maxVelocity*/);
        }
    }
    public double getVelocity(){
        return (talonLeft.getSelectedSensorVelocity()+talonRight.getSelectedSensorVelocity())/2;
    }
    public void initDefaultCommand() {
        setDefaultCommand(new TeleDrive());
    }
}

