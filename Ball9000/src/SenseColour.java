import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

/**
 * Class to implement color/light sensors.
 * @author Simen
 * @version 20191107
 */


public class SenseColour
{
    @SuppressWarnings("FieldCanBeLocal")
    private EV3ColorSensor ballSensor; // sensor for detecting ball presence and color
    private SampleProvider colorProvider;
    //sample array for ball color mode
    private float[] colorSample;

    //sample array for ball ambient mode
    private SampleProvider ambientProvider;
    private float[] ambientSample;

    @SuppressWarnings("FieldCanBeLocal")
    private EV3ColorSensor distanceSensor; // sensor for measuring arm height
    private SampleProvider distanceProvider;
    private float[] distanceSample;
    private float ambientValue;


    public SenseColour(Port ballPort, Port distancePort)
    {
        // You don't need a sample provider to get a color, you can fetch directly with getColorID()
        this.ballSensor = new EV3ColorSensor(ballPort);


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
        this.colorProvider = ballSensor.getColorIDMode();
        this.colorSample = new float[colorProvider.sampleSize()];
        colorProvider.fetchSample(colorSample, 0);

        return (int) colorSample[0];

    }

    /**
     * Get ambient light value, which tells if sensor is blocked by object or not
     * @return true if something has blocked the light sensor, and lowered the light value it reads`
     */
    public boolean getBallDetected()
    {
        this.ambientProvider = ballSensor.getAmbientMode(); //ambient mode detects ball if light is blocked
        this.ambientSample = new float[ambientProvider.sampleSize()];
        ambientProvider.fetchSample(ambientSample, 0);

        return (ambientSample[0] > ambientValue);
    }

    public void calibrateAmbient()
    {
        this.ambientProvider = ballSensor.getAmbientMode(); //ambient mode detects ball if light is blocked
        this.ambientSample = new float[ambientProvider.sampleSize()];
        ambientProvider.fetchSample(ambientSample, 0);
        this.ambientValue = ambientSample[0];

    }
}
