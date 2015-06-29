import java.awt.Color;
import java.awt.Graphics2D;


public class CursorLine extends Curseor{

	private int screenHeight;
	/**
	 * Most basic constructor; represents on-screen a filled-in Target Object that starts 
	 * with it's top right-hand corner in the top right hand corner of the window 
	 * [coordinates (0,0)], from rest, with spatial frequency 182 and modulation 1
	 */
	public CursorLine(){
		super();
	}
	/**
	 * Next-level basic constructor; represents on-screen a filled-in circle that starts 
	 * with it's top right-hand corner at coordinates specified by the user, from rest, 
	 * with spatial frequency 182 and modulation 1
	 * @param startX X-coordinate of the top right-hand corner where the circle starts
	 * @param startY Y-coordinate of the top right-hand corner where the circle starts
	 */
	public CursorLine(int startX){
		super(startX,100);
	}
	
	public CursorLine(int startX,int sH){
		super(startX,100);
		setScreenHeight(sH);
	}
	public CursorLine(int startX, double startDx, double startDy,double SF,double mod){
		super(startX,100,startDx,startDy,SF,mod);
	}
	public CursorLine(int startX, double startDx, double startDy){
		super(startX,100,startDx,startDy);
	}
	public CursorLine(int startX, double startDx, double startDy,double SF,double mod,int sH){
		super(startX,100,startDx,startDy,SF,mod);
		setScreenHeight(sH);
	}
	public CursorLine(int startX, double startDx, double startDy,int sH){
		super(startX,100,startDx,startDy);
		setScreenHeight(sH);
	}
	
	public void draw(Graphics2D g){
		Color c=g.getColor();
		g.setColor(Color.red);
		g.drawLine(xCenterPosition, 10, xCenterPosition, screenHeight-10);
		g.setColor(c);
	}
	
	public void move(long delta,int screenWidth,int screenHeight){
		setScreenHeight(screenHeight);
		setDx(delta);
		dy=0;
		if(getDynamicMod()!=null)
			getDynamicMod().setyGain(0);
		super.move(delta, screenWidth, screenHeight);
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	public double getError(ScreenObject o){
		return Math.abs(o.xCenterPosition-xCenterPosition);
	}
}
