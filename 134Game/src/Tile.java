

public class Tile {

	private int image;
	private boolean coll;
	
	public Tile(int image, boolean coll)
	{
		this.image = image;
		this.coll = coll;
	}
	
	public int getImage()
	{
		return image;
	}
	
	public boolean Coll()
	{
		return coll;
	}
 
	public boolean isDoor()
	{
		return false;
	}
	
	public String room()
	{
		return null;
	}
}
