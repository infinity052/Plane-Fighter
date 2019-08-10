
public class Enemy extends Sprite implements GameConstants{
	
	Enemy(int x, int y){
		this.x=x;
		this.y=y;
		height=50;
		width=70;
		speed=-10;
		image=ImageLoader.loadImage("ufo.jpg");
		}
@Override
protected
void move() {
	x+=speed;
}

}
