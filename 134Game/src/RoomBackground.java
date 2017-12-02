import com.jogamp.opengl.GL2;

public class RoomBackground {

	private int width, height;
	private Tile[] tiles;
	private Tile[] grassRoom;
	private Tile[] waterRoom;
	private Tile[] fireRoom;
	private Tile[] proRoom;
	private GL2 gl;
	private int grassTile, fireTile, waterTile, roomTile, exit;
	
	private static int[] tileSize = new int[2];
	private ImageProcess img = new ImageProcess();
	
	public RoomBackground(GL2 gl) 
	{
		width = 20;
		height = 20;
		grassRoom = new Tile[height * width];
		waterRoom = new Tile[height * width];
		fireRoom = new Tile[height * width];
		proRoom = new Tile[height * width];
		this.gl = gl;
		
		grassTile = img.glTexImageTGAFile(gl, "roomImage/grassFloor.tga", tileSize); 
		fireTile = img.glTexImageTGAFile(gl, "roomImage/fireFloor.tga", tileSize); 
		waterTile = img.glTexImageTGAFile(gl, "roomImage/waterFloor.tga", tileSize); 
		roomTile = img.glTexImageTGAFile(gl, "roomImage/roomFloor.tga", tileSize); 
		exit = img.glTexImageTGAFile(gl, "roomImage/exit.tga", tileSize); 
		
		for (int i = 0; i < height * width; i++)
		{
			grassRoom[i] = new Tile(grassTile, false);
			waterRoom[i] = new Tile(waterTile, false);
			fireRoom[i] = new Tile(fireTile, false);
			proRoom[i] = new Tile(roomTile, false);
		}
		
		grassRoom[390] = new DoorTile(exit, "");
		waterRoom[390] = new DoorTile(exit, "");
		fireRoom[390] = new DoorTile(exit, "");
		proRoom[390] = new DoorTile(exit, "");
	}

	public void drawTile()
	{
		for (int i = 0; i < tiles.length; i++)
		{
			int tileW = i % width;
			int tileH = (int) Math.floor(i / height);
	
			img.glDrawSprite(gl, tiles[i].getImage(), tileW * tileSize[0], tileH * tileSize[1], tileSize[0], tileSize[1]);
		}
	}
	
	public Tile getTile(int x, int y)
	{
		 return tiles[y * width + x];
	}
	
	public int[] getWorldSize()
	{
		int[] world = new int[] {tileSize[0] * width, tileSize[1] * height};
		
		return world;
	}
	
	public int[] getTileSize()
	{
		return tileSize;
	}
	
	public void setImage(String typeTile)
	{
		if (typeTile.equalsIgnoreCase("grass"))
			tiles = grassRoom;
		else if (typeTile.equalsIgnoreCase("water"))
			tiles = waterRoom;
		else if (typeTile.equalsIgnoreCase("fire"))
			tiles = fireRoom;
		else if (typeTile.equalsIgnoreCase("pro"))
			tiles = proRoom;
	}
}
