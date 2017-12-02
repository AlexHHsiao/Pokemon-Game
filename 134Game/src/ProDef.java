

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

public class ProDef {

	private ArrayList<FrameDef> framesUp;
	private ArrayList<FrameDef> framesDown;
	private ArrayList<FrameDef> framesRight;
	private ArrayList<FrameDef> framesLeft;
	private ArrayList<FrameDef> framesStandUp;
	private ArrayList<FrameDef> framesStandDown;
	private ArrayList<FrameDef> framesStandRight;
	private ArrayList<FrameDef> framesStandLeft;
	
	private ImageProcess img = new ImageProcess();
	
	public ProDef(GL2 gl, int[] spriteSize)
	{
		framesUp = new ArrayList<FrameDef>();
		framesDown = new ArrayList<FrameDef>();
		framesRight = new ArrayList<FrameDef>();
		framesLeft = new ArrayList<FrameDef>();
		framesStandUp = new ArrayList<FrameDef>();
		framesStandDown = new ArrayList<FrameDef>();
		framesStandRight = new ArrayList<FrameDef>();
		framesStandLeft = new ArrayList<FrameDef>();
		
		
		framesLeft.add(new FrameDef(img.glTexImageTGAFile(gl, "proImage/proWalkLeftR.tga", spriteSize), System.nanoTime()));
		framesLeft.add(new FrameDef(img.glTexImageTGAFile(gl, "proImage/proWalkLeftL.tga", spriteSize), System.nanoTime()));
		
		framesRight.add(new FrameDef(img.glTexImageTGAFile(gl, "proImage/proWalkRightR.tga", spriteSize), System.nanoTime()));
		framesRight.add(new FrameDef(img.glTexImageTGAFile(gl, "proImage/proWalkRightL.tga", spriteSize), System.nanoTime()));
		
		framesUp.add(new FrameDef(img.glTexImageTGAFile(gl, "proImage/proWalkUpR.tga", spriteSize), System.nanoTime()));
		framesUp.add(new FrameDef(img.glTexImageTGAFile(gl, "proImage/proWalkUpL.tga", spriteSize), System.nanoTime()));
		
		framesDown.add(new FrameDef(img.glTexImageTGAFile(gl, "proImage/proWalkDownR.tga", spriteSize), System.nanoTime()));
		framesDown.add(new FrameDef(img.glTexImageTGAFile(gl, "proImage/proWalkDownL.tga", spriteSize), System.nanoTime()));
	}
	
	public ArrayList<FrameDef> getFrameArray(String charDirection, boolean walk)
	{
		if (charDirection.equalsIgnoreCase("right"))
			if (walk)
				return framesRight;
			else
				return framesStandRight;
		else if (charDirection.equalsIgnoreCase("up"))
			if (walk)
				return framesUp;
			else
				return framesStandUp;	
		else if (charDirection.equalsIgnoreCase("down"))
			if (walk)
				return framesDown;
			else
				return framesStandDown;
		else 
			if (walk)
				return framesLeft;
			else
				return framesStandLeft;
	}
}
