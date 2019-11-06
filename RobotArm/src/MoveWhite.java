import lejos.robotics.subsumption.Behavior;
import org.graalvm.compiler.lir.sparc.SPARCMove;

/**
 * The Behavior that should become active when a white ball is detected.
 */


public class MoveWhite implements Behavior {

    private boolean supressed = false;


    //TODO add constructor with motors and sensors as parameters
    public MoveWhite()
    {

    }

    @Override
    public boolean takeControl()
    {
        return false;
        //TODO add method to take control when SenseColor returns a white reading
        //TODO and so that button is NOT pushed
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
