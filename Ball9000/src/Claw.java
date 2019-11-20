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
        claw.setSpeed(CalibrationValues.MOVEMENT_SPEED.getValue());


    }

    public boolean findZero() {
        claw.rotate(180); // closes claw completely
        claw.resetTachoCount(); //resets tacho counter to 0 while claw is closed
        return (claw.getTachoCount() == 0);
    }

    public void stallAndReset()
    {
        claw.setStallThreshold(3, 1);
        while (!claw.isStalled()) {
            claw.rotate(180, true);
            System.out.println("claw is not stalled, rotating");
        }
        if(claw.isStalled()){
            claw.resetTachoCount();
            System.out.println("claw is stalled, resetting tacho");
            System.out.println("Claw tacho is: " + claw.getTachoCount());
        }
        claw.setStallThreshold(50, 1000);
    }


    public void openClaw() {
        claw.rotateTo(-75);
        System.out.println("Claw open. Thacho is: " + claw.getTachoCount());
        // -75 degrees is approx. to open from completely closed
    }
    public void closeClaw()
    {
        // Cannot use isStalled, because motor does not actually stall when claw is
        // closed, the motor just keeps spinning and grinding gears

        claw.rotateTo(0);
        System.out.println("Claw closed.  Thacho is: " + claw.getTachoCount());
        //TODO find tacho count when stalled because of ball?

    }

}
