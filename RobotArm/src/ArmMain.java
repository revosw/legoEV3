import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

import java.awt.*;

public class ArmMain {

    public static void main(String[] args)
    {
        // Behaviors
        Behavior sense = new SenseColour();
        Behavior white = new MoveWhite();
        Behavior black = new MoveBlack();
        Behavior back = new MoveBack();

        // Behavior array
        Behavior [] bArray = {white, black, back};

        // Arbitrator
        Arbitrator arb = new Arbitrator(bArray);
        arb.go();

    }

}
