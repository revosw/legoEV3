import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.port.Port;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * The selection menu for starting robot functions
 */
public class Ball {

    private Vertical vertical;
    private Horizontal horizontal;
    private Claw claw;
    private SenseColour colour;
    private Pressure pressure;
    private EV3 ev3;
    private Port s1;
    private Port s2;
    private Port s3;
    private MoveHome home;
    private MoveBlack black;
    private MoveWhite white;
    private Wait wait;
    private Arbitrator arb;
    private Behavior[] bArray;

    //fields detailing the degree limits of various positions
    public final int platformHori = -20; //horizontal position of platform
    public final int whiteCupHhori = -330; //horizontal position of white cup
    public final int blackCupHori = -600; //horizontal position of black cup
    public final int platformVert = 200; //height of ball platform
    public final int moveHeightVert = 110; //height of arm needed to clear the platform and rotate freely

    /**
     * Constructs ports, Arbitrator, behaviors, sensors and movement classes.
     */
    public Ball()
    {
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
        white = new MoveWhite(horizontal, vertical, claw, colour, pressure);
        black = new MoveBlack(horizontal, vertical, claw, colour, pressure);
        home = new MoveHome(pressure, horizontal, claw, vertical);
        wait = new Wait();

        // Behavior array
        bArray = new Behavior[]{wait, home, white, black};

        //TODO should the Arbitrator be in its own class (nested class?),
        // with sensors and motors (or Behavior[]?) as construction parameters?

        // Arbitrator
        arb = new Arbitrator(bArray);
        //TODO should Arbitrator and Calibration be implemented
        // as static nested classes in ArmMain?

    }

    public void start() {

        //menu(); //starts a menu only used for debugging.
        calibrate(); // uncomment to run calibration at start
        arb.go(); //uncomment to start arbitrator
        System.exit(0);
        //TODO fuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuck
    }//start

    /**
     * moves all motors to their intended zeroing position
     */
    public void calibrate()
    {
        claw.findZero();
        while(colour.getDistance() < 0.7){
            vertical.moveArm(-300);
        }
        vertical.stop();
        vertical.resetTacho();
        home.action();
    }


}
