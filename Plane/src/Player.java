import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends Sprite implements GameConstants{
	ArrayList <Bullet>bullets = new ArrayList<>();
	
	
	
	
	public Player() {
		x=40;
		y=300;
		width=100;
		height=50;
		image=ImageLoader.loadImage("player.jpg");
		
		}




	public ArrayList<Bullet> getBullets() {
		return bullets;
	}




	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	void fire() {
		Bullet bullet = new Bullet(x,y);
		bullets.add(bullet);
		
		

}
//void fall() {
//	if(y>=G_HEIGHT) {
//		y=G_HEIGHT;
//	}
//y+=GRAVITY;	
//}
}
