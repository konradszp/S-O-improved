import java.util.Random;

public class Enemy extends GamePanel{
    int enemyHP;
    int enemyGold;
    int enemyX;
    int enemyY;
    private Random random;

    Enemy(){
        enemyX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        enemyY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        enemyHP = random.nextInt(0,9);
        enemyGold = random.nextInt(1,5);
    }

    public int getEnemyX(){
        return this.enemyX;
    }

    public int getEnemyY(){
        return this.enemyY;
    }

    public int getEnemyHP(){
        return this.enemyHP;
    }
}
