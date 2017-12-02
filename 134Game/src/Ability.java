

import com.jogamp.opengl.GL2;

public class Ability {
	
	int image;
	int[] abilitySize = new int[2];
	int[] abilityPos = new int[2];
	int[] charSize;
	private ImageProcess img = new ImageProcess();
	private GL2 gl;
	boolean trigger;
	private String abDirection;
	
	public Ability(GL2 gl, int[] charSize)
	{
		this.gl = gl;
		image = img.glTexImageTGAFile(gl, "charImage/ball.tga", abilitySize);
		trigger = false;
		this.charSize = charSize;
	}
	
	public void trigger(int x, int y, String direction)
	{
		abilityPos[0] = x;
		abilityPos[1] = y;
		abDirection = direction;
		trigger = true;
		
		if (abDirection.equalsIgnoreCase("up"))
		{
			abilityPos[0] += charSize[0] / 2 - abilitySize[0] / 2;
			abilityPos[1] -= abilitySize[1];
		}
		else if (abDirection.equalsIgnoreCase("down"))
		{
			abilityPos[0] += charSize[0] / 2 - abilitySize[0] / 2;
			abilityPos[1] += charSize[1];
		}
		else if (abDirection.equalsIgnoreCase("right"))
		{
			abilityPos[0] += charSize[0];
			abilityPos[1] += charSize[1] / 2 - abilitySize[1] / 2;
		}
		else if (abDirection.equalsIgnoreCase("left"))
		{
			abilityPos[0] -= abilitySize[0];
			abilityPos[1] += charSize[1] / 2 - abilitySize[1] / 2;
		}
	}
	
	public void drawAbility(int x, int y)
	{
			img.glDrawSprite(gl, image, x, y, abilitySize[0], abilitySize[1]);
	}
	
	public int[] getPos()
	{
		return abilityPos;
	}
	
	public int[] getSize()
	{
		return abilitySize;
	}
	
	public String getDirection()
	{
		return abDirection;
	}
}
