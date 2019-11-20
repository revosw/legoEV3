import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Class to control vertical movement of arm.
 */
public class Vertical {


    private RegulatedMotor vertical;

    /**
     * Horizontal class constructor.
     * @param arm the motor that raises and lowers the arm
     */
    public Vertical(EV3LargeRegulatedMotor arm) {
        vertical = arm;
        vertical.setSpeed(CalibrationValues.MOVEMENT_SPEED.getValue());
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
