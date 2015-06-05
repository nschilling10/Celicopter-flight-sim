import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.math.*;
/**
 * Represents an object on the screen
 * Object can be defined by the x and y coordinates of it's topmost corner
 * @author NathanS
 *
 */
public class ScreenObject{
	protected double xPosition;
	protected double yPosition;
	protected double dx;
	protected double dy;
	protected Polygon shape;
	protected BufferedImage sprite;
	protected double spatialFrequency;
	protected double modulation;
	
	/**
	 * Most basic constructor; represents on-screen a filled-in circle that starts 
	 * with it's top right-hand corner in the top right hand corner of the window 
	 * [coordinates (0,0)], from rest, with spatial frequency 182 and modulation 1
	 */
	public ScreenObject(){
		xPosition=0;
		yPosition=0;
		dx=0;
		dy=0;
		shape=null;
		sprite=null;
		spatialFrequency=182;
		modulation=1;
	}
	
	/**
	 * Next-level basic constructor; represents on-screen a filled-in circle that starts 
	 * with it's top right-hand corner at coordinates specified by the user, from rest, 
	 * with spatial frequency 182 and modulation 1
	 * @param startX X-coordinate of the top right-hand corner where the circle starts
	 * @param startY Y-coordinate of the top right-hand corner where the circle starts
	 */
	public ScreenObject(double startX, double startY){
		xPosition=startX;
		yPosition=startY;
		dx=0;
		dy=0;
		shape=null;
		sprite=null;
		spatialFrequency=182;
		modulation=1;
	}

	/**
	 * Fairly basic constructor; represents on-screen a filled-in circle that starts 
	 * with it's top right-hand corner at coordinates specified by the user, x and y speed specified by the user
	 * with spatial frequency 182 and modulation 1
	 * @param startX X-coordinate of the top right-hand corner where the circle starts
	 * @param startY Y-coordinate of the top right-hand corner where the circle starts
	 */
	public ScreenObject(double startX, double startY, double startDx, double startDy){
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		shape=null;
		sprite=null;
		spatialFrequency=182;
		modulation=1;
	}

	/**
	 * Most basic constructor; represents on-screen a filled-in circle that starts 
	 * with it's top right-hand corner in the top right hand corner of the window 
	 * [coordinates (0,0)], from rest, with user-specified spatial frequency 182 and modulation 1
	 * @param SF The spatial frequency
	 */
	public ScreenObject(double SF){
		xPosition=0;
		yPosition=0;
		dx=0;
		dy=0;
		shape=null;
		sprite=null;
		spatialFrequency=SF;
		modulation=1;
	}
	
	/**
	 * Next-level basic constructor; represents on-screen a filled-in circle with a 10 pixel radius that starts 
	 * with it's top right-hand corner at coordinates specified by the user, from rest, 
	 * with spatial frequency 182 and modulation 1
	 * @param SF The spatial frequency
	 * @param startX X-coordinate of the top right-hand corner where the circle starts
	 * @param startY Y-coordinate of the top right-hand corner where the circle starts
	 */
	public ScreenObject(double SF,double startX, double startY){
		xPosition=startX;
		yPosition=startY;
		dx=0;
		dy=0;
		shape=null;
		sprite=null;
		spatialFrequency=SF;
		modulation=1;
	}

	/**
	 * Fairly basic constructor; represents on-screen a filled-in circle with a 10 pixel radius that starts 
	 * with it's top right-hand corner at coordinates specified by the user, x and y speed specified by the user
	 * with spatial frequency 182 and modulation 1
	 * @param SF The spatial frequency
	 * @param startX X-coordinate of the top right-hand corner where the circle starts
	 * @param startY Y-coordinate of the top right-hand corner where the circle starts
	 */
	public ScreenObject(double SF,double startX, double startY, double startDx, double startDy){
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		shape=null;
		sprite=null;
		spatialFrequency=SF;
		modulation=1;
	}
	
	/**
	 * Fairly basic constructor; represents on-screen a filled-in circle with a 10 pixel radius that starts 
	 * with it's top right-hand corner at coordinates specified by the user, x and y speed specified by the user
	 * with spatial frequency 182 and modulation 1
	 * @param radius, in pixels
	 * @param startX X-coordinate of the top right-hand corner where the circle starts
	 * @param startY Y-coordinate of the top right-hand corner where the circle starts
	 */
	public ScreenObject(double r,double startX, double startY, double startDx, double startDy,double SF, double mod){
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		width=2*r;
		height=2*r;
		shape=null;
		sprite=null;
		spatialFrequency=182;
		modulation=1;
	}
	
	/**
	 * Constructs an on-screen object meant to represent a regular polygon
	 * This constructor assumes each side is to be 10 pixels long and the shape initially starts from rest
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param numberOfSides The number of sides the polygon to be rendered on the screen has
	 */
	public ScreenObject(double startX, double startY, int numberOfSides){
		double sideLength=10;
		xPosition=startX;
		yPosition=startY;
		dx=0;
		dy=0;
		int[] xPts=new int[numberOfSides];
		int[] yPts=new int[numberOfSides];
		double intAngle=2*Math.PI/numberOfSides;
		double r=sideLength/(2*Math.sin(intAngle));
		double theta=(3*Math.PI/2)+intAngle/2;
		for(int i=0;i<=numberOfSides;i++){
			xPts[i]=(int) (r*Math.sin(theta+i*intAngle));
			yPts[i]=(int) (r*Math.cos(theta+i*intAngle));
		}
		shape=new Polygon(xPts,yPts,numberOfSides);
		shape.translate((int) (startX+shape.getBounds2D().getWidth()/2), (int) (startY+shape.getBounds2D().getHeight()/2));
		width=shape.getBounds2D().getWidth();
		height=shape.getBounds2D().getHeight();
		sprite=null;
		spatialFrequency=182;
		modulation=1;
	}
	
	/**
	 * Constructs an on-screen object meant to represent a regular polygon
	 * This constructor assumes each side is to be 10 pixels long and the shape initially starts from rest (dx & dy=0)
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param numberOfSides The number of sides the polygon to be rendered on the screen has
	 * @param sideLength The side-length, in pixels, of the polygon
	 */
	public ScreenObject(double startX, double startY, int numberOfSides, double sideLength){
		xPosition=startX;
		yPosition=startY;
		dx=0;
		dy=0;
		int[] xPts=new int[numberOfSides];
		int[] yPts=new int[numberOfSides];
		double intAngle=2*Math.PI/numberOfSides;
		double r=sideLength/(2*Math.sin(intAngle));
		double theta=(3*Math.PI/2)+intAngle/2;
		for(int i=0;i<=numberOfSides;i++){
			xPts[i]=(int) (r*Math.sin(theta+i*intAngle));
			yPts[i]=(int) (r*Math.cos(theta+i*intAngle));
		}
		shape=new Polygon(xPts,yPts,numberOfSides);
		shape.translate((int) (startX+shape.getBounds2D().getWidth()/2), (int) (startY+shape.getBounds2D().getHeight()/2));
		width=shape.getBounds2D().getWidth();
		height=shape.getBounds2D().getHeight();
		sprite=null;
		spatialFrequency=182;
		modulation=1;
	}
	
	/**
	 * Constructs an on-screen object meant to represent a regular polygon
	 * This constructor assumes each side is to be 10 pixels long and the shape 
	 * initially starts with speed in the x and y direction specified by the user
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param startDx The starting speed of the polygon in the x-direction 
	 * @param startDy The starting speed of the polygon in the y-direction (up and down)
	 * @param numberOfSides The number of sides the polygon to be rendered on the screen has
	 */
	public ScreenObject(double startX, double startY, double startDx, double startDy, int numberOfSides){
		double sideLength=10;
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		int[] xPts=new int[numberOfSides];
		int[] yPts=new int[numberOfSides];
		double intAngle=2*Math.PI/numberOfSides;
		double r=sideLength/(2*Math.sin(intAngle));
		double theta=(3*Math.PI/2)+intAngle/2;
		for(int i=0;i<=numberOfSides;i++){
			xPts[i]=(int) (r*Math.sin(theta+i*intAngle));
			yPts[i]=(int) (r*Math.cos(theta+i*intAngle));
		}
		shape=new Polygon(xPts,yPts,numberOfSides);
		shape.translate((int) (startX+shape.getBounds2D().getWidth()/2), (int) (startY+shape.getBounds2D().getHeight()/2));
		width=shape.getBounds2D().getWidth();
		height=shape.getBounds2D().getHeight();
		sprite=null;
		spatialFrequency=182;
		modulation=1;
	}
	
	/**
	 * Constructs an on-screen object meant to represent a regular polygon
	 * This constructor allows the user to specify a side-length and the shape 
	 * initially starts with speed in the x and y direction specified by the user
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param startDx The starting speed of the polygon in the x-direction 
	 * @param startDy The starting speed of the polygon in the y-direction (up and down)
	 * @param numberOfSides The number of sides the polygon to be rendered on the screen has
	 * @param sideLength Length of each side, in pixels
	 */
	public ScreenObject(double startX, double startY, double startDx, double startDy, int numberOfSides, double sideLength){
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		int[] xPts=new int[numberOfSides];
		int[] yPts=new int[numberOfSides];
		double intAngle=2*Math.PI/numberOfSides;
		double r=sideLength/(2*Math.sin(intAngle));
		double theta=(3*Math.PI/2)+intAngle/2;
		for(int i=0;i<=numberOfSides;i++){
			xPts[i]=(int) (r*Math.sin(theta+i*intAngle));
			yPts[i]=(int) (r*Math.cos(theta+i*intAngle));
		}
		shape=new Polygon(xPts,yPts,numberOfSides);
		shape.translate((int) (startX+shape.getBounds2D().getWidth()/2), (int) (startY+shape.getBounds2D().getHeight()/2));
		width=shape.getBounds2D().getWidth();
		height=shape.getBounds2D().getHeight();
		sprite=null;
		spatialFrequency=182;
		modulation=1;
	}
	

	public ScreenObject(double startX,double startY, BufferedImage image){
		xPosition=startX;
		yPosition=startY;
		dx=0;
		dy=0;
		width=image.getWidth();
		height=image.getHeight();
		shape=null;
		sprite=image;
		spatialFrequency=182;
		modulation=1;
	}
	public ScreenObject(double startX, double startY, double startDx, double startDy, BufferedImage image){
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		width=image.getWidth();
		height=image.getHeight();
		shape=null;
		sprite=image;
		spatialFrequency=182;
		modulation=1;
	}
	
	/**
	 * Constructs an on-screen object meant to represent a regular polygon
	 * This constructor assumes each side is to be 10 pixels long and the shape initially starts from rest
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param numberOfSides The number of sides the polygon to be rendered on the screen has
	 * @param startSF The user-specified starting spatial-frequency
	 */
	public ScreenObject(double startX, double startY, double startSF,int numberOfSides){
		double sideLength=10;
		xPosition=startX;
		yPosition=startY;
		dx=0;
		dy=0;
		int[] xPts=new int[numberOfSides];
		int[] yPts=new int[numberOfSides];
		double intAngle=2*Math.PI/numberOfSides;
		double r=sideLength/(2*Math.sin(intAngle));
		double theta=(3*Math.PI/2)+intAngle/2;
		for(int i=0;i<=numberOfSides;i++){
			xPts[i]=(int) (r*Math.sin(theta+i*intAngle));
			yPts[i]=(int) (r*Math.cos(theta+i*intAngle));
		}
		shape=new Polygon(xPts,yPts,numberOfSides);
		shape.translate((int) (startX+shape.getBounds2D().getWidth()/2), (int) (startY+shape.getBounds2D().getHeight()/2));
		width=shape.getBounds2D().getWidth();
		height=shape.getBounds2D().getHeight();
		sprite=null;
		spatialFrequency=startSF;
		modulation=1;
	}
	
	/**
	 * Constructs an on-screen object meant to represent a regular polygon
	 * This constructor assumes each side is to be 10 pixels long and the shape initially starts from rest (dx & dy=0)
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param startSF The user-specified starting spatial-frequency
	 * @param numberOfSides The number of sides the polygon to be rendered on the screen has
	 * @param sideLength The side-length, in pixels, of the polygon
	 */
	public ScreenObject(double startX, double startY, double startSF,int numberOfSides, double sideLength){
		xPosition=startX;
		yPosition=startY;
		dx=0;
		dy=0;
		int[] xPts=new int[numberOfSides];
		int[] yPts=new int[numberOfSides];
		double intAngle=2*Math.PI/numberOfSides;
		double r=sideLength/(2*Math.sin(intAngle));
		double theta=(3*Math.PI/2)+intAngle/2;
		for(int i=0;i<=numberOfSides;i++){
			xPts[i]=(int) (r*Math.sin(theta+i*intAngle));
			yPts[i]=(int) (r*Math.cos(theta+i*intAngle));
		}
		shape=new Polygon(xPts,yPts,numberOfSides);
		shape.translate((int) (startX+shape.getBounds2D().getWidth()/2), (int) (startY+shape.getBounds2D().getHeight()/2));
		width=shape.getBounds2D().getWidth();
		height=shape.getBounds2D().getHeight();
		sprite=null;
		spatialFrequency=startSF;
		modulation=1;
	}
	
	/**
	 * Constructs an on-screen object meant to represent a regular polygon
	 * This constructor assumes each side is to be 10 pixels long and the shape 
	 * initially starts with speed in the x and y direction specified by the user
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param startDx The starting speed of the polygon in the x-direction 
	 * @param startDy The starting speed of the polygon in the y-direction (up and down)
	 * @param startSF The user-specified starting spatial-frequency
	 * @param sideLength The side-length, in pixels, of the polygon
	 * @param numberOfSides The number of sides the polygon to be rendered on the screen has
	 */
	public ScreenObject(double startX, double startY, double startDx, double startDy, double startSF,int numberOfSides){
		double sideLength=10;
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		int[] xPts=new int[numberOfSides];
		int[] yPts=new int[numberOfSides];
		double intAngle=2*Math.PI/numberOfSides;
		double r=sideLength/(2*Math.sin(intAngle));
		double theta=(3*Math.PI/2)+intAngle/2;
		for(int i=0;i<=numberOfSides;i++){
			xPts[i]=(int) (r*Math.sin(theta+i*intAngle));
			yPts[i]=(int) (r*Math.cos(theta+i*intAngle));
		}
		shape=new Polygon(xPts,yPts,numberOfSides);
		shape.translate((int) (startX+shape.getBounds2D().getWidth()/2), (int) (startY+shape.getBounds2D().getHeight()/2));
		width=shape.getBounds2D().getWidth();
		height=shape.getBounds2D().getHeight();
		sprite=null;
		spatialFrequency=startSF;
		modulation=1;
	}
	
	/**
	 * Constructs an on-screen object meant to represent a regular polygon
	 * This constructor assumes each side is to be 10 pixels long and the shape 
	 * initially starts with speed in the x and y direction specified by the user
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param startDx The starting speed of the polygon in the x-direction 
	 * @param startDy The starting speed of the polygon in the y-direction (up and down)
	 * @param startSF The user-specified starting spatial-frequency
	 * @param sideLength The side-length, in pixels, of the polygon
	 * @param numberOfSides The number of sides the polygon to be rendered on the screen has
	 * @param sideLength Length of each side, in pixels
	 */
	public ScreenObject(double startX, double startY, double startDx, double startDy, double startSF,int numberOfSides, double sideLength){
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		int[] xPts=new int[numberOfSides];
		int[] yPts=new int[numberOfSides];
		double intAngle=2*Math.PI/numberOfSides;
		double r=sideLength/(2*Math.sin(intAngle));
		double theta=(3*Math.PI/2)+intAngle/2;
		for(int i=0;i<=numberOfSides;i++){
			xPts[i]=(int) (r*Math.sin(theta+i*intAngle));
			yPts[i]=(int) (r*Math.cos(theta+i*intAngle));
		}
		shape=new Polygon(xPts,yPts,numberOfSides);
		shape.translate((int) (startX+shape.getBounds2D().getWidth()/2), (int) (startY+shape.getBounds2D().getHeight()/2));
		width=shape.getBounds2D().getWidth();
		height=shape.getBounds2D().getHeight();
		sprite=null;
		spatialFrequency=startSF;
		modulation=1;
	}
	/**
	 * Constructs a ScreenObject meant to represent an on-screen image rather than a shape.
	 * The image starts from rest
	 * @param startX X-coordinate of the top right-hand corner where the object starts
	 * @param startY Y-coordinate of the top right-hand corner where the object starts
	 * @param startSF The user-specified starting spatial-frequency
	 * @param startMod The user-specified starting modulation as a decimal 0-1 inclusive
	 * @param image The BufferedImage meant to represent the object on-screen. Similar to a sprite in videogames
	 */
	public ScreenObject(double startX,double startY, double startSF, double startMod,BufferedImage image){
		xPosition=startX;
		yPosition=startY;
		dx=0;
		dy=0;
		width=image.getWidth();
		height=image.getHeight();
		shape=null;
		sprite=image;
		spatialFrequency=startSF;
		modulation=1;
	}
	
	/**
	 * 
	 * @param startX
	 * @param startY
	 * @param startDx
	 * @param startDy
	 * @param startSF
	 * @param startMod
	 * @param image
	 */
	public ScreenObject(double startX, double startY, double startDx, double startDy, double startSF,double startMod,BufferedImage image){
		xPosition=startX;
		yPosition=startY;
		dx=startDx;
		dy=startDy;
		width=image.getWidth();
		height=image.getHeight();
		shape=null;
		sprite=image;
		spatialFrequency=startSF;
		modulation=1;
	}
	
	/**
	 * Draws the object in the specified graphics context
	 * @param g The Graphics context to draw the object in
	 */
	public void draw(Graphics g){
		
	}
	public void move(long time){
		
	}

}