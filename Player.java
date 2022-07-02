import java.util.Random;

public class Player extends GamePanel{

    int playerX;
    int playerY;
    int playerHP;
    int playerAD;
    int playerGold;
    private Random random = new Random();


    Player(){
        playerX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE);
        playerY = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE);
        playerHP = 5;
        playerAD = 3;
        playerGold = 0;
    }

    public int getPlayerX(){
        return this.playerX;
    }

    public int getPlayerY(){
        return this.playerY;
    }

    public int getPlayerGold(){
        return this.playerGold;
    }

    public int getPlayerAD(){
        return this.playerAD;
    }

    public void setPlayerX(int x){
        this.playerX = x;
    }

    public void setPlayerY(int y){
        this.playerY = y;
    }

    public void addGold(){
        this.playerGold++;
    }

}
