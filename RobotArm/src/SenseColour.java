import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

/**
 * Class to implement color/light sensors.
 */


public class SenseColour
{
    private EV3ColorSensor colorSensor; // sensor for detecting ball color
    private EV3ColorSensor distanceSensor; // sensor for measuring arm height

    private SampleProvider distanceProvider;
    private float[] distanceSample;

    public SenseColour(Port colorPort, Port distancePort)
    {
        // You don't need a sample provider to get a color, you can fetch directly with getColorID()
        this.colorSensor = new EV3ColorSensor(colorPort);
        // Need a sample provider and sample container to read from getRedMode()
        this.distanceSensor = new EV3ColorSensor(distancePort);

        distanceProvider = distanceSensor.getRedMode();
        distanceSample = new float[distanceProvider.sampleSize()];
    }

    /**
     * Get the sensor value for arm height
     * @return sensor value for arm height
     */
    public float getDistance()
    {
        distanceProvider.fetchSample(distanceSample, 0);
        return distanceSample[0];
    }

    /**
     * Get the color ID of the ball color that is detected. We only care about black
     * and white, but this is easily extended.
     * @return int ColorID
     */
    public int getColor()
    {
        return colorSensor.getColorID();
    }

}
