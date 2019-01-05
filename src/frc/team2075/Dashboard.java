package frc.team2075;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2075.commands.FullAutonomousCommandGroups.TestAutonomous;

public class Dashboard {

    private SendableChooser<Command> autonomousChooser;
    public Dashboard(){

        //Sets Up The autonomous chooser
        autonomousChooser = new SendableChooser<Command>();
        autonomousChooser.addDefault("Test", new TestAutonomous());
    }

    public void updateDashboard(){

        /**Drivetrain SmartDashboard Data*/
        SmartDashboard.putNumber("Raw Velocity", Robot.drivetrain.getRawVelocity());
        SmartDashboard.putNumber("Velocity", Robot.drivetrain.getVelocity());
        SmartDashboard.putNumber("Raw Position", Robot.drivetrain.getRawPosition());
        SmartDashboard.putNumber("Position", Robot.drivetrain.getPosition());

    }


}
