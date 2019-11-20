import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when a black ball is detected.
 */


public class MoveBall implements Behavior {

    private boolean suppressed = false;
    private Horizontal horizontal;
    private Vertical vertical;
    private Claw claw;
    private SenseColour colour;
    private Pressure pressure;

    /**
     * Constructor for the MoveBlack behavior, which should request control when a black ball is present.
     * @param hori the horizontal motor
     * @param vertical
     * @param claw
     * @param col the color sensors
     * @param pres the pressure sensor
     */
    //TODO add constructor with motors and sensors as parameters
    public MoveBall(Horizontal hori, Vertical vertical, Claw claw, SenseColour col, Pressure pres)
    {
        this.horizontal = hori;
        this.vertical = vertical;
        this.claw = claw;
        this.colour = col;
        this.pressure = pres;
    }


    /**
     * Requests control if the ball sensor reports a black ball.
     * @return true if ball is black && pressure sensor is pressed, else false.
     */
    @Override
    public boolean takeControl()
    {
        return ( colour.getColor() == 6 || colour.getColor() == 7 ) && (pressure.isPressed());
        //TODO add method to take control when SenseColor returns a black reading.
    }

    /**
     * Sets the MoveBlack behavior's suppressed field to true,
     * which interrupts MoveBlack action.
     */
    @Override
    public void suppress()
    {
        suppressed = true;
    }

    @Override
    public void action()
    {

        suppressed = false;

        while(!suppressed) {
            horizontal.rotateTo(-20); // arm is centered to 0 at a position slightly to the right of the ball tray

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int ballColour = colour.getColor();
            if (ballColour != 7 && ballColour != 6) {
                suppressed = true;
                continue;
            }

            vertical.changeElevation(CalibrationValues.PLATFORM_VERT.getValue()); //moves arm down to ball height
            claw.closeClaw(); //claw starts open, this closes it to grab ball
            vertical.changeElevation(CalibrationValues.MOVE_HEIGHT_VERT.getValue());

            if (ballColour == 7) {
                horizontal.rotateTo(CalibrationValues.BLACK_CUP_HORIZONTAL.getValue());
            } else if (ballColour == 6) {
                horizontal.rotateTo(CalibrationValues.WHITE_CUP_HORIZONTAL.getValue());
            }

            vertical.changeElevation(CalibrationValues.CUP_VERT.getValue()); //lowers arm into cup
            claw.openClaw(); //drops ball
            suppressed = true;
        }
        /*TODO:
        1. lower arm
        2. grab ball
        3. raise arm
        4. rotate to white cup
            (4.b lower arm?)
        5. drop ball
        6. finish
        */

    }
}
