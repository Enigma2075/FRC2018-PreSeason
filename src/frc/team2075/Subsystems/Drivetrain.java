package frc.team2075.Subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2075.ExternalClasses.CheesyDriveHelper;
import frc.team2075.ExternalClasses.DriveSignal;
import frc.team2075.RobotMap;
import frc.team2075.Commands.TeleDrive;

public class Drivetrain extends Subsystem {

    private TalonSRX talonLeft = RobotMap.drivetrainTalonLeft;
    private TalonSRX talonRight = RobotMap.drivetrainTalonRight;
    private PigeonIMU pigeon = RobotMap.pigeonIMU;

    final private double INCHESPERROTATION = 4*Math.PI;
    final private double TICKSPERROTATION = 4096;

    private CheesyDriveHelper cheesyHelper = new CheesyDriveHelper();

    public Drivetrain(){
        super("Drivetrain");
    }

    /**DRIVETRAIN CONTROL METHODS*/

    /**@param throttle forward/backwards power variable scale -1,1
     * @param wheel  left/right turn variable scale -1,1
     * @param quickTurn whether you are turning in place or not*/
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

    /**@param inches number of inches to move
     * @return number of ticks based on how far to move*/
    public double calculateCounts(double inches){
        return (inches/INCHESPERROTATION)*TICKSPERROTATION;
    }

    /**@param degrees to turn relative to current position*/
    public void turn(double degrees){
        turnTo(degrees+getHeading());
    }

    /**@param degrees to turn relative to your starting position*/
    public void turnTo(double degrees){
        talonRight.selectProfileSlot(2,0);
        talonLeft.selectProfileSlot(2,0);
        talonRight.set(ControlMode.Position,(degrees+360)%360);

    }

    /**Stops the robot by setting power output to zero*/
    public void stop() {
        talonLeft.set(ControlMode.PercentOutput, 0);
        talonRight.set(ControlMode.PercentOutput, 0);
    }
    /***/
   /* public BufferedTrajectoryPointStream  injectProfileBuffer(double[][] profile){
        TrajectoryPoint point = new TrajectoryPoint();
        for(int i=0; i<profile.length; i++){
            point.position = profile[i][0];
            point.velocity = profile[i][1];
            point.auxiliaryPos = profile[i][2];
            point.timeDur = TrajectoryPoint.TrajectoryDuration.Trajectory_Duration_10ms;
            point.isLastPoint = (i+1) == profile.length;
            buffered
        }
        talonRight.motionProfile
    }*/
    /**Resets the mag encoders to 0*/
    public void resetEncoders(){
        talonRight.selectProfileSlot(0,0);
        talonRight.setSelectedSensorPosition(0,0,10);
        talonLeft.setSelectedSensorPosition(0,0,10);
    }

    /**TALON DATA*/

    /**@return velocity in raw sensor ticks per second*/
    public int getRawVelocity(){
        return talonRight.getSelectedSensorVelocity();
    }

    /**@return velocity in inches per seconds*/
    public double getVelocity(){
        return (getRawVelocity()/TICKSPERROTATION)*INCHESPERROTATION;
    }

    /**@return position in raw sensor ticks*/
    public int getRawPosition(){
        return talonRight.getSelectedSensorPosition();
    }

    /**@return position in inches per second*/
    public double getPosition(){
        return (getRawPosition()/TICKSPERROTATION)*INCHESPERROTATION;
    }

    /**@return heading of the pigeon*/
    public double getHeading(){
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        return ypr[0];
    }

    public double getSelectedFeedbackPosition(){
        return talonRight.getSelectedSensorPosition(0);
    }
    /**@param PIDIdx which pid index we look at 0= primary, 1 = aux
     * @return the closed loop error on the specified loop*/
    public double getClosedLoopError(int PIDIdx){
        return talonRight.getClosedLoopError(PIDIdx);
    }

    public double getLeftClosedLoopError(int PIDIdx) {
        return talonLeft.getClosedLoopError(PIDIdx);
    }

    public double getLeftSelectedFeedbackSensor(){
        return talonLeft.getSelectedSensorPosition(0);
    }

    /**Selects TeleDrive as the default command to run when the subsystem doesn't have any other commands*/
    public void initDefaultCommand() {
        setDefaultCommand(new TeleDrive());
    }

}

