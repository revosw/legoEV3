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

        //TODO change this to a lejos.utility.TextMenu
        // implementing choices "Start Arbitrator", "Calibrate", and "Manual Control"
        boolean cont = true;
        while (cont) {
            switch (Button.waitForAnyPress()) {
                case (Button.ID_ENTER):
                    //arb.go();
                    claw.findZero();
                    while(colour.getDistance() < 0.7){
                        vertical.moveArm(-300);
                    }
                    vertical.stop();
                    vertical.resetTacho();
                    home.action();
                    break;
                case (Button.ID_ESCAPE):
                    arb.stop();
                    cont = false;
                    break;
                case (Button.ID_LEFT):
                    white.action();
                    break;
                case (Button.ID_DOWN):
                    armTest();
                    break;
                case (Button.ID_UP):
                    home.action();
                    break;
                case (Button.ID_RIGHT):
                    clawTest();
                    break;
            }//switch
        }//while
        //TODO add app shutdown with ESCAPE button
        // while(Button.ESCAPE.isUp())
        // Exits program when ESCAPE is pressed
        System.exit(0);

        //TODO add lejos.hardware.Button functions for testing of behaviors separately
        // i.e. up for Home, left for 90 deg, down for 180 deg, right for calibrate.
        // center button to activate/deactivate arbitrator?

        //TODO start with arbitrator off, and activate manually?

        //TODO possible to call a behavior directly with Behavior.action?


    }//start



    private void armTest() {
        boolean armTest = true;
        while (armTest) {
            switch(Button.waitForAnyPress()) {
                case (Button.ID_DOWN):
                    vertical.changeElevation(0);
                    break;
                case (Button.ID_UP):
                    vertical.changeElevation(220);
                    break;
                case (Button.ID_ENTER):
                    while(colour.getDistance() < 0.7){
                        vertical.moveArm(-300);
                    }
                    vertical.stop();
                    vertical.resetTacho();
                    break;
                case (Button.ID_RIGHT):
                    vertical.changeElevation(110);
                    break;
                case (Button.ID_ESCAPE):
                    armTest = false;
                    break;
            }
        }
    }

    private void clawTest() {
        boolean clawTest = true;
        while(clawTest) {
            switch (Button.waitForAnyPress()) {
                case (Button.ID_UP):
                    claw.closeClaw();
                    break;
                case (Button.ID_DOWN):
                    claw.openClaw();
                    break;
                case (Button.ID_ESCAPE):
                    clawTest = false;
                    break;
                case (Button.ID_ENTER):
                    claw.findZero();
                    break;
            }
        }
    }
}

