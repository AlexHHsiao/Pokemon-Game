

import com.jogamp.opengl.GL2;

public class BackgroundDef {
	
	private int width, height;
	private Tile[] tiles;
	private GL2 gl;
	private int tree1, tree2, tree3, tree4, floor, treeEnd1, treeEnd2, poolWater, poolUL, poolUR, poolU, poolR, poolL;
	private int lawnH, lawnV, lawnUL, lawnUR, lawnLL, lawnLR, handrailH, handrailV, grass, board;
	private int house1ROOF, house1UL, house1UR, house1UP, house1LL, house1LR, house1LOW, house1L, house1R;
	private int house3ROOF, house3UL, house3UR, house3UP, house3LL, house3LR, house3LOW, house3L, house3R;
	private int house4ROOF, house4UL, house4UR, house4UP, house4LL, house4LR, house4LOW, house4L, house4R;
	
	private int tileXCamera, tileYCamera;

	private static int[] tileSize = new int[2];
	
	private ImageProcess img = new ImageProcess();
	
	public BackgroundDef(GL2 gl, int cameraWidth, int cameraHeight)
	{
		width = 50;
		height = 50;
		tiles = new Tile[height * width];
		this.gl = gl;
		
		tree1 = img.glTexImageTGAFile(gl, "worldImage/tree1.tga", tileSize); 
		tree2 = img.glTexImageTGAFile(gl, "worldImage/tree2.tga", tileSize); 
		tree3 = img.glTexImageTGAFile(gl, "worldImage/tree3.tga", tileSize); 
		tree4 = img.glTexImageTGAFile(gl, "worldImage/tree4.tga", tileSize); 
		floor = img.glTexImageTGAFile(gl, "worldImage/floor.tga", tileSize); 
		treeEnd1 = img.glTexImageTGAFile(gl, "worldImage/treeEnd1.tga", tileSize); 
		treeEnd2 = img.glTexImageTGAFile(gl, "worldImage/treeEnd2.tga", tileSize); 
		poolWater= img.glTexImageTGAFile(gl, "worldImage/poolWater.tga", tileSize); 
		poolUL = img.glTexImageTGAFile(gl, "worldImage/poolUL.tga", tileSize); 
		poolUR = img.glTexImageTGAFile(gl, "worldImage/poolUR.tga", tileSize); 
		poolU = img.glTexImageTGAFile(gl, "worldImage/poolU.tga", tileSize); 
		poolR = img.glTexImageTGAFile(gl, "worldImage/poolR.tga", tileSize); 
		poolL = img.glTexImageTGAFile(gl, "worldImage/poolL.tga", tileSize); 
		lawnH = img.glTexImageTGAFile(gl, "worldImage/lawnH.tga", tileSize); 
		lawnV = img.glTexImageTGAFile(gl, "worldImage/lawnV.tga", tileSize); 
		lawnUL = img.glTexImageTGAFile(gl, "worldImage/lawnUL.tga", tileSize); 
		lawnUR = img.glTexImageTGAFile(gl, "worldImage/lawnUR.tga", tileSize); 
		lawnLL = img.glTexImageTGAFile(gl, "worldImage/lawnLL.tga", tileSize); 
		lawnLR = img.glTexImageTGAFile(gl, "worldImage/lawnLR.tga", tileSize); 
		handrailH = img.glTexImageTGAFile(gl, "worldImage/handrailH.tga", tileSize);
		handrailV = img.glTexImageTGAFile(gl, "worldImage/handrailV.tga", tileSize);
		grass = img.glTexImageTGAFile(gl, "worldImage/grass.tga", tileSize);
		board = img.glTexImageTGAFile(gl, "worldImage/board.tga", tileSize);
		
		house1ROOF = img.glTexImageTGAFile(gl, "house1Image/house1ROOF.tga", tileSize);
		house1UL = img.glTexImageTGAFile(gl, "house1Image/house1UL.tga", tileSize);
		house1UR = img.glTexImageTGAFile(gl, "house1Image/house1UR.tga", tileSize);
		house1UP = img.glTexImageTGAFile(gl, "house1Image/house1UP.tga", tileSize);
		house1LL = img.glTexImageTGAFile(gl, "house1Image/house1LL.tga", tileSize);
		house1LR = img.glTexImageTGAFile(gl, "house1Image/house1LR.tga", tileSize);
		house1LOW = img.glTexImageTGAFile(gl, "house1Image/house1LOW.tga", tileSize);
		house1L = img.glTexImageTGAFile(gl, "house1Image/house1L.tga", tileSize);
		house1R = img.glTexImageTGAFile(gl, "house1Image/house1R.tga", tileSize);
		
		house3ROOF = img.glTexImageTGAFile(gl, "house3Image/house3ROOF.tga", tileSize);
		house3UL = img.glTexImageTGAFile(gl, "house3Image/house3UL.tga", tileSize);
		house3UR = img.glTexImageTGAFile(gl, "house3Image/house3UR.tga", tileSize);
		house3UP = img.glTexImageTGAFile(gl, "house3Image/house3UP.tga", tileSize);
		house3LL = img.glTexImageTGAFile(gl, "house3Image/house3LL.tga", tileSize);
		house3LR = img.glTexImageTGAFile(gl, "house3Image/house3LR.tga", tileSize);
		house3LOW = img.glTexImageTGAFile(gl, "house3Image/house3LOW.tga", tileSize);
		house3L = img.glTexImageTGAFile(gl, "house3Image/house3L.tga", tileSize);
		house3R = img.glTexImageTGAFile(gl, "house3Image/house3R.tga", tileSize);
		
		house4ROOF = img.glTexImageTGAFile(gl, "house4Image/house4ROOF.tga", tileSize);
		house4UL = img.glTexImageTGAFile(gl, "house4Image/house4UL.tga", tileSize);
		house4UR = img.glTexImageTGAFile(gl, "house4Image/house4UR.tga", tileSize);
		house4UP = img.glTexImageTGAFile(gl, "house4Image/house4UP.tga", tileSize);
		house4LL = img.glTexImageTGAFile(gl, "house4Image/house4LL.tga", tileSize);
		house4LR = img.glTexImageTGAFile(gl, "house4Image/house4LR.tga", tileSize);
		house4LOW = img.glTexImageTGAFile(gl, "house4Image/house4LOW.tga", tileSize);
		house4L = img.glTexImageTGAFile(gl, "house4Image/house4L.tga", tileSize);
		house4R = img.glTexImageTGAFile(gl, "house4Image/house4R.tga", tileSize);
		
		// first four
		for (int i = 0; i < width; i++)
		{
			if ((i + 1) % 2 != 0)
				tiles[i] = new Tile(tree1, true);
			else
				tiles[i] = new Tile(tree2, true);
		}
		
		for (int i = 0; i < width; i++)
		{
			if ((i + 1) % 2 != 0)
				tiles[i + width] = new Tile(tree3, true);
			else
				tiles[i + width] = new Tile(tree4, true);
		}
		
		for (int i = 0; i < width; i++)
		{
			if (i < 4 || i > 45)
			{
				if ((i + 1) % 2 != 0)
					tiles[i + 2 * width] = new Tile(tree1, true);
				else
					tiles[i + 2 * width] = new Tile(tree2, true);
			}
			else
			{
				if ((i + 1) % 2 != 0)
					tiles[i + 2 * width] = new Tile(treeEnd1, true);
				else
					tiles[i + 2 * width] = new Tile(treeEnd2, true);
			}
		}
		
		for (int i = 0; i < width; i++)
		{
			if (i < 4 || i > 45)
			{
				if ((i + 1) % 2 != 0)
					tiles[i + 3 * width] = new Tile(tree3, true);
				else
					tiles[i + 3 * width] = new Tile(tree4, true);
			}
			else
			{
				if (i + 1 == 5)
					tiles[i + 3 * width] = new Tile(lawnUL, true);
				else if (i + 1 == 46)
					tiles[i + 3 * width] = new Tile(lawnUR, true);
				else
					tiles[i + 3 * width] = new Tile(lawnH, true);
			}
		}
		
		// main background
		for (int i = 4; i < height - 4; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if ((i + 1) % 2 == 0)
				{
					if (j < 4 || j > 45)
					{
						if ((j + 1) % 2 != 0)
							tiles[j + i * width] = new Tile(tree3, true);
						else
							tiles[j + i * width] = new Tile(tree4, true);
					}
					else
					{
						if (j + 1 == 5 || j + 1 ==46)
							tiles[j + i * width] = new Tile(lawnV, true);
						else if (j == 40 && i < 35)
							tiles[j + i * width] = new Tile(handrailV, true);
						else if (j < 40 && i == 40)
							tiles[j + i * width] = new Tile(handrailH, true);
						else if (j < 41 && i < 41)
							tiles[j + i * width] = new Tile(floor, false);
						else
							tiles[j + i * width] = new Tile(grass, false);
					}
				}
				else
				{
					if (j < 4 || j > 45)
					{
						if ((j + 1) % 2 != 0)
							tiles[j + i * width] = new Tile(tree1, true);
						else
							tiles[j + i * width] = new Tile(tree2, true);
					}
					else
					{
						if (j + 1 == 5 || j + 1 ==46)
							tiles[j + i * width] = new Tile(lawnV, true);
						else if (j == 40 && i < 35)
							tiles[j + i * width] = new Tile(handrailV, true);
						else if (j < 40 && i == 40)
							tiles[j + i * width] = new Tile(handrailH, true);
						else if (j < 41 && i < 41)
							tiles[j + i * width] = new Tile(floor, false);
						else
							tiles[j + i * width] = new Tile(grass, false);
					}
				}	
			}
		}
		
		
		// last four
		for (int i = 0; i < width; i++)
		{
			if (i < 4 || i > 45)
			{
				if ((i + 1) % 2 != 0)
					tiles[i + 46 * width] = new Tile(tree1, true);
				else
					tiles[i + 46 * width] = new Tile(tree2, true);
			}
			else
			{
				if (i + 1 == 5)
					tiles[i + 46 * width] = new Tile(lawnLL, true);
				else if (i + 1 == 46)
					tiles[i + 46 * width] = new Tile(lawnLR, true);
				else
					tiles[i + 46 * width] = new Tile(lawnH, true);
			}
		}
		
		for (int i = 0; i < width; i++)
		{
			if (i < 4 || i > 45)
			{
				if ((i + 1) % 2 != 0)
					tiles[i + 47 * width] = new Tile(tree3, true);
				else
					tiles[i + 47 * width] = new Tile(tree4, true);
			}
			else
			{
				if (i + 1 == 5)
					tiles[i + 47 * width] = new Tile(poolUL, true);
				else if (i + 1 == 46)
					tiles[i + 47 * width] = new Tile(poolUR, true);
				else
					tiles[i + 47 * width] = new Tile(poolU, true);
			}
		}
		
		for (int i = 0; i < width; i++)
		{
			if (i < 4 || i > 45)
			{
				if ((i + 1) % 2 != 0)
					tiles[i + 48 * width] = new Tile(tree1, true);
				else
					tiles[i + 48 * width] = new Tile(tree2, true);
			}
			else
			{
				if (i + 1 == 5)
					tiles[i + 48 * width] = new Tile(poolL, true);
				else if (i + 1 == 46)
					tiles[i + 48 * width] = new Tile(poolR, true);
				else
					tiles[i + 48 * width] = new Tile(poolWater, true);
			}
		}
		
		for (int i = 0; i < width; i++)
		{
			if (i < 4 || i > 45)
			{
				if ((i + 1) % 2 != 0)
					tiles[i + 49 * width] = new Tile(tree3, true);
				else
					tiles[i + 49 * width] = new Tile(tree4, true);
			}
			else
			{
				if (i + 1 == 5)
					tiles[i + 49 * width] = new Tile(poolL, true);
				else if (i + 1 == 46)
					tiles[i + 49 * width] = new Tile(poolR, true);
				else
					tiles[i + 49 * width] = new Tile(poolWater, true);
			}
		}
		
		// guideboard
		tiles[2040] = new Tile(board, true);
		
		// house1
		
		for (int i = 0; i < 5; i++)
		{
			if (i + 1 == 1)
				tiles[10 + i + 500] = new Tile(house1UL, true);
			else if (i  + 1 == 5)
				tiles[10 + i + 500] = new Tile(house1UR, true);
			else 
				tiles[10 + i + 500] = new Tile(house1UP, true);
		}
		
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (i + 1 == 1)
					tiles[10 + i + (j * width + 550)] = new Tile(house1L, true);
				else if (i + 1 == 5)
					tiles[10 + i + (j * width + 550)] = new Tile(house1R, true);
				else 
					tiles[10 + i + (j * width + 550)] = new Tile(house1ROOF, true);
			}
		}
		
		for (int i = 0; i < 5; i++)
		{
			if (i + 1 == 1)
				tiles[10 + i + 700] = new Tile(house1LL, true);
			else if (i  + 1 == 5)
				tiles[10 + i + 700] = new Tile(house1LR, true);
			else 
				tiles[10 + i + 700] = new Tile(house1LOW, true);
		}
		
		for (int i = 0; i < 10; i++)
		{
			if (i < 5)
			{
				if (i + 1 == 3 || i + 1 == 2 || i + 1 == 4)
					tiles[10 + i + 750] = new DoorTile(img.glTexImageTGAFile(gl, "house1Image/house1B" + Integer.toString(i + 1) + ".tga", tileSize), "pro");
				else
					tiles[10 + i + 750] = new Tile(img.glTexImageTGAFile(gl, "house1Image/house1B" + Integer.toString(i + 1) + ".tga", tileSize), true);
			}
			else
			{
					tiles[5 + i + 800] = new Tile(img.glTexImageTGAFile(gl, "house1Image/house1B" + Integer.toString(i + 1) + ".tga", tileSize), false);
			}	
		}
		
		// house2
		
		tiles[680] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B1.tga", tileSize), true);
		
		for (int i = 1; i <= 3; i++)
		{
			tiles[680 + i] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B2.tga", tileSize), true);
		}
		
		tiles[684] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B3.tga", tileSize), true);
		
		tiles[730] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B4.tga", tileSize), true);
		
		for (int i = 1; i <= 3; i++)
		{
			tiles[730 + i] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B5.tga", tileSize), true);
		}
		
		tiles[734] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B6.tga", tileSize), true);
		
		tiles[780] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B7.tga", tileSize), true);
		
		for (int i = 1; i <= 3; i++)
		{
			tiles[780 + i] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B8.tga", tileSize), true);
		}
		
		tiles[784] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B9.tga", tileSize), true);
		
		for (int i = 10; i < 20; i++)
		{
			if (i < 15)
			{
				if (i == 11 || i == 12 || i == 13)
					tiles[i + 820] = new DoorTile(img.glTexImageTGAFile(gl, "house2Image/house2B" + Integer.toString(i) + ".tga", tileSize), "grass");
				else
					tiles[i + 820] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B" + Integer.toString(i) + ".tga", tileSize), true);
			}
			else
			{
					tiles[i + 870 - 5] = new Tile(img.glTexImageTGAFile(gl, "house2Image/house2B" + Integer.toString(i) + ".tga", tileSize), false);
			}	
		}

		// house3

		for (int i = 0; i < 6; i++)
		{
			if (i + 1 == 1)
				tiles[10 + i + 1350] = new Tile(house3UL, true);
			else if (i  + 1 == 6)
				tiles[10 + i + 1350] = new Tile(house3UR, true);
			else 
				tiles[10 + i + 1350] = new Tile(house3UP, true);
		}
		
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				if (i + 1 == 1)
					tiles[10 + i + (j * width + 1400)] = new Tile(house3L, true);
				else if (i + 1 == 6)
					tiles[10 + i + (j * width + 1400)] = new Tile(house3R, true);
				else 
					tiles[10 + i + (j * width + 1400)] = new Tile(house3ROOF, true);
			}
		}
		
		for (int i = 0; i < 6; i++)
		{
			if (i + 1 == 1)
				tiles[10 + i + 1500] = new Tile(house3LL, true);
			else if (i  + 1 == 6)
				tiles[10 + i + 1500] = new Tile(house3LR, true);
			else 
				tiles[10 + i + 1500] = new Tile(house3LOW, true);
		}
		
		for (int i = 0; i < 12; i++)
		{
			if (i < 6)
			{
				if (i + 1 == 3 || i + 1 == 2 || i + 1 == 4)
					tiles[10 + i + 1550] = new DoorTile(img.glTexImageTGAFile(gl, "house3Image/house3B" + Integer.toString(i + 1) + ".tga", tileSize), "water");
				else
					tiles[10 + i + 1550] = new Tile(img.glTexImageTGAFile(gl, "house3Image/house3B" + Integer.toString(i + 1) + ".tga", tileSize), true);
			}
			else
			{
					tiles[4 + i + 1600] = new Tile(img.glTexImageTGAFile(gl, "house3Image/house3B" + Integer.toString(i + 1) + ".tga", tileSize), false);
			}	
		}
		
		// house4
		
		for (int i = 0; i < 8; i++)
		{
			if (i + 1 == 1)
				tiles[28 + i + 1350] = new Tile(house4UL, true);
			else if (i  + 1 == 8)
				tiles[28 + i + 1350] = new Tile(house4UR, true);
			else 
				tiles[28 + i + 1350] = new Tile(house4UP, true);
		}
		
		for (int i = 0; i < 8; i++)
		{
				if (i + 1 == 1)
					tiles[28 + i + 1400] = new Tile(house4L, true);
				else if (i + 1 == 8)
					tiles[28 + i + 1400] = new Tile(house4R, true);
				else 
					tiles[28 + i + 1400] = new Tile(house4ROOF, true);
		}
		
		for (int i = 0; i < 8; i++)
		{
			if (i + 1 == 1)
				tiles[28 + i + 1450] = new Tile(house4LL, true);
			else if (i  + 1 == 8)
				tiles[28 + i + 1450] = new Tile(house4LR, true);
			else 
				tiles[28 + i + 1450] = new Tile(house4LOW, true);
		}
		
		for (int i = 0; i < 16; i++)
		{
			if (i < 8)
			{
				if (i + 1 == 3 || i + 1 == 5 || i + 1 == 4)
					tiles[28 + i + 1500] = new DoorTile(img.glTexImageTGAFile(gl, "house4Image/house4B" + Integer.toString(i + 1) + ".tga", tileSize), "fire");
				else
					tiles[28 + i + 1500] = new Tile(img.glTexImageTGAFile(gl, "house4Image/house4B" + Integer.toString(i + 1) + ".tga", tileSize), true);
			}
			else
			{
					tiles[20 + i + 1550] = new Tile(img.glTexImageTGAFile(gl, "house4Image/house4B" + Integer.toString(i + 1) + ".tga", tileSize), false);
			}	
		}
		
		// ==================================================
		
		tileXCamera = cameraWidth / tileSize[0] + 2;
		tileYCamera = cameraHeight / tileSize[1] + 2;
	}
	
	public void drawTile(int cameraX, int cameraY)
	{
		int tileX = -1 * (int) Math.floor(cameraX / tileSize[0]);
		int tileY = -1 * (int) Math.floor(cameraY / tileSize[1]);
		
		for (int i = 0; i < tileXCamera; i++)
		{
			for (int j = 0; j < tileYCamera; j++)
			{
				if (tileX + i < width && tileY + j < height)
					img.glDrawSprite(gl, getTile(tileX + i, tileY + j).getImage(), tileSize[0] * (tileX + i) + cameraX, tileSize[1] * (tileY + j) + cameraY, tileSize[0], tileSize[1]);
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		 return tiles[y * width + x];
	}
	
	public int[] getTileSize()
	{
		return tileSize;
	}
	
	public int[] getWorldSize()
	{
		int[] world = new int[] {tileSize[0] * width, tileSize[1] * height};
		
		return world;
	}
}
