

public class FrameDef 
{
	public int image;
	public float frameTimeSecs;
	
	FrameDef(int image, float frameTimeSecs)
	{
		this.image = image ;
		this.frameTimeSecs = frameTimeSecs;
	}
	
	public int getImage()
	{
		return image;
	}
	
	public float getFrameTimeSecs()
	{
		return frameTimeSecs;
	}
}
