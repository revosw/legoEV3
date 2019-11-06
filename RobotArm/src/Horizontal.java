import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;

/**
 * Class to control horizontal arm movement, i.e. rotation.
 */
public class Horizontal {

    private RegulatedMotor horizontal;

    public Horizontal()
    {
        horizontal = new EV3LargeRegulatedMotor(MotorPort.C);
    }

    public void rotate(int limit)
    {
        horizontal.rotateTo(limit);
    }
}
