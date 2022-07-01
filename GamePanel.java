import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1300;
	static final int SCREEN_HEIGHT = 750;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    private Random random;

    Player p1;
    Merchant m1;
    char direction = 0;

    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    boolean fightState = true;
    String s;
    boolean chance;


    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
    }

    public void startGame(){
        p1 = new Player();
        m1 = new Merchant();
        newEnemies();

    }

    public void newEnemies(){
        for(int x=0;x<5;x++){
            enemies.add(new Enemy());
        }
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

    public void draw(Graphics g) {
	
		if(fightState==true&&enemies.size()>0){
			for(int x = 0;x<enemies.size();x++){

				g.setColor(Color.red);
				g.fillOval((enemies.get(x)).getEnemyX(), (enemies.get(x)).getEnemyY(), UNIT_SIZE, UNIT_SIZE);

				s = String.valueOf((enemies.get(x).getEnemyHP()));

				g.setColor(Color.pink);
				g.setFont( new Font("Ink Free",Font.BOLD, UNIT_SIZE));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString(s, ((enemies.get(x)).getEnemyX() - metrics.stringWidth(s))+UNIT_SIZE-9, (enemies.get(x)).getEnemyY()+UNIT_SIZE-6);
			}
			//g.fillOval(enemyX, enemyY, UNIT_SIZE, UNIT_SIZE);

			g.setColor(Color.green);
			g.fillRect(p1.getPlayerX(), p1.getPlayerY(), UNIT_SIZE, UNIT_SIZE);

			g.setColor(Color.YELLOW);
			g.fillOval(m1.getMerchantX(), m1.getMerchantY(), UNIT_SIZE, UNIT_SIZE);

			

			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics2 = getFontMetrics(g.getFont());
			g.drawString("Gold: "+p1.getPlayerGold()+ " AD: "+p1.getPlayerAD(), (SCREEN_WIDTH - metrics2.stringWidth("Gold: "+p1.getPlayerGold()+ " AD: "+p1.getPlayerAD()))/2, g.getFont().getSize());

		
		}
		else if(fightState==true&&enemies.size()==0){
			gameWon(g);
		}
		else{
			gameOver(g);
		}
	}

    public void checkCollisions() {
        //check if character touches the enemy
        for(int i=0;i<enemies.size();i++){
            if(p1.getPlayerX()==(enemies.get(i)).getEnemyX() && p1.getPlayerY()==(enemies.get(i)).getEnemyY()){
                switch(direction){
                case 'U':
                    p1.setPlayerY(p1.getPlayerY()+UNIT_SIZE);
                    repaint();
                    break;
                case 'D':
                    p1.setPlayerY(p1.getPlayerY()-UNIT_SIZE);
                    repaint();
                    break;
                case 'L':
                    p1.setPlayerX(p1.getPlayerX()+UNIT_SIZE);
                    repaint();
                    break;
                case 'R':
                    p1.setPlayerX(p1.getPlayerX()-UNIT_SIZE);
                    repaint();
                    break;
                }
            }
        
        }

        if(p1.getPlayerX()==m1.getMerchantX() && p1.getPlayerY()==m1.getMerchantY()){
            switch(direction){
            case 'U':
                p1.setPlayerY(p1.getPlayerY()+UNIT_SIZE);
                repaint();
                break;
            case 'D':
                p1.setPlayerY(p1.getPlayerY()-UNIT_SIZE);
                repaint();
                break;
            case 'L':
                p1.setPlayerX(p1.getPlayerX()+UNIT_SIZE);
                repaint();
                break;
            case 'R':
                p1.setPlayerX(p1.getPlayerX()-UNIT_SIZE);
                repaint();
                break;
            }
        }
    }

    public void deleteEnemy(int pos){
        enemies.remove(pos);
        repaint();
    }

    public void attack(){
        for(int i=0;i<enemies.size();i++){
            if(((p1.getPlayerX() == enemies.get(i).getEnemyX() + UNIT_SIZE || p1.getPlayerX() == enemies.get(i).getEnemyX()  - UNIT_SIZE)&&(p1.getPlayerY() == enemies.get(i).getEnemyY() ))||((p1.getPlayerY() == enemies.get(i).getEnemyY()  - UNIT_SIZE || p1.getPlayerY() == enemies.get(i).getEnemyY() +UNIT_SIZE)&&(p1.getPlayerX() == enemies.get(i).getEnemyX()))){
                if(p1.getPlayerAD()>=enemies.get(i).getEnemyHP()*2){
                    p1.addGold();
                    deleteEnemy(i);
                }
                else if(p1.getPlayerAD()>=enemies.get(i).getEnemyHP()){
                    if(chance = new Random().nextInt(4)!=3){
                        p1.addGold();
                        deleteEnemy(i);
                    }
                    else{
                        fightState = false;
                    }
                }
                else{
                    if(p1.getPlayerAD()<enemies.get(i).getEnemyHP()){
                        if(chance = new Random().nextInt(4)<=1){
                            p1.addGold();
                            deleteEnemy(i);
                        }

                        
                        else{
                            fightState=false;
                        }
                    }
                }
            }
        }
    }


    public void gameWon(Graphics g){

        //Game Won text
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("You won! ", (SCREEN_WIDTH - metrics2.stringWidth("You won! "))/2, SCREEN_HEIGHT/2);
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+p1.getPlayerAD(), (SCREEN_WIDTH - metrics1.stringWidth("Score: "+p1.getPlayerAD()))/2, g.getFont().getSize());
        //Game Over text
        if(enemies.size()>0){
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
        }
        }

        @Override
	public void actionPerformed(ActionEvent e) {
		checkCollisions();
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				direction = 'L';
				if(p1.getPlayerX() > 0){
					p1.setPlayerX(p1.getPlayerX()-UNIT_SIZE);
					checkCollisions();
					repaint();
				}
				break;
                case KeyEvent.VK_RIGHT:
				direction = 'R';
				if(p1.getPlayerX()<SCREEN_WIDTH-UNIT_SIZE){
					p1.setPlayerX(p1.getPlayerX()+UNIT_SIZE);
					checkCollisions();
					repaint();
				}
				break;
			case KeyEvent.VK_UP:
				direction = 'U';
				if(p1.getPlayerY()>0){
					p1.setPlayerY(p1.getPlayerY()-UNIT_SIZE);
					checkCollisions();
					repaint();
				}
				break;
			case KeyEvent.VK_DOWN:
				direction = 'D';
				if(p1.getPlayerY()<SCREEN_HEIGHT-UNIT_SIZE){
					p1.setPlayerY(p1.getPlayerY()+UNIT_SIZE);
					checkCollisions();
					repaint();
				}
				break;
			case KeyEvent.VK_SPACE:
				for(int i=0;i<enemies.size();i++){
				if(((p1.getPlayerX() == (enemies.get(i)).getEnemyX() + UNIT_SIZE || p1.getPlayerX() == (enemies.get(i)).getEnemyX() - UNIT_SIZE)&&(p1.getPlayerY() == (enemies.get(i)).getEnemyY())||((p1.getPlayerY() == (enemies.get(i)).getEnemyY() - UNIT_SIZE || p1.getPlayerY() == (enemies.get(i)).getEnemyY()+UNIT_SIZE)&&(p1.getPlayerX() == (enemies.get(i)).getEnemyX())))){
					attack();
					checkCollisions();
					repaint();
					}
				}
				if(((p1.getPlayerX() == m1.getMerchantX() + UNIT_SIZE || p1.getPlayerX() == m1.getMerchantX() - UNIT_SIZE)&&(p1.getPlayerY() == m1.getMerchantY()))||((p1.getPlayerY() == m1.getMerchantY() - UNIT_SIZE || p1.getPlayerY() == m1.getMerchantY()+UNIT_SIZE)&&(p1.getPlayerX() == m1.getMerchantX()))){
					new MerchantFrame();
				}
				break;
            }
			updateStats();
			repaint();

		}
		
	}
	}






}   
