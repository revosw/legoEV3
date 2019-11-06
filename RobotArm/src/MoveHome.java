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

    @Override
    public void action()
    {
        supressed = false;

        //moves back to known 0 position until supressed
        // or interrupted by touch button

        while (!supressed && (touch.getTouch() == 0)) {
             horizontal.rotateHome();
        } //while


        if( touch.getTouch() == 1) {
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
