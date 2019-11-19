import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Class to control claw operations.
 */
public class Claw {

    private RegulatedMotor claw;
    //TODO add constructor with motors as parameters

    /**
     * Constructor for Claw class.
     */
    public Claw() {
        claw = new EV3MediumRegulatedMotor(MotorPort.B);
    }

    public boolean findZero() {
        claw.rotate(180); //
        claw.resetTachoCount();
        return (claw.getTachoCount() == 0);
    }


    public void openClaw() {
        claw.rotateTo(-75);
        // -75 degrees is approx. to open from completely closed
    }
    public void closeClaw()
    {
        // Cannot use isStalled, because motor does not actually stall when claw is
        // closed, the motor just ke
        // eps spinning and grinding gears

        claw.rotateTo(10);

        //TODO find tacho count when stalled because of ball?

    }

}
