

public class Camera {
	
	public static final int cameraWidth = 640;
	public static final int cameraHeight = 640;
	//private static final int zoneWidth = 400;
	//private static final int zoneHeight = 400;

	private int[] cameraPos = new int[2];
	
	public Camera (int x, int y)
	{
		cameraPos[0] = x;
		cameraPos[1] = y;
	}
	
	public void moveCamera(int x, int y)
	{
		cameraPos[0] = x;
		cameraPos[1] = y;
	}
	
	public int[] getCameraCoor()
	{
		return cameraPos;
	}
}