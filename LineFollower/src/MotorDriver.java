import com.sun.org.apache.regexp.internal.RE;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
/**
 * Main class of app to make robot drive forward.
 */

public class MotorDriver
{

    //declare motors
    private Brick brick;
    private RegulatedMotor right;
    private RegulatedMotor left;
    private RegulatedMotor[] motors;

    public MotorDriver()
    {

        brick = BrickFinder.getDefault();

        left = new EV3LargeRegulatedMotor(MotorPort.D);
        right = new EV3LargeRegulatedMotor(MotorPort.A);

        motors = new RegulatedMotor[]{left};
        right.synchronizeWith(motors);
    }

    public void forward()
    {
        right.setSpeed(450);
        left.setSpeed(450);

        right.startSynchronization(); // starts the synchronization of right and left motors.
        right.forward();
        left.forward();
        right.endSynchronization();
    }

    public void stop()
    {
        right.startSynchronization();
        left.stop();
        right.stop();
        right.endSynchronization();
    }
    public void backwards() throws InterruptedException {
        right.setSpeed(450);
        left.setSpeed(450);
        right.startSynchronization(); // starts the synchronization of right and left motors.
        right.backward();
        left.backward();
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
