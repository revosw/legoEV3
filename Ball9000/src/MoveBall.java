import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when a black ball is detected.
 */


public class MoveBall implements Behavior {

    private boolean suppressed = false;
    private Horizontal horizontal;
    private Vertical vertical;
    private Claw claw;
    private SenseColour colour;
    private Pressure pressure;

    /**
     * Constructor for the MoveBlack behavior, which should request control when a black ball is present.
     * @param hori the horizontal motor
     * @param vertical
     * @param claw
     * @param col the color sensors
     * @param pres the pressure sensor
     */
    //TODO add constructor with motors and sensors as parameters
    public MoveBall(Horizontal hori, Vertical vertical, Claw claw, SenseColour col, Pressure pres)
    {
        this.horizontal = hori;
        this.vertical = vertical;
        this.claw = claw;
        this.colour = col;
        this.pressure = pres;
    }


    /**
     * Requests control if the ball sensor reports a black ball.
     * @return true if ball is black && pressure sensor is pressed, else false.
     */
    @Override
    public boolean takeControl()
    {
        return ( colour.getColor() == 6 || colour.getColor() == 7 ) && (pressure.isPressed());
        //TODO add method to take control when SenseColor returns a black reading.
    }

    /**
     * Sets the MoveBlack behavior's suppressed field to true,
     * which interrupts MoveBlack action.
     */
    @Override
    public void suppress()
    {
        suppressed = true;
    }

    @Override
    public void action()
    {

        suppressed = false;
        horizontal.rotateTo(-20); // arm is centered to 0 at a position slightly to the right of the ball tray
        claw.openClaw();
        vertical.changeElevation(CalibrationValues.PLATFORM_VERT.getValue()); //moves arm down to ball height
        claw.closeClaw();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int ballColour = colour.getColor();
        vertical.changeElevation(CalibrationValues.MOVE_HEIGHT_VERT.getValue());
        //TODO see if refactoring white and black behavior to single class breaks anything.
        // Thought is that they're basically the exact same, but with different values for where the cup is.
        // by refactoring, and using a second color check in the action to determine what color the ball is
        // we might be able to eliminate the misreading of white balls as black when they're placed on the
        // platform while the robot is in Wait behavior.
        if(ballColour == 7) {
            horizontal.rotateTo(CalibrationValues.BLACK_CUP_HORIZONTAL.getValue());
        }
        else if(ballColour == 6){
            horizontal.rotateTo(CalibrationValues.WHITE_CUP_HORIZONTAL.getValue());
        }
        vertical.changeElevation(CalibrationValues.CUP_VERT.getValue()); //lowers arm into cup
        claw.openClaw(); //drops ball
        suppressed = true;
        /*TODO:
        1. lower arm
        2. grab ball
        3. raise arm
        4. rotate to white cup
            (4.b lower arm?)
        5. drop ball
        6. finish
        */

    }
}
