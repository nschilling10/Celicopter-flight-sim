import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


/**
 * Represents a group of Target Objects
 * @author Nathan_Schilling
 *
 */
public class TargetGroup extends Target{
	private ArrayList<Target> targets;
	private Rectangle boundary;

	public TargetGroup(){
		super();
		targets=new ArrayList<Target>();
	}

	public TargetGroup(int x, int y,double[] radii,double[] thetas,Target[] scs){
		super();
		targets=new ArrayList<Target>();
		for(int i=0;i<scs.length;i++){
			scs[i].xCenterPosition=0;
			scs[i].yCenterPosition=0;
			scs[i].dx=0;
			scs[i].dy=0;
		}
		if(radii.length==thetas.length && radii.length==scs.length){
			for(int i=0;i<scs.length;i++){
				scs[i].xCenterPosition=(int) (x+radii[i]*Math.cos(Math.PI*thetas[i]/180));
				scs[i].yCenterPosition=(int) (y+radii[i]*Math.sin(Math.PI*thetas[i]/180));
			}
			for(int i=0;i<scs.length;i++){
				if(!targets.contains(scs[i]))
					targets.add(scs[i]);
				else
					targets.add(scs[i].clone());
			}
		}
		else{
			System.err.println("Fatal Error--array dimentions no not agree");
			System.exit(0);
		}
		xCenterPosition=x;
		yCenterPosition=y;
	}
	
	public TargetGroup(int x, int y,double dx,double dy,double[] radii,double[] thetas,Target[] scs){
		super();
		targets=new ArrayList<Target>();
		for(int i=0;i<scs.length;i++){
			scs[i].xCenterPosition=0;
			scs[i].yCenterPosition=0;
			scs[i].dx=0;
			scs[i].dy=0;
		}
		if(radii.length==thetas.length && radii.length==scs.length){
			for(int i=0;i<scs.length;i++){
				scs[i].xCenterPosition=(int) (x+radii[i]*Math.cos(Math.PI*thetas[i]/180));
				scs[i].yCenterPosition=(int) (y+radii[i]*Math.sin(Math.PI*thetas[i]/180));
			}
			for(int i=0;i<scs.length;i++)
				targets.add(scs[i]);
		}
		else{
			System.err.println("Fatal Error--array dimentions no not agree");
			System.exit(0);
		}
		xCenterPosition=x;
		yCenterPosition=y;
		this.dx=dx;
		this.dy=dy;
	}
	
	public TargetGroup(Target t){
		super();
		targets=new ArrayList<Target>();
		xCenterPosition=t.xCenterPosition;
		yCenterPosition=t.yCenterPosition;
		dx=t.dx;
		dy=t.dy;
		this.add(t);
		calculateCenter();
	}

	public TargetGroup(ArrayList<Target> ts){
		super();
		targets=ts;
		updateDx(this.dx);
		updateDy(this.dy);
		calculateCenter();
	}

	public void updateDx(){
		for(Target t:targets)
			t.dx=this.dx;
	}

	public void updateDy(){
		for(Target t:targets)
			t.dy=this.dy;
	}
	
	public void updateDx(double dx){
		this.dx=dx;
		for(Target t:targets)
			t.dx=this.dx;
	}

	public void updateDy(double dy){
		
		this.dy=dy;
		for(Target t:targets){
			t.dy=this.dy;
			
		}
	}

	private void caclculateBoundary() {
		if(!targets.isEmpty()){
			Target firstTarget=targets.get(0);
			int leastX=(int) Math.round(firstTarget.xCenterPosition-firstTarget.getPixelDiameter()/2);
			int leastY=(int) Math.round(firstTarget.yCenterPosition-firstTarget.getPixelDiameter()/2);
			int maxX=(int) Math.round(firstTarget.xCenterPosition+firstTarget.getPixelDiameter()/2);
			int maxY=(int) Math.round(firstTarget.yCenterPosition+firstTarget.getPixelDiameter()/2);
			int LeastX,LeastY,MaxX,MaxY;
			for(Target t:targets){
				if(t.shape==null)
				{
					LeastX=(int) Math.round(t.xCenterPosition-t.getPixelDiameter()/2);
					LeastY=(int) Math.round(t.yCenterPosition-t.getPixelDiameter()/2);
					MaxX=(int) Math.round(t.xCenterPosition+t.getPixelDiameter()/2);
					MaxY=(int) Math.round(t.yCenterPosition+t.getPixelDiameter()/2);
				}
				else {
					LeastX=(int) Math.round(t.xCenterPosition-t.shape.getBounds2D().getWidth()/2);
					LeastY=(int) Math.round(t.yCenterPosition-t.shape.getBounds2D().getHeight()/2);
					MaxX=(int) Math.round(t.xCenterPosition+t.shape.getBounds2D().getWidth()/2);
					MaxY=(int) Math.round(t.yCenterPosition+t.shape.getBounds2D().getHeight()/2);
				}
				if(LeastX<leastX)
					leastX=LeastX;
				if(LeastY<leastY)
					leastY=LeastY;
				if(MaxX>maxX)
					maxX=MaxX;
				if(MaxY>maxY)
					maxY=MaxY;
			}
			boundary=new Rectangle(leastX,leastY,maxX-leastX,maxY-leastY);
		}

	}

	public boolean add(Target t){
		if(targets!=null){
			boolean b=true;
			for(Target tt:targets)
				if(tt==t)
					b=false;
			if(b)
				b=targets.add(t);
			else
				System.err.println("This element is already in the list");
				t.dx=dx;
				t.dy=dy;
				calculateCenter();
				return b;
			}
		else
			System.err.println("This group has a null targets/is undefined");
		return false;
	}

	public boolean remove(Target t){
		if(targets!=null)
			if(!targets.isEmpty()){
				boolean b=targets.remove(t);
				calculateCenter();
				return b;
			}
			else
				System.err.println("This group is empty");
		else
			System.err.println("This group has a null targets/is undefined");
		return false;
	}

	public Target remove(int index){
		if(targets!=null)
			if(!targets.isEmpty()){
				Target b=targets.remove(index);
				calculateCenter();
				return b;
			}
			else
				System.err.println("This group is empty");
		else
			System.err.println("This group has a null targets/is undefined");
		return null;
	}

	public Target get(int index){
		if(targets!=null)
			if(!targets.isEmpty())
				return targets.get(index);
			else
				System.err.println("This group is empty");
		else
			System.err.println("This group has a null targets/is undefined");
		return null;
	}

	/**
	 * Calculates the center of this Target group based on an average the centers of each target
	 */
	public void calculateCenter(){
		if(!targets.isEmpty()&& targets!=null){
			caclculateBoundary();
//			xCenterPosition=(int) Math.round(boundary.getX()+boundary.getWidth()/2);
//			yCenterPosition=(int) Math.round(boundary.getY()+boundary.getHeight()/2);
			double muX=0;
			double muY=0;
			for(Target t:targets){
				muX+=t.xCenterPosition*1.0/targets.size();
				muY+=t.yCenterPosition*1.0/targets.size();
			}
			xCenterPosition=(int) Math.round(muX);
			yCenterPosition=(int) Math.round(muY);
		}
		
	}

	public void move(int newX,int newY, int screenWidth,int screenHeight){
		int deltaX=newX-xCenterPosition;
		int deltaY=newY-yCenterPosition;
		if(targets!=null && !targets.isEmpty()){
			Target leftTarget=null,topTarget=null,bottomTarget=null,rightTarget=null;
			double minXdist=0,minYdist=0;
			int mostTx=0,mostTy=0;
			for(Target t:targets){
				int newTx=t.xCenterPosition+deltaX;
				int newTy=t.yCenterPosition+deltaY;
				if(t.distanceFromLeftSide(newTx)<minXdist){
					leftTarget=t;
					mostTx=newTx;
					minXdist=leftTarget.distanceFromLeftSide(mostTx);
				}
				if(t.distanceFromRightSide(newTx, screenWidth)<minXdist){
					rightTarget=t;
					mostTx=newTx;
					minXdist=rightTarget.distanceFromRightSide(mostTx, screenWidth);
				}
				if(t.distanceFromTop(newTy)<minYdist){
					topTarget=t;
					mostTy=newTy;
					minYdist=topTarget.distanceFromTop(mostTy);
				}
				if(t.distanceFromBottom(newTy, screenHeight)<minYdist){
					bottomTarget=t;
					mostTy=newTy;
					minYdist=bottomTarget.distanceFromBottom(mostTy, screenHeight);
				}
			}
			if(leftTarget!=null || rightTarget!=null){
				if(leftTarget!=null && rightTarget!=null){
					deltaX=0;
					System.err.println("Error--group to large or spread out to fit sideways on screen.");
				}
				else{ if(leftTarget!=null)
						deltaX-=(int) Math.round(leftTarget.distanceFromLeftSide(mostTx));
					  else
						deltaX+=(int) Math.round(rightTarget.distanceFromRightSide(mostTx,screenWidth));
				}
			}
			if(topTarget!=null || bottomTarget!=null){
				if(topTarget!=null && bottomTarget!=null){
					deltaY=0;
					System.err.println("Error--group to large or spread out to fit sideways on screen.");
				}
				else{ if(topTarget!=null)
						deltaY-=(int) Math.round(topTarget.distanceFromTop(mostTy));
					  else
						deltaY+=(int) Math.round(bottomTarget.distanceFromBottom(mostTy,screenHeight));
				}
			}
		xCenterPosition+=deltaX;
		yCenterPosition+=deltaY;
		for(Target t:targets){
			t.xCenterPosition+=deltaX;
			t.yCenterPosition+=deltaY;
		}
		}
	}
	
	public ArrayList<Target> getTargets(){
		return targets;
	}
	
	public void move(long delta,int screenWidth,int screenHeight){
		setDx(delta);
		setDy(delta);
		updateDx();
		updateDy();
		boolean groupAtRightSide=false,groupAtTop=false,groupAtLeftSide=false,groupAtBottom=false;
		if(targets!=null && !targets.isEmpty()){
			for(Target t:targets){
				int newX=(int) Math.round(t.xCenterPosition+dx*delta);
				int newY=(int) Math.round(t.yCenterPosition+dy*delta);
				if(!groupAtRightSide && t.distanceFromRightSide(newX, screenWidth)<0)
					groupAtRightSide=true;
				if(!groupAtLeftSide && t.distanceFromLeftSide(newX)<0)
					groupAtLeftSide=true;
				if(!groupAtTop && t.distanceFromTop(newY)<0)
					groupAtTop=true;
				if(!groupAtBottom && t.distanceFromBottom(newY, screenHeight)<0)
					groupAtBottom=true;
			}
			if(groupAtLeftSide || groupAtRightSide){
				if(groupAtLeftSide && groupAtRightSide || (getDynamicMod()!=null && getDynamicMod().getStick()!=null))
					updateDx(0);
				else{
					updateDx(-dx);

				}
			}
			if(groupAtTop || groupAtBottom){
				if(groupAtTop && groupAtBottom || (getDynamicMod()!=null && getDynamicMod().getStick()!=null))
					updateDy(0);
				else
					updateDy(-dy);

			}
			for(Target t:targets)
				t.move(delta, screenWidth, screenHeight);
			calculateCenter();
		}
	}

	public void draw(Graphics2D g){
		//g.fillRect(xCenterPosition, yCenterPosition, 3, 3);
		if(targets!=null && !targets.isEmpty())
			for(Target t:targets)
				t.draw(g);
	}
}
