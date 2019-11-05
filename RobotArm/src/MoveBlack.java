import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when a black ball is detected.
 */


public class MoveBlack implements Behavior {
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
