import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Test extends Canvas{
	/**
	 * 
	 */
	public Rectangle virtualBounds;
	public Target sc;
	public ScreenObject sc1;
	public long lastLoopTime;

	public static void main(String []args){
		Test t=new Test();
		//Test t1=new Test();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//Unpacks this object and gets a list of all peripheries 
		GraphicsDevice[] gs = ge.getScreenDevices();
		JFrame f=new JFrame("Test",gs[0].getConfigurations()[0]);
		System.out.println();
		
		
		/*
		//Gets information related to the display screen. This only works for single-monitor devices
		Toolkit tk = Toolkit.getDefaultToolkit();  

		//Gets the width of the screen and stores it in variable xSize
	    int xSize = ((int) tk.getScreenSize().getWidth()); 

	    //Gets the height of the screen and stores it in variable ySize
	    int ySize = ((int) tk.getScreenSize().getHeight());  

	    //Sets the screen to it's maximum allowable size (maximizes screen)
	    f.setSize(xSize,ySize);
		 */
		
		f.setBounds(t.virtualBounds);
		f.add(t);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}//closes window listener that closes the program when we exit out of the window
		}//closes patch
		);
		
//		//JFrame f1=new JFrame("Test1",gs[1].getConfigurations()[0]);
//		System.out.println(gs[1].getConfigurations()[0].getBounds());
//		//f1.add(t1);
//		//f1.setBounds(gs[1].getConfigurations()[0].getBounds());
//		//f1.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}//closes window listener that closes the program when we exit out of the window
//		}//closes patch
//		);
		//f1.setVisible(true);
//		JFrame f2=new JFrame("");
//		f2.add(t);
//		f2.setSize(900,900);
//		f2.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}//closes window listener that closes the program when we exit out of the window
//		}//closes patch
//		);
//		f2.setVisible(true);
	}

	public Test() {
		super();
		/**This code should work for devices with multiple screens*/
		//Defines a variable to hold the maximum bounds of the screen we will find shortly
		virtualBounds = new Rectangle();
		//Gets an object that encapsulates all periphery devices attached to this computer (screens, printers, etc.)
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//Unpacks this object and gets a list of all peripheries 
		GraphicsDevice[] gs = ge.getScreenDevices();
		//Iterates through this list, going to each device, getting the list of settings with each device, and parsing the maximum window size from these settings  
		for (int j = 0; j < gs.length; j++) {
			//Gets the list of settings associated with the device the for loop is currently on
			GraphicsConfiguration[] gc =gs[j].getConfigurations();
			//Iterates through the settings to get the bounds
			for (int i=0; i < gc.length; i++) {
				virtualBounds =virtualBounds.union(gc[i].getBounds());
			}
		}
		sc=new Target(0,0,0,0,182,1,4);
		sc1=new ScreenObject(1920,100,0.7,-0.7,182,1);
//		BufferedImage op = null;
//		try {
//			op=ImageIO.read(new File("2881806-hoth.jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//sc1=new ScreenObject(0,0,0.7,0.7,op);
		lastLoopTime = System.currentTimeMillis();
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		long delta=-1*(lastLoopTime-System.currentTimeMillis());
		lastLoopTime = System.currentTimeMillis();
		g.fillRect(100, 100, 100, 100);
		g.setColor(Color.black);
		sc.draw(g);
		sc1.draw(g);
		sc.move(delta, getWidth(), getHeight());
		sc1.move(delta, getWidth(), getHeight());
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}
}