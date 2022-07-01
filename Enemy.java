import java.util.Random;
import java.util.ArrayList;

public class Enemy extends GamePanel{
    int enemyHP;
    int enemyGold;
    int enemyX;
    int enemyY;
    private Random random;
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    Enemy(){
        enemyX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        enemyY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        enemyHP = random.nextInt(0,9);
    }

    public void newEnemy(){
        enemies.add(new Enemy());
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
