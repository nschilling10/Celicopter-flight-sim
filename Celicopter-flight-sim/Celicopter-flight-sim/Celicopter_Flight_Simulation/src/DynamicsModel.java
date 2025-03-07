import joystick.JInputJoystick;

//TODO Make class...
/**
 * Representation and encapsulation of the equations that 
 * underly helicopter dynamics. 
 * @author Nathan_Schilling and Matt Horr
 *
 */
public class DynamicsModel {

	/**Object representing the joystick. Holds all information about the state of the joystick since it was polled last*/
	private JInputJoystick stick;
	/**Gain for movement along the x-axis*/
	private double xGain;
	/**Gain for movement along the y-axis*/
	private double yGain;

	/**
	 * Creates a basic Dynamics Model object, in this case with a null stick,
	 * xGain and yGain set to zero
	 */
	public DynamicsModel(){
		stick=null;
		xGain=0;
		yGain=0;
	}
	
	/**
	 * Creates a more specialized DynamicsModel based on inputs the user puts in
	 * @param stick JInputJoystick that controls the x and y speed of the ScreenObject this DynamicsModel is associated with
	 * @param xGain Gain for movement along the x-axis (xGain)
	 * @param yGain Gain for movement along the y-axis (yGain)
	 */
	public DynamicsModel(JInputJoystick stick, double xGain, double yGain) {
		this.stick = stick;
		this.xGain = xGain;
		this.yGain = yGain;
	}
	
	public JInputJoystick getStick() {
		return stick;
	}

	public void setStick(JInputJoystick stick) {
		this.stick = stick;
	}

	public double getxGain() {
		return xGain;
	}

	public void setxGain(double xGain) {
		this.xGain = xGain;
	}

	public double getyGain() {
		return yGain;
	}

	public void setyGain(double yGain) {
		this.yGain = yGain;
	}

	
	
	/**
	 * Calculates the dx based on the instantiated Dynamics model, 
	 * joystick input and associated gain (if present)
	 * the start dx, and the time elapsed 
	 * @param time time elapsed since last dx was calculated
	 * @param dx start dx
	 * @return calculated dx
	 */
	public double solveDx(long time,double dx) {
		if(stick!=null){// Check if the controller was found.
			if( !stick.isControllerConnected() )
			{
				System.err.println("No controller found!");
				return dx;
			}

			// Get current state of joystick! And check, if joystick is disconnected.
			if( !stick.pollController() ) {
				System.err.println("Controller disconnected!");
				return dx;
			}
			return xGain*(stick.getXAxisPercentage()-50.0)/100;
		}
		else {
			//Just returns the input speed if not controller was found
			return dx;
		}
	}

	/**
	 * Calculates the dy based on the instantiated Dynamics model, 
	 * joystick input and associated gain (if present)
	 * the start dy, and the time elapsed 
	 * @param time time elapsed since last dx was calculated
	 * @param dy start dy
	 * @return calculated dy
	 */
	public double solveDy(long time,double dy) {
		// TODO Auto-generated method stub
		if(stick!=null){
			// Check if the controller was found.
			if( !stick.isControllerConnected() )
			{
				System.err.println("No controller found!");
				return dy;
			}

			// Get current state of joystick! And check, if joystick is disconnected.
			if( !stick.pollController() ) {
				System.err.println("Controller disconnected!");
				return dy;
			}
			return yGain*(stick.getYAxisPercentage()-50.0)/100;
		}
		else {
			//Just returns the input speed if not controller was found
			return dy;
		}
	}

}
