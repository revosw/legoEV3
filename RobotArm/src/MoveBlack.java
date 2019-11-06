import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when a black ball is detected.
 */


public class MoveBlack implements Behavior {

    private boolean supressed = false;

    //TODO add motors and sensors as parameters to constructor
    public MoveBlack()
    {

    }

    /**
     * Requests control if the ball sensor reports a white ball.
     * @return true if ball is black.
     */
    @Override
    public boolean takeControl()
    {
        return false;
        //TODO add method to take control when SenseColor returns a black reading.
    }

    /**
     * Sets the MoveBlack behavior's suppressed field to true,
     * which interrupts MoveBlack action.
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
