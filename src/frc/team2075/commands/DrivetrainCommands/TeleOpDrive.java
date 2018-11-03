package frc.team2075.commands.DrivetrainCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2075.Robot;


public class TeleOpDrive extends Command {

    public final double Deadzone = .11;
    public TeleOpDrive() {
        requires(Robot.drivetrain);
    }





    @Override
    protected void initialize() {

    }




    @Override
    protected void execute() {
        //uses the oi to setup the controller varialbes
        double yRightValue = Robot.oi.driver.getY(GenericHID.Hand.kRight);
        double xRightValue = Robot.oi.driver.getX(GenericHID.Hand.kRight);
        double yLeftValue = Robot.oi.driver.getY(GenericHID.Hand.kLeft);
        double xLeftValue = Robot.oi.driver.getX(GenericHID.Hand.kLeft);


        //applies a deadzone to the sticks to prevent the robot form moving when sticks are idle
        yRightValue = applyDeadzone(yRightValue, Deadzone);
        xRightValue = applyDeadzone(xRightValue, Deadzone);
        yLeftValue = applyDeadzone(yLeftValue, Deadzone);
        xLeftValue = applyDeadzone(xLeftValue, Deadzone);


        Robot.drivetrain.CheesyDriveWithSticks((-yLeftValue), (xRightValue), false);



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
}
