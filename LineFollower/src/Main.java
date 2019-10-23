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

        //TODO switch run and direction booleans in while and ifs?

        while(run) {

            while(direction) {
                driver.forward();
                direction = !sensor.blackDetectedFront(); // sensor returns true on black
                //switch direction when black is detected
            }
            driver.stop();
            while(!direction){
                driver.backwards();
                Thread.sleep(500);
                driver.stop();
                driver.turnLeft();
            }

            if(!direction){
                driver.stop();
                run = false;
            }

        }

        while(!run) {

            if(!direction){
                driver.backwards();
                run = sensor.blackDetectedBack(); // sensor returns true on black
            }

            if(direction){
                driver.stop();
                direction = true;
            }
        }

    }
}
