
public class Bullet extends Sprite implements GameConstants {

	
	Bullet(int x,int y){
		this.x=x+40;
		this.y=y+25 ;
		width=20;
		height=10;
		image=ImageLoader.loadImage("bullet.jpg");
		speed=20;
	}
	
	@Override
	protected void move() {
		x+=speed;
	}
	
	
	
}
