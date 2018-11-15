/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2075;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    boolean OperatorControllerActive = true;
    boolean DriverControllerActive = true;
    public static XboxController driver;
    public static XboxController operator;

    //File for controlling the robot in teleop
    public OI(){
        try {
            driver = new XboxController(0);
        } catch (Exception e) {
            DriverControllerActive = false;
        }
        try {
            operator = new XboxController(1);
        } catch (Exception e) {
            OperatorControllerActive = false;
        }

        if(DriverControllerActive){

        }

        if(OperatorControllerActive){

        }
    }
}
