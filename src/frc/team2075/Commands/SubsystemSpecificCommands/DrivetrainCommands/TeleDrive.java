package frc.team2075.Commands.DrivetrainCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2075.Robot;


public class TeleDrive extends Command {

    private final double dead = .1;
    public TeleDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {

    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        if(!DriverStation.getInstance().isAutonomous()) {
            //uses the oi to setup the controller varialbes
            double yRightValue = Robot.oi.driver.getY(GenericHID.Hand.kRight);
            double xRightValue = Robot.oi.driver.getX(GenericHID.Hand.kRight);
            double yLeftValue = Robot.oi.driver.getY(GenericHID.Hand.kLeft);
            double xLeftValue = Robot.oi.driver.getX(GenericHID.Hand.kLeft);


            //applies a deadzone to the sticks to prevent the robot form moving when sticks are idle
            yRightValue = applyDeadzone(yRightValue, dead);
            xRightValue = applyDeadzone(xRightValue, dead);
            yLeftValue = applyDeadzone(yLeftValue, dead);
            xLeftValue = applyDeadzone(xLeftValue, dead);

            //checks to see if the robot should be running in slow mode and if so limits the max power to 65%
            Robot.drivetrain.cheesyDrive((-yLeftValue), (xRightValue), false);
        }
    }



    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }



    @Override
    protected void end() {

    }



    @Override
    protected void interrupted() {
        super.interrupted();
    }
    private double applyDeadzone(double value, double deadzone) {
        double output = value;

        if (Math.abs(value) < deadzone) {
            output = 0;
        } else {
            output = (value - (Math.abs(value) / value * deadzone)) / (1 - deadzone);
        }
        return output;
    }

    private double applyMaximum(double input, double maximum) {
        double output;
        if (Math.abs(input) > maximum) output = input / Math.abs(input) * maximum;
        else output = input;
        return output;
    }

}
