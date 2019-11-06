import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when the arm has to return to home position.
 */


public class MoveHome implements Behavior {

    private final Horizontal horizontal;
    private boolean supressed = false;
    private Pressure touch;

    public MoveHome(Pressure touch, Horizontal horizontal)
    {
        this.touch = touch;
        this.horizontal = horizontal;
    }

    /**
     * Requests control if touch button is not pressed.
     * @return true if it requests control, false if not
     */
    @Override
    public boolean takeControl()
    {
        return (touch.getTouch() == 0);
        //TODO add method so that behavior takes control when pressure sensor is NOT pushed
    }

    @Override
    public void suppress()
    {
        supressed = true;
    }

    /**+
     * Performs the MoveHome action,
     * which returns the arm to starting position
     * and resets rotation tachometer to zero.
     */
    @Override
    public void action()
    {
        supressed = false;

        //moves back to known 0 position until supressed
        // or interrupted by touch button

        //while touch is not pressed and supressed = true
        while (!supressed && (touch.getTouch() == 0)) {
             horizontal.rotateHome();
        } //while

        // if touch is pressed
        if( touch.getTouch() == 1 ) {
            //touch button has been pressed.
            //we are home, so we reset tacho to 0.
            horizontal.resetTacho();
            //calling resetTacho stops any motor movement
        } //if

        //TODO should this be two whiles, for !suppressed and touch.pressed separately?


        //TODO move arm back to home position, or until pressure sensor is pressed
        // implement gyro and tachometer sensors, as well as pressure, for higher accuracy.
        // possible to use pressure sensor as a way to calibrate gyro and tachometer?
    }//action
}
