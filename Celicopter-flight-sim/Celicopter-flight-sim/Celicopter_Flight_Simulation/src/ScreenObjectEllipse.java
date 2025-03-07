import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Expansion of the ScreenObject class to better handle ellipses.
 * Under the hood, the ellipse is actually an on-screen image with an ellipse drawn on-top of it
 * @author Nathan_Schilling
 *
 */
public class ScreenObjectEllipse extends ScreenObject{
	/**Amount, in degrees, the ellipse is rotated about it's center*/
	private double theta;
	/**Height of the ellipse. The pixel diameter field is used to determine the width of the ellipse*/
	private double pixelHeight=5;

	public ScreenObjectEllipse(){
		xCenterPosition=0;
		yCenterPosition=0;
		dx=0;
		dy=0;
		shape=null;
		setPixelDiameter(10);
		modulation=1.0;
		setColor(Color.black);
		setTheta(0);
	}

	/**
	 * Next-level basic constructor; represents on-screen a filled-in ellipse that starts 
	 * with it's center at coordinates specified by the user, from rest, 
	 * with width 10 pixels, height 10 pixels and modulation 1.0
	 * @param startX X-coordinate of the center where the ellipse starts
	 * @param startY Y-coordinate of the center where the ellipse starts
	 */
	public ScreenObjectEllipse(int startX, int startY){
		xCenterPosition=startX;
		yCenterPosition=startY;
		dx=0;
		dy=0;
		shape=null;
		setPixelDiameter(10);
		modulation=1;
		setColor(Color.black);
		setTheta(0);
	}

	public ScreenObjectEllipse(int startX, int startY,double theta){
		xCenterPosition=startX;
		yCenterPosition=startY;
		dx=0;
		dy=0;
		shape=null;
		setPixelDiameter(10);
		modulation=1;
		setColor(Color.black);
		this.setTheta(theta);
	}

	/**
	 * Represents on-screen a filled-in ellipse that starts 
	 * with it's center at x,y coordinates specified by the user, 
	 * and speed along the x and y axes specified by the user.
	 * This constructor assumes the ellipse width is 10 pixels, height is 10 pixels, modulation is 1.0.
	 * @param startX X-coordinate of the center where the circle starts
	 * @param startY Y-coordinate of the center where the circle starts
	 * @param startDx start velocity along x-axis (positive to right, negative to left)
	 * @param startDy start velocity along y-axis (positive down, negative up)
	 */
	public ScreenObjectEllipse(int startX, int startY, double startDx, double startDy){
		xCenterPosition=startX;
		yCenterPosition=startY;
		dx=startDx;
		dy=startDy;
		shape=null;
		setPixelDiameter(10);
		modulation=1;
		setColor(Color.black);
		setTheta(0);
	}

	public ScreenObjectEllipse(int startX, int startY, double startDx, double startDy,double t){
		xCenterPosition=startX;
		yCenterPosition=startY;
		dx=startDx;
		dy=startDy;
		shape=null;
		setPixelDiameter(10);
		modulation=1;
		setColor(Color.black);
		setTheta(t);
	}

	/**
	 * Super-expanded constructor for a ScreenObjectEllipse that is represented on-screen by an Image. Has maximum functionality
	 * with the user being able to specify where the center starts in the x,y coordinate space, velocities along both 
	 * the x and y axes, the starting width/height of the object in pixels,  and the starting modulation (0-1.0).
	 * @param startX X-coordinate of the center where the object starts
	 * @param startY Y-coordinate of the center where the object starts
	 * @param startDx starting x-axis velocity
	 * @param startDy staring y-axis velocity
	 * @param startSF User-specified starting width/height of the object in pixels
	 * @param startMod The user-specified starting modulation as a decimal 0-1 inclusive
	 */
	public ScreenObjectEllipse(int startX, int startY, double startDx, double startDy, double startSF,double startMod){
		xCenterPosition=startX;
		yCenterPosition=startY;
		dx=startDx;
		dy=startDy;
		shape=null;
		setPixelDiameter(10);
		modulation=startMod;
		setColor(Color.black);
		setTheta(0);
	}

	public ScreenObjectEllipse(int startX, int startY, double startDx, double startDy, double startSF,double startMod,double theta,double height){
		xCenterPosition=startX;
		yCenterPosition=startY;
		dx=startDx;
		dy=startDy;
		shape=null;
		pixelHeight=height;
		setPixelDiameter(10);
		modulation=startMod;
		setColor(Color.black);
		this.setTheta(theta);
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
		double t=this.theta*Math.PI/180;
		double tx=Math.atan2(0.5*pixelHeight*Math.sin(t), -(0.5*getPixelDiameter()*Math.cos(t)));
		double ty=Math.atan2(-0.5*pixelHeight*Math.cos(t), -(0.5*getPixelDiameter()*Math.sin(t)));
		double w=(getPixelDiameter()*Math.cos(tx)*Math.cos(t)-pixelHeight*Math.sin(tx)*Math.sin(t));
		if(!(w>0)){
			tx+=Math.PI;
			w=(getPixelDiameter()*Math.cos(tx)*Math.cos(t)-pixelHeight*Math.sin(tx)*Math.sin(t));
		}
		System.out.println("w="+w);
		double h=(getPixelDiameter()*Math.cos(ty)*Math.sin(t)+pixelHeight*Math.sin(ty)*Math.cos(t));
		if(!(h>0)){
			ty+=Math.PI;
			h=(getPixelDiameter()*Math.cos(ty)*Math.sin(t)+pixelHeight*Math.sin(ty)*Math.cos(t));
		}
		System.out.println("h="+h);
		if(this.sprite!=null)
			this.sprite.flush();
		System.gc();
		this.sprite=new BufferedImage((int)w,(int)h,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=(Graphics2D)sprite.getGraphics();
		System.out.println(g);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setColor(Color.white);
		g.fillRect(0, 0, sprite.getWidth(), sprite.getHeight());
		g.setColor(Color.red);
		g.drawRect(0, 0, sprite.getWidth(), sprite.getHeight());
		g.setColor(getColor());
		//I have no idea why this works...
		g.rotate(-t,w/2,h/2);
		g.translate(w/2,h/2);
		g.fillOval(-(int) getPixelDiameter()/2,-(int) pixelHeight/2,(int) getPixelDiameter(),(int) pixelHeight);
		if(getPixelDiameter()==pixelHeight)
			sprite=null;
	}

	public double getPixelHeight() {
		return pixelHeight;
	}

	public void setPixelHeight(double pixelHeight) {
		this.pixelHeight = pixelHeight;
		setTheta(this.theta);
	}

	public void drawObject(Graphics2D g){
		if(sprite!=null)
			setTheta(getTheta());
		g.drawImage(sprite, (int)xCenterPosition-(int)getPixelDiameter()/2,(int)yCenterPosition-(int)pixelHeight/2,null);
	}

	@Override
	public double distanceFromLeftSide(int x){
		return x-sprite.getWidth()/2;
	}
	/**Calculates the distance from the top-most part of the object to the top of the screen in pixels. Negative if the object is partially obscured by, or completely off the top of the screen.
	 * @param y current y-position in pixels*/
	public double distanceFromTop(int y){
		return y-sprite.getHeight()/2;
	}

	/**Calculates the distance from the bottom-most part of the object to the bottom of the screen in pixels. Negative if the object is partially obscured by, or completely off the bottom of the screen.
	 * @param y current y-position in pixels
	 * @param screenHeigth Current height of the screen in pixels*/
	public double distanceFromBottom(int y,int screenHeight){
		return screenHeight-(y+sprite.getWidth()/2);
	}

	/**Calculates the distance from the right-most part of the object to the right-side of the screen in pixels. Negative if the object is partially obscured by, or completely off the right-side of the screen.
	 * @param x current x-position in pixels
	 * @param screenWidth Current width of the screen in pixels*/
	public double distanceFromRightSide(int x,int screenWidth){
		return screenWidth-(x+sprite.getWidth()/2);
	}

	public void move(long time, int screenWidth, int screenHeight){

		super.move(time, screenWidth, screenHeight);
		/*If the user simply does xCenterPosotion=some value or yCenterPosition=some value, 
		 * these lines ensure that even if the user specifies an off-screen value, 
		 * the object is moved so it is barely on-screen, thereby staying on screen.
		 */
		if(distanceFromLeftSide(xCenterPosition)<0){
			xCenterPosition=(int) (sprite.getWidth()/2);

		}
		if(distanceFromTop(yCenterPosition)<0){
			yCenterPosition=(int) (sprite.getHeight()/2);
		}
		if(distanceFromRightSide(yCenterPosition,screenWidth)<0){
			xCenterPosition=(int) (screenWidth-sprite.getWidth()/2);				
		}

		if(distanceFromBottom(yCenterPosition,screenHeight)<0){
			yCenterPosition=(int) (screenHeight-sprite.getHeight()/2);
		}
	}
	
	/**
	 * Returns a clone of the current object. Used for copying 
	 * @return an object with the exact same properties as this object but a different address in memory 
	 */
	public ScreenObjectEllipse clone(){
		ScreenObjectEllipse o=new ScreenObjectEllipse();
		o.xCenterPosition=this.xCenterPosition;
		o.yCenterPosition=this.yCenterPosition;
		o.dx=this.dx;
		o.dy=this.dy;
		o.shape=this.shape;
		o.sprite=this.sprite;
		o.setPixelDiameter(getPixelDiameter());
		o.modulation=this.modulation;
		o.isOval=this.isOval;
		Color c;
		if(getColor()!=null){
			c=new Color(getColor().getRed(),getColor().getGreen(),getColor().getBlue());
		o.setColor(c);
		}
		o.minY=this.minY;
		o.maxY=this.maxY;
		o.setTheta(this.theta);
		o.setPixelHeight(this.pixelHeight);
		return o;
	}
}
