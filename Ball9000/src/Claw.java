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
     * @param medium the medium motor that controls the claw
     */
    public Claw(EV3MediumRegulatedMotor medium) {
        claw = medium;
        claw.setSpeed(CalibrationValues.MOVEMENT_SPEED.getValue());


    }

    public boolean findZero() {
        claw.rotate(180); // closes claw completely
        claw.resetTachoCount(); //resets tacho counter to 0 while claw is closed
        return (claw.getTachoCount() == 0);
    }

    public void stallAndReset()
    {
        claw.setStallThreshold(3, 5); //sets low stall tolerance, to prevent overtightening of claw.
        while (!claw.isStalled()) {
            claw.rotate(180, true);
            System.out.println("claw is not stalled, rotating");
        }
        if(claw.isStalled()){
            claw.resetTachoCount();
            System.out.println("claw is stalled, resetting tacho");
            System.out.println("Claw tacho is: " + claw.getTachoCount());
        }
        claw.setStallThreshold(50, 1000);//sets stall tolerance back to default
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
        //TODO remake this with a claw.forward and while not stalled
        claw.setStallThreshold(3, 10); //sets low stall tolerance, to prevent overtightening of claw.
        while(!claw.isStalled() && claw.getTachoCount() < 0){
            claw.rotateTo(0);
        }
        claw.setStallThreshold(50, 1000); //sets stall tolerance back to default
        System.out.println("Claw closed.  Thacho is: " + claw.getTachoCount());
        //TODO find tacho count when stalled because of ball? THIS!!
    }

}
