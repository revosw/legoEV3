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

        boolean run = true;
        boolean direction = true; // true is forward, false is reverse

        while(run) {
            lcd.drawString(sensor.getSampleString(), 0, 1);

            if(direction) {
                driver.forward();
                direction = !sensor.blackDetectedFront(); // sensor returns true on black
                //switch direction when black is detected
            }
            if(!direction){
                driver.stop();
                run = false;
            }
/*            if(!direction){
                driver.backwards();
                Thread.sleep(1000);
                direction = sensor.blackDetectedBack(); // sensor returns true on black
            }*/
        }


        /*
        while(true)
        {
            lcd.drawString(sensor.getSampleString(), 0, 1);
        }*/

    }
}
