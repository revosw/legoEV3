import lejos.robotics.subsumption.Behavior;

/**
 * The default behavior of the RobotArm. Holds arm upright, while waiting for a new ball to be discovered.
 */

public class Wait implements Behavior {
    private boolean supressed = false;

    @Override
    public boolean takeControl()
    {
        return true;
        //always wants control, but has lowest priority
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
        //TODO: add method to keep robot arm raised and stationary


    }
}
