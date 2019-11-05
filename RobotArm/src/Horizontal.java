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
    private Brick brick = BrickFinder.getDefault();
    private RegulatedMotor rotation = new EV3LargeRegulatedMotor(MotorPort.A);
    private Port s1 = brick.getPort("S1");
    private EV3TouchSensor touch = new EV3TouchSensor(s1);

}
