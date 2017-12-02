

import com.jogamp.opengl.GL2;

public class Enemy {

	int health, x, y, image;
	int[] enemySize = new int[2];
	private ImageProcess img = new ImageProcess();
	private GL2 gl;
	
	Enemy(GL2 gl)
	{
		health = 5;
		this.gl = gl;
		image = img.glTexImageTGAFile(gl, "charImage/magikarp.tga", enemySize);	
	}
	
	public void draw(int x, int y)
	{
		img.glDrawSprite(gl, image, x, y, enemySize[0], enemySize[1]);
	}
	
	public void hit()
	{
		health--;
	}
	
	public boolean isDead()
	{
		if (health <= 0)
			return true;
		else 
			return false;
	}
	
	public int[] getSize()
	{
		return enemySize;
	}
	
	public int getHealth()
	{
		return health;
	}
}
