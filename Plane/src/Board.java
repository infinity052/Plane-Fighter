import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Board extends JPanel implements GameConstants{
	Player player;
	Timer timer;
    Enemy[][] enemies = new Enemy[10][3];
	
	Board(){
	
		setBackground(Color.black);
		
		double x=G_WIDTH; 
		for(int i=0;i<10;i++) {
			double y=40;	
			for(int j=0;j<3;j++) {
				
				
				
			Enemy enemy = new Enemy((int)x,(int)y);
			enemies[i][j]=enemy;
			 y+=150+(Math.random()*100);
			
		}
			x+=350+(Math.random()*100);
	   
		}
		player=new Player();
		setFocusable(true);
		
		registerEvents();
		
		
		
		
		gameLoop();
		
		
		

	}
	
	void drawBullets(Graphics g) {
		for(Bullet bullet : player.getBullets()) {
			bullet.move();
			bullet.draw(g);
			
		}
	}
	
	private Boolean isCollided() {
		 
		int width=Math.max(player.getWidth(),enemies[0][0].getWidth());
		int height=Math.max(player.getHeight(), enemies[0][0].getHeight());
		int xDistance[][]=new int[10][3];
		int yDistance[][]=new int[10][3];
		for(int i=0;i<10;i++) {
			for(int j=0;j<3;j++) {
				xDistance[i][j]=Math.abs(player.getX()-enemies[i][j].getX());
				yDistance[i][j]=Math.abs(player.getY()-enemies[i][j].getY());
			}
			
		}
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<3;j++) {
				
//		System.out.println(xDistance[i][j] +" of "+i+" "+j+" "+yDistance[i][j]);
		if(xDistance[i][j]<=width-30 && yDistance[i][j]<=height-20)
			return true; 
			}
			}
		return false;
		
	}
	
	private void collisionMessage(Graphics g) {
		
		    g.setColor(Color.white);
		    g.setFont(new Font("Arial",Font.BOLD,49));
			g.drawString("Game Over!!", G_WIDTH/2-190, G_HEIGHT/2-60);
			timer.stop();
		
		
	}
	
	private void collisionCheck(Graphics g) {
		if(isCollided()==true) {
			
			collisionMessage(g);
		}
	}
	
	private void registerEvents() {
		addKeyListener(new KeyAdapter(){
		@Override
		public	
		void keyPressed(KeyEvent e) {


			if(e.getKeyCode()==KeyEvent.VK_UP) {
				
				player.setSpeed(-20);
				player.move();
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				player.setSpeed(20);
				player.move();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				player.fire();
			}
		}
		});
	}
	
	

	void gameLoop() {
	   timer=new Timer(35,(e)->{
		  for(int i=0;i<10;i++) {
				for(int j=0;j<3;j++) {
					requestFocus(true);
				enemies[i][j].move();
			}}
		  
//		  player.fall();
		  checkIfEnemyInside();
			repaint();
		});
	  timer.start();
	}
	
	private void drawEnemies(Graphics g) {
		
		for(int i=0;i<10;i++) 
		{
			for(int j=0;j<3;j++) {
			enemies[i][j].draw(g);
			
		}
		
		}
		
   
    		
    }
	
	private boolean checkIfEnemyDead(Enemy enemy) {
		int xDistance;
		int yDistance;
		
		
		
		for(Bullet bullet : player.getBullets()) {
			xDistance=Math.abs(bullet.getX()-enemy.getX());
		    yDistance=Math.abs(bullet.getY()-enemy.getY());
			if(xDistance<=enemy.getWidth()-30 && yDistance<=enemy.getHeight()-20)	
			{   bullet.setX(1300);
			    bullet.setY(0);
			    bullet.setSpeed(0);
				return true;
			}		}
		return false;
	}
	
	
	
	private void checkIfEnemyInside() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<3;j++) {
				if(enemies[i][j].getX()<=G_WIDTH) {
					if(checkIfEnemyDead(enemies[i][j])) {
					enemies[i][j].setX(1400);
					enemies[i][j].setY(0);
					enemies[i][j].setSpeed(0);
					
					
					
				}
			}
		}
		}
	}
		
	
	
private boolean winCheck() {
	for(int i=0;i<3;i++)
	 {if(enemies[9][i].getX()<=0)
		 return true;
	 }
	return false;
	
}

private void win(Graphics g) {
	if(winCheck()==true) {
		 g.setColor(Color.YELLOW);
		    g.setFont(new Font("Arial",Font.BOLD,60));
			g.drawString("YOU WON", (G_WIDTH/2)-180, (G_HEIGHT/2)-60);
			timer.stop();
	}
}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);
		player.draw(g);
		drawEnemies(g);
		drawBullets(g);
		collisionCheck(g);
		win(g);
		
		
	}
	
	private void drawBackground(Graphics g) {
		g.drawImage(ImageLoader.loadImage("bg.jpg"), 0, 0, G_WIDTH, G_HEIGHT, null);
	}
}
