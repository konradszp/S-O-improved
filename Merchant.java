import java.util.Random;


public class Merchant extends GamePanel{
    int merchantX;
    int merchantY;
    int merchantGold;
    private Random random;

    Merchant(){
        merchantX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		merchantY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        merchantGold = 5;
    }

    public int getMerchantX(){
        return this.merchantX;
    }

    public int getMerchantY(){
        return this.merchantY;
    }
}
