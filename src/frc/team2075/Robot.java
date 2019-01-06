
package frc.team2075;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2075.Commands.TurnAbsolute;
import frc.team2075.Subsystems.Drivetrain;


public class Robot extends TimedRobot
{
    /** Subsystem Declaration */
    public static OI oi;
    public static Drivetrain drivetrain;
    public static Compressor compressor;
    public static Dashboard dashboard;

    /**Code run on robot startup*/
    @Override
    public void robotInit() {
        //Initializes Electronics in the RobotMap
        RobotMap.init();

        //Defines and sets up the compressor to not overflow
        compressor = new Compressor(3);
        compressor.setClosedLoopControl(true);

        //Defines all of the Subsystems
        drivetrain = new Drivetrain();

        //Sets up the Smart Dashboard
        dashboard = new Dashboard();
        //Defines the Operator Interface so the robot can respond to inputs
        oi = new OI();
    }

    /**Code run continuously while robot is powered up*/
    @Override
    public void robotPeriodic(){
        dashboard.updateDashboard();
    }

    /**Code run when you disable the robot*/
    @Override
    public void disabledInit() {
        
    }

    /**Code run repeatedly when the robot is disabled*/
    @Override
    public void disabledPeriodic() 
    {
        Scheduler.getInstance().run();
    }


    /**Code Run when autonomous is started*/
    @Override
    public void autonomousInit() {
        Command auto = new TurnAbsolute(90);
        auto.start();
    }


    /**Code run repeatedly while autonomous is running*/
    @Override
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    /**Code run when Tele-Op is started*/
    @Override
    public void teleopInit() {

    }


    /**Code run repeatedly when Tele-Op is running*/
    @Override
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
    }

}
