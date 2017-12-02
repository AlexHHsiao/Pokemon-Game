

import java.awt.Rectangle;

public class Box {

	private int[] BoxSize;
	private Rectangle box ;
	
	public Box(int[] pos, int[] size)
	{
		BoxSize = size;
		box = new Rectangle(pos[0], pos[1], size[0], size[1]);
	}
	
	public void updateBox(int x, int y)
	{
		box = new Rectangle(x, y, BoxSize[0], BoxSize[1]);
	}
	
	public Boolean Collision(Box b)
	{
		return this.box.intersects(b.box);
	}
	
	public Boolean Contain(Box b)
	{
		return this.box.contains(b.box);
	}
}
