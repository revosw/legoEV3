import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Class to control vertical movement of arm.
 */
public class Vertical {
    //TODO add constructor with motors as parameters

    private RegulatedMotor vertical;

    /**
     * Horizontal class constructor.
     */
    public Vertical() {
        vertical = new EV3MediumRegulatedMotor(MotorPort.A);
    }

    //TODO find acceleration values?

    public void changeElevation(int elevation)
    {
        vertical.rotateTo(elevation);
    }

    public void moveArm(int distance)
    {
        vertical.rotate(distance, true);
    }

    public void resetTacho() {
        vertical.resetTachoCount();
    }

    public void stop() {
        vertical.stop();
    }
}
