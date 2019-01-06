package frc.team2075;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {

    private SendableChooser<Command> autonomousChooser;
    public Dashboard(){

        //Sets Up The autonomous chooser
        //autonomousChooser = new SendableChooser<Command>();
        //autonomousChooser.addDefault("Test", new TestAutonomous());


    }

    public void updateDashboard(){

        /**Drivetrain SmartDashboard Data*/
        SmartDashboard.putNumber("Raw Velocity", Robot.drivetrain.getRawVelocity());
        SmartDashboard.putNumber("Velocity", Robot.drivetrain.getVelocity());
        SmartDashboard.putNumber("Raw Position", Robot.drivetrain.getRawPosition());
        SmartDashboard.putNumber("Position", Robot.drivetrain.getPosition());
        SmartDashboard.putNumber("Heading", Robot.drivetrain.getHeading());
        SmartDashboard.putNumber("Primary Closed Loop Error", Robot.drivetrain.getClosedLoopError(0));
        SmartDashboard.putNumber("Selected Feedback Position", Robot.drivetrain.getSelectedFeedbackPosition());
        SmartDashboard.putNumber("Left Primary Closed Loop Error", Robot.drivetrain.getLeftClosedLoopError(0));
        SmartDashboard.putNumber("Left Selected Feedback Position", Robot.drivetrain.getLeftSelectedFeedbackSensor());

    }


}
