import lejos.robotics.subsumption.Behavior;

/**
 * The Behavior that should become active when a white ball is detected.
 */


public class MoveWhite implements Behavior {

    private boolean suppressed = false;
    private Horizontal horizontal;
    private Claw claw;
    private SenseColour colour;
    private Pressure pressure;
    private Vertical vertical;

    //TODO add constructor with motors and sensors as parameters
    /**
     * Constructor for the MoveWhite behavior, which should request control when a white ball is present.
     * @param hori the horizontal motor
     * @param claw
     * @param col the color sensors
     * @param pres the pressure sensor
     */
    public MoveWhite(Horizontal hori, Vertical vert, Claw claw, SenseColour col, Pressure pres)
    {

        this.horizontal = hori;
        this.vertical = vert;
        this.claw = claw;
        this.colour = col;
        this.pressure = pres;
    }

    /**
     * Requests control if the ball sensor reports a white ball.
     * @return true if ball is white.
     */
    @Override
    public boolean takeControl()
    {
        return (colour.getColor() == 6) && (pressure.isPressed());
        //TODO add method to take control when SenseColor returns a white reading
        //TODO and so that button is NOT pushed
    }


    /**
     * Sets the MoveWhite behavior's suppressed field to true,
     * which interrupts MoveWhite action.
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

        horizontal.rotateTo(-20);
        claw.openClaw();
        vertical.changeElevation(220);
        claw.closeClaw();
        vertical.changeElevation(110);
        horizontal.rotateTo(-280);
        claw.openClaw();

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
