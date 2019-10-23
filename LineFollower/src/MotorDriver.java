import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
/**
 * Controls robot driving, stopping and turning.
 * @author Olav Valle
 * @version 20191023
 */

public class MotorDriver
{

    //declare motors
    private Brick brick;
    private RegulatedMotor right;
    private RegulatedMotor left;
    private RegulatedMotor[] motors;

    /**
     * constructor for MotorDriver class.
     */
    public MotorDriver()
    {

        brick = BrickFinder.getDefault();

        left = new EV3LargeRegulatedMotor(MotorPort.D);
        right = new EV3LargeRegulatedMotor(MotorPort.A);

        motors = new RegulatedMotor[]{left};
        right.synchronizeWith(motors);
    }

    /**
     * Drives robot forward.
     */
    public void forward()
    {
        right.setSpeed(450);
        left.setSpeed(450);

        right.startSynchronization(); // starts the synchronization of right and left motors.
        right.forward();
        left.forward();
        right.endSynchronization();
    }

    /**
     * Stops both motors.
     */
    public void stop()
    {
        right.startSynchronization();
        left.stop();
        right.stop();
        right.endSynchronization();
    }

    /**
     * Drives backwards a short distance.
     */
    public void backwards(){
        right.setSpeed(450);
        left.setSpeed(450);
        right.startSynchronization();
        // starts the synchronization of right and left motors.

        right.rotate(-180);
        left.rotate(-180);
        right.waitComplete();
        left.waitComplete();

        right.endSynchronization();
        //ends synch between motors.
    }

    /**
     * Turns left motor 180 deg forward and left motor 180 deg backwards, turning robot 90 degrees to the left.
     */
    public void turnLeft()
    {
        right.setSpeed(450);
        left.setSpeed(450);
        right.startSynchronization();
        // starts the synchronization of right and left motors.

        right.rotate(190);
        left.rotate(-190);

        right.waitComplete();
        left.waitComplete();

        right.endSynchronization(); //ends synch between motors.
    }

    public void forwardWhile()
    {
        right.setSpeed(450);
        left.setSpeed(450);

        //Q: does synch start/end have to be inside or outside a while/if loop?

        int i = 0; //while conditional

        right.startSynchronization();

        while(i <= 12)
        {
            right.rotate(90);
            left.rotate(90);
            i++;
        }

        right.endSynchronization();

    }
    /**
     * Drives vehicle forward for 10 seconds.
     * @throws Exception
     */
    public void forwardTenSec() throws Exception {

        right.setSpeed(450);
        left.setSpeed(450);

        right.startSynchronization(); // starts the synchronization of right and left motors.
        right.forward();
        left.forward();
        right.endSynchronization(); //ends synch between motors.
        // Required to make commands between start and end execute.
        Thread.sleep(10000); // runs previous command for 10 seconds
        //Stop commands not required, ends when thread wakes.
    }
}
