
public class DoorTile extends Tile{

	String room;
	
	public DoorTile(int image, String room) {
		super(image, false);
		
		this.room = room;
	}
	
	public boolean isDoor()
	{
		return true;
	}
	
	public String room()
	{
		return room;
	}
}