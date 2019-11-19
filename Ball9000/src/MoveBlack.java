import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when a black ball is detected.
 */


public class MoveBlack implements Behavior {

    private boolean suppressed = false;
    private Horizontal horizontal;
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
    public MoveBlack(Horizontal hori, Vertical vertical, Claw claw, SenseColour col, Pressure pres)
    {
        this.horizontal = hori;
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
        return (colour.getColor() == 7) && (pressure.isPressed());
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
        horizontal.rotateTo(-560);
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
