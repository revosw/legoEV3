import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Class to control horizontal arm movement, i.e. rotation.
 */
public class Horizontal {

    private RegulatedMotor horizontal;

    public Horizontal() {
        horizontal = new EV3LargeRegulatedMotor(MotorPort.C);
    }

    //TODO find acceleration values?

    /**
     * Rotates motor to the specified angle value, limit < 0 rotates to left.
     * Angle limits have a ratio of aprox. 3:1 to actual arm movement.
     * For example, a 90 degree turn to the left would be rotate(-280)
     * rotate(0) returns the arm to its initial starting point,
     * unless motor tachometer has been reset
     *
     * @param limit the angle position to rotate to
     */
    public void rotateTo(int limit) {
        horizontal.rotateTo(limit);
    }

    public void absoluteRotation(int degrees)
    {
        horizontal.rotate(degrees, true);
    }
    public void haltHorizontal()
    {
        horizontal.stop();
    }
    public void rotateHome()
    {
        horizontal.rotateTo(0, true);
    }
    public void resetTacho()
    {
        horizontal.resetTachoCount();
    }


}
