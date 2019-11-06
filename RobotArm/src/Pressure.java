import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

/**
 * The pressure sensor methods.
 */
public class Pressure
{
    private EV3TouchSensor touch;
    private SampleProvider touchProvider;
    private float[] touchSample;
    /**
     * Pressure button class constructor.
     * @param buttonPort the port the home button sensor is connected to
     */
    public Pressure(Port buttonPort)
    {
        this.touch = new EV3TouchSensor(buttonPort);
        touchProvider = touch.getTouchMode();
        touchSample = new float[touchProvider.sampleSize()];
    }

    /**
     * Returns the current status of the pressure button as an int.
     * @return 1 if button is pressed, 0 if not pressed.
     */
    public int getTouch()
    {
        touchProvider.fetchSample(touchSample, 0);
        return (int) touchSample[0];
        //TODO unsure if casting to (int) is safe.
        // getTouchMode should only return 1/0
    }
}
