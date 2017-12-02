

import com.jogamp.opengl.GL2;

public class AnimationData {
	
	private AnimationDef def;
	private int curFrame;
	private float secsUnitNextFrame;
	private boolean walk;
	private GL2 gl;
	private int[] spriteSize = new int[2];
	private int counter;
	private String charDirection;
	private ImageProcess img = new ImageProcess();
	
	public AnimationData(GL2 gl)
	{
		this.gl = gl;
		curFrame = 0;
		def = new AnimationDef(gl, spriteSize);
		counter = 0;
	}

	public void update(float deltaTime, String direction, boolean status)
	{
		charDirection = direction;
		walk = status;
		
		secsUnitNextFrame = 
				(deltaTime / 10000000 + def.getFrameArray(charDirection, walk).get(curFrame).frameTimeSecs / 1000000000) / 20000;
		 
		if (counter == ((int) (secsUnitNextFrame) - 1))
			curFrame = (curFrame + 1) % 2;	 
		
		//System.out.println(secsUnitNextFrame);
		
		// update the counter
		counter = (counter + 1) % (int) secsUnitNextFrame;
	}
	 
	public void draw(int x, int y)
	{
		img.glDrawSprite(gl, def.getFrameArray(charDirection, walk).get(curFrame).image, x, y, spriteSize[0], spriteSize[1]);
	}

	public int[] getSpriteSize()
	{
		return spriteSize;
	}
}
