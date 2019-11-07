import lejos.robotics.Color;
import lejos.robotics.subsumption.Behavior;

/**
 * The Behavior that should become active when a white ball is detected.
 */


public class MoveWhite implements Behavior {

    private boolean supressed = false;
    private Horizontal horizontal;
    private SenseColour colour;
    private Pressure pressure;

    //TODO add constructor with motors and sensors as parameters
    /**
     * Constructor for the MoveWhite behavior, which should request control when a white ball is present.
     * @param hori the horizontal motor
     * @param col the color sensors
     * @param pres the pressure sensor
     */
    public MoveWhite(Horizontal hori, SenseColour col, Pressure pres)
    {
        this.horizontal = hori;
        this.colour = col;
        this.pressure = pres;
    }

    /**
     * Requests control if the ball sensor reports a white ball.
     * @return true if ball is white.
     */
    @Override
    public boolean takeControl()
    {
        return (colour.getColor() == 6) && (pressure.isPressed());
        //TODO add method to take control when SenseColor returns a white reading
        //TODO and so that button is NOT pushed
    }


    /**
     * Sets the MoveWhite behavior's suppressed field to true,
     * which interrupts MoveWhite action.
     */
    @Override
    public void suppress()
    {
        supressed = true;
    }

    @Override
    public void action()
    {
        supressed = false;

        horizontal.rotateTo(-280);
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
