import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.port.Port;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * The main method, and Arbitrator for the RobotArm behaviors.
 *
 * Behaviors have priorities according to their index in the Behavior[]
 * from [0] (lowest), to [n] (highest) where n is last index of array.
 *
 * The behaviors in the array are continually queried by the Arbitrator (from highest
 * to lowest index) through their takeControl() methods.
 *
 * If a takeControl() method returns true, and the class has a higher priority index
 * than the currently active behavior, the new behavior is given control.
 *
 * If more than one behavior's takeControl() method returns true, the behavior with the highest
 * priority (array index) is given control.
 *
 * This executes the behavior's action() method. The action() method details the exact behavior
 * to be taken while it is in control.
 *
 * An action() method is kept active by a boolean variable (suppressed = false),
 * and should finish when suppressed = true.
 *
 * When the action() method finishes, it should put the robot in a safe state,
 * e.g. stop the motors, before control is relinquished back to the Arbitrator.
 *
 * The behavior will not relinquish control until either the action() method is finished,
 * or if the behavior's suppress() method is called.
 *
 * A behavior's suppress() method sets the behavior's boolean suppressed field to true,
 * which is the action() method's exit condition.
 *
 * Calling the suppress() method of the behavior that is currently in control should immediately and safely
 *
 * @author Olav Valle
 * @version 04/11/19
 */

public class ArmMain {

    private static Vertical vertical;
    private static Horizontal horizontal;
    private static Claw claw;
    private static SenseColour colour;
    private static Pressure pressure;
    private static EV3 ev3;
    private static Port s1;
    private static Port s2;
    private static Port s3;



    public static void main(String[] args)
    {
        //brick
        ev3 = (EV3) BrickFinder.getDefault();

        //sensor ports
        s1 = ev3.getPort("S1");
        s2 = ev3.getPort("S2");
        s3 = ev3.getPort("S3");

        //sensors
        colour = new SenseColour(s3, s1);
        pressure = new Pressure(s2);

        //motors
        vertical = new Vertical();
        horizontal = new Horizontal();
        claw = new Claw();


        // Behaviors
        Behavior white = new MoveWhite(horizontal, colour, pressure);
        Behavior black = new MoveBlack(horizontal, colour, pressure);
        Behavior home = new MoveHome(pressure, horizontal);
        Behavior wait = new Wait();


        // Behavior array
        Behavior[] bArray = { wait, home, white, black };

        // Arbitrator
        Arbitrator arb = new Arbitrator(bArray);

        boolean cont = true;
        while(cont) {
            switch (Button.waitForAnyPress()) {
                case (Button.ID_ENTER):
                    arb.go();
                    cont = false;
                    break;
                case (Button.ID_ESCAPE):
                    arb.stop();
                    cont = false;
                    break;
                case (Button.ID_LEFT):
                    horizontal.rotateTo(-280);
                    break;
                case (Button.ID_RIGHT):
                    horizontal.rotateTo(0);
                    break;
                case(Button.ID_UP):
                    home.action();
            }
        }
        //TODO add app shutdown with ESCAPE button
    }

    //TODO add lejos.hardware.Button functions for testing of behaviors separately
    // i.e. up for Home, left for 90 deg, down for 180 deg, right for calibrate.
    // center button to activate/deactivate arbitrator?

    //TODO start with arbitrator off, and activate manually?

    //TODO possible to call a behavior directly with Behavior.action?
}
