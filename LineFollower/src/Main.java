import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;

public class Main {
    public static void main(String[] args) throws Exception {

        Brick brick = BrickFinder.getDefault();
        MotorDriver driver = new MotorDriver();
        LightSensor sensor = new LightSensor();

        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();

        lcd.drawString(sensor.getSampleString(), 0, 1);
        boolean run = true;
        boolean direction = true; // true is forward, false is reverse

        //TODO switch run and direction booleans in while and ifs
        while(direction) {

            //noinspection ConstantConditions
            if(run) {
                driver.forward();
                run = !sensor.blackDetectedFront(); // sensor returns true on black
                //switch direction when black is detected
            }

            if(!run){
                driver.stop();
                direction = false;
            }

        }
        while(!direction) {

            //noinspection ConstantConditions
            if(!run){
                driver.backwards();
                direction = sensor.blackDetectedBack(); // sensor returns true on black
            }

            //noinspection ConstantConditions
            if(run){
                driver.stop();
                run = true;
            }
        }

    }
}
