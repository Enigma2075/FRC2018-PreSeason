package frc.team2075.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2075.ExternalClasses.CheesyDriveHelper;
import frc.team2075.ExternalClasses.DriveSignal;
import frc.team2075.RobotMap;
import frc.team2075.commands.DrivetrainCommands.TeleOpDrive;

public class Drivetrain extends Subsystem {

    private final TalonSRX talonRight = RobotMap.drivetrainTalonRightFront;
    private final TalonSRX talonLeft = RobotMap.drivetrainTalonLeftFront;
    private final DoubleSolenoid doublesolenoidRightShift = RobotMap.drivetrainDoubleSolenoidRightShift;
    private final DoubleSolenoid doublesolenoidLeftShift = RobotMap.drivetrainDoubleSolenoidLeftShift;
    private final PigeonIMU pigeonIMU = RobotMap.pigeonIMU;
    private CheesyDriveHelper cheesyHelper = new CheesyDriveHelper();
    public Drivetrain(){
        super("Drivetrain");
    }


    public void CheesyDriveWithSticks(double leftStick, double rightStick, boolean quickTurn) {
        //checks to see if the robot should be doing a zero point turn
        if (leftStick < .05) {
            quickTurn = true;
        }

        //uses the cheesy drive helper to get the correct speeds based on joystick values
        DriveSignal signal = cheesyHelper.cheesyDrive(leftStick * leftStick * Math.signum(leftStick), rightStick * rightStick * Math.signum(rightStick), quickTurn);

        if (signal.rightMotor == 0) {
            talonRight.set(ControlMode.PercentOutput, 0);
        } else {
            talonRight.set(ControlMode.PercentOutput, signal.rightMotor);
        }

        if (signal.leftMotor == 0) {
            talonLeft.set(ControlMode.PercentOutput, 0);
        } else {
            talonLeft.set(ControlMode.PercentOutput, signal.leftMotor);
        }
    }
    public void initDefaultCommand() {
        setDefaultCommand(new TeleOpDrive());
    }
}

