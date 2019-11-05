import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when the arm has to return to home position.
 */


public class MoveHome implements Behavior {
    @Override
    public boolean takeControl() {
        return false;
    }

    @Override
    public void action() {

    }

    @Override
    public void suppress() {

    }
}
