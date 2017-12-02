
import javax.swing.JOptionPane;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.opengl.*;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.opengl.GLWindow;

public class JavaGame {
    // Set this to true to make the game loop exit.
    private static boolean shouldExit;

    // The previous frame's keyboard state.
    private static boolean kbPrevState[] = new boolean[256];

    // The current frame's keyboard state.
    private static boolean kbState[] = new boolean[256];

    // Position of the sprite.
    private static int[] charPos = new int[] { 280, 280 };
    private static int[] camPos = new int[] { 0, 0 };
    private static int[] pokePos = new int[] { 300, 300 };
    private static int[] proPos = new int[] { 300, 300 };
    private static int[] conPos = new int[] { 100, 500 };
    private static int[] cgPos = new int[] { 230, 100 };
    
    private static int[] charmanderSize = new int[2];
    private static int[] ivysaurSize = new int[2];
    private static int[] squirtleSize = new int[2];
    private static int[] conSize = new int[2];
    private static int[] cgSize = new int[2];
    
    private static int[] beforeBa = new int[5];
    private static int[] afterBa = new int[2];
    private static int[] chatPro;
    
    public static void main(String[] args) {
        GLProfile gl2Profile;
       
        try {
            // Make sure we have a recent version of OpenGL
            gl2Profile = GLProfile.get(GLProfile.GL2);
        }
        catch (GLException ex) {
            System.out.println("OpenGL max supported version is too low.");
            System.exit(1);
            return;
        }

        // Create the window and OpenGL context.
        GLWindow window = GLWindow.create(new GLCapabilities(gl2Profile));
        window.setSize(640, 640);
        window.setTitle("Java Game");
        window.setVisible(true);
        window.setDefaultCloseOperation(
                WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.isAutoRepeat()) {
                    return;
                }
                kbState[keyEvent.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.isAutoRepeat()) {
                    return;
                }
                kbState[keyEvent.getKeyCode()] = false;
            }
        });

        // Setup OpenGL state.
        window.getContext().makeCurrent();
        GL2 gl = window.getGL().getGL2();
        gl.glViewport(0, 0, 640, 640);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glOrtho(0, 640, 640, 0, 0, 100);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

		// Game initialization goes here.
        BGM music = new BGM();
		AnimationData animation = new AnimationData(gl);
		ProData pro = new ProData(gl);
		Camera cam = new Camera(camPos[0], camPos[1]);
		BackgroundDef mainBackground = new BackgroundDef(gl, Camera.cameraWidth, Camera.cameraWidth);
		RoomBackground roomBackground = new RoomBackground(gl);
		ImageProcess img = new ImageProcess();
		
		
		
		Box animationBox = new Box(charPos, animation.getSpriteSize());
		Box cameraBox = new Box(camPos, new int[] {Camera.cameraWidth,  Camera.cameraWidth});
		
		boolean walk = false;
		int tileULX, tileULY, tileURX, tileURY, tileLLX, tileLLY, tileLRX, tileLRY;
		String direction = "right";
		String proDirection = "down";
		boolean inRoom = false;
		int leftBound = 0;
		int rightBound = mainBackground.getWorldSize()[0] - animation.getSpriteSize()[0];
		int upBound = 0;
		int lowBound = mainBackground.getWorldSize()[1] - animation.getSpriteSize()[1];
		int camTemX = 0, camTemY = 0, charTemX = 0, charTemY = 0;
		String room = "";
		int chatCharmander, chatIvysaur, chatSquirtle, endChat;
		boolean hasPoke = false;
		String chatBox = "";
		boolean chatBoxExist = false;
		int chatCounter = 0;
		boolean battleStart = false;
		boolean conWithPro = false;
		double distance = 0;
		int counter = 0;
		boolean gameOver = false;
		boolean haveChatWithPro = false;
		boolean drawCG = false;
		String pokemon = "";
		boolean battleCG = false;
		boolean bgm2 =true;
		
		int charmander = img.glTexImageTGAFile(gl, "pokeImage/charmander.tga", charmanderSize);
	    int ivysaur = img.glTexImageTGAFile(gl, "pokeImage/ivysaur.tga", ivysaurSize);
	    int squirtle = img.glTexImageTGAFile(gl, "pokeImage/squirtle.tga", squirtleSize);
	    
	    int charmanderCG = img.glTexImageTGAFile(gl, "pokeImage/charmanderCG.tga", cgSize);
	    int ivysaurCG = img.glTexImageTGAFile(gl, "pokeImage/ivysaurCG.tga", cgSize);
	    int squirtleCG = img.glTexImageTGAFile(gl, "pokeImage/squirtleCG.tga", cgSize);
	    int CG = img.glTexImageTGAFile(gl, "pokeImage/CG.tga", cgSize);
	    
	    for (int i = 0; i < 5; i++)
	    {
	    	beforeBa[i] = img.glTexImageTGAFile(gl, "Conversation/" + Integer.toString(i + 1)  + ".tga", conSize);
	    }
	    
	    for (int i = 0; i < 2; i++)
	    {
	    	afterBa[i] = img.glTexImageTGAFile(gl, "Conversation/ba" + Integer.toString(i + 1)  + ".tga", conSize);
	    }
	    
	    chatCharmander = img.glTexImageTGAFile(gl, "Conversation/charmander.tga", conSize);
	    chatIvysaur = img.glTexImageTGAFile(gl, "Conversation/ivysaur.tga", conSize);
	    chatSquirtle = img.glTexImageTGAFile(gl, "Conversation/squirtle.tga", conSize);
	    endChat = img.glTexImageTGAFile(gl, "Conversation/end.tga", conSize);
	    
	    Box charmanderBox = new Box(pokePos, charmanderSize);
		Box ivysaurBox = new Box(pokePos, ivysaurSize);
		Box squirtleBox = new Box(pokePos, squirtleSize);
		Box proBox = new Box(proPos, pro.getSpriteSize());

        // The game loop
        long lastFrameNS;
        long curFrameNS = System.nanoTime();

        music.playSound("game");
        
        while (!shouldExit)
        {
            System.arraycopy(kbState, 0, kbPrevState, 0, kbState.length);
            lastFrameNS = curFrameNS;
            curFrameNS = System.nanoTime();
            long deltaTimeMS = (curFrameNS - lastFrameNS) / 1000000;

            // Actually, this runs the entire OS message pump.
            window.display();
            
            if (!window.isVisible()) {
                shouldExit = true;
                break;
            }

            // Game logic goes here.    
        	
            if (kbState[KeyEvent.VK_ESCAPE]) {
                shouldExit = true;
            }
            
            if (!gameOver)
            {
	            if (!chatBoxExist)
	            {  	
	            	if (kbState[KeyEvent.VK_LEFT]) {	
		            	
	            		if (charPos[0] > leftBound) 
	            		{
		            		direction = "left";
		            		walk = true;
		            		
		            		if (!inRoom)
		            		{
		            			charPos[0] -= deltaTimeMS / 2;
		            			
		            			if (camPos[0] < 0 && (charPos[0] + camPos[0]) < 200)
		                			camPos[0] += deltaTimeMS / 2;
		            			
		            			tileULX = (int) Math.floor(charPos[0] / mainBackground.getTileSize()[0]);
			                	tileULY = (int) Math.floor(charPos[1] / mainBackground.getTileSize()[1]);
			                	tileLLX = (int) Math.floor(charPos[0] / mainBackground.getTileSize()[0]);
			                	tileLLY = (int) Math.floor((charPos[1] + animation.getSpriteSize()[1]) / mainBackground.getTileSize()[1]);
			            		
			            		if (mainBackground.getTile(tileULX, tileULY).Coll() || mainBackground.getTile(tileLLX, tileLLY).Coll())
			            			charPos[0] = (tileULX + 1) * mainBackground.getTileSize()[0] + 5;
		            		}
		            		else
		            		{
		            			distance = Math.sqrt(Math.pow((charPos[0] - proPos[0]), 2) + (Math.pow((charPos[1] - proPos[1]), 2)));
		            			charPos[0] -= deltaTimeMS / 2;
		            			animationBox.updateBox(charPos[0], charPos[1]);
		            			
		            			if (battleStart)
		            			{
			            			//dodge
			            			if(proPos[0] < leftBound)
			            				proPos[0] = leftBound;
			            			
			            			if(distance < 260 && proPos[0] > leftBound)
			            			{
			            				if(proPos[0] < charPos[0])
			            				{
			            					proPos[0] -= (int)((deltaTimeMS / 2) * 1.5);
			            					proDirection = "left";
			            				}
			            			}
			            			else if(distance < 260)
			            			{
			            				if(proPos[1] >= charPos[1]) //pro is below char
			            				{
			            					if(proPos[1]>(lowBound)*0.8)
			            					{
			            						proPos[1] -= (int)((deltaTimeMS / 2) * 3);
			            						proDirection = "up";		
			            					}
			            					else
			            					{
			            						if(proPos[1]<lowBound)
			            						{
			            							proPos[1] += (int)((deltaTimeMS / 2) * 1.5);
			            							proDirection = "down";
			            						}
			            					}
			            				}
			            				else //pro is above char
			            				{
			            					if(proPos[1] <= (lowBound)*0.2)
			            					{
			            						proPos[1] += (int)((deltaTimeMS / 2) * 3);
		            							proDirection = "down";
			            						
			            					}
			            					else
			            					{
			            						if(proPos[1]>upBound)
			            						{
			            							proPos[1] -= (int)((deltaTimeMS / 2) * 1.5);
				            						proDirection = "up";
			            						}
			            					}
			            				}
			            			}
			            			
			            			proBox.updateBox(proPos[0], proPos[1]);
		            			}
		            			
		            			if (room.equalsIgnoreCase("grass"))
		            			{
		            				if (animationBox.Collision(ivysaurBox))
			            				charPos[0] += deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("fire"))
		            			{
		            				if (animationBox.Collision(charmanderBox))
			            				charPos[0] += deltaTimeMS / 2;
		            				
		            			}
		            			else if (room.equalsIgnoreCase("water"))
		            			{
		            				if (animationBox.Collision(squirtleBox))
			            				charPos[0] += deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("pro"))
		            			{
		            				if (!battleStart)
		            				{
			            				if (animationBox.Collision(proBox))
				            				charPos[0] += deltaTimeMS / 2;
		            				}
		            			}
		            		}
		            	}
		            	else
		            		charPos[0] = leftBound;
		            }
		            else if (kbState[KeyEvent.VK_RIGHT]) { 	
		            
		            	if (charPos[0] < rightBound) 
		            	{
		            		direction = "right";
		            		walk = true;
		            		
		            		if (!inRoom)
		            		{
		            			charPos[0] += deltaTimeMS / 2;
		            			
		            			if (camPos[0] > -950 && (charPos[0] + camPos[0]) > 400)
		                            camPos[0] -= deltaTimeMS / 2;
		            			
			            		tileURX = (int) Math.floor((charPos[0] + animation.getSpriteSize()[0]) / mainBackground.getTileSize()[0]);
			                	tileURY = (int) Math.floor(charPos[1] / mainBackground.getTileSize()[1]);
			                	tileLRX = (int) Math.floor((charPos[0] + animation.getSpriteSize()[0]) / mainBackground.getTileSize()[0]);
			                	tileLRY = (int) Math.floor((charPos[1] + animation.getSpriteSize()[1]) / mainBackground.getTileSize()[1]);
			                	
			            		if (mainBackground.getTile(tileURX, tileURY).Coll() || mainBackground.getTile(tileLRX, tileLRY).Coll())
			            			charPos[0] = tileURX * mainBackground.getTileSize()[0] - 5 - animation.getSpriteSize()[0];   
		            		}
		            		else
		            		{
		            			distance = Math.sqrt(Math.pow((charPos[0] - proPos[0]), 2) + (Math.pow((charPos[1] - proPos[1]), 2)));
		            			charPos[0] += deltaTimeMS / 2;
		            			animationBox.updateBox(charPos[0], charPos[1]);
		            			
		            			if (battleStart)
		            			{
			            			//dodge 
			            			if(proPos[0] > rightBound)
			            				proPos[0] = rightBound;
		
			            			if(distance < 260 && proPos[0] < rightBound)
			            			{
			            				if(proPos[0] > charPos[0])
			            				{
			            					proPos[0] += (int)((deltaTimeMS / 2) * 1.5);
			            					proDirection = "right";
			            				}
			            			}
			            			else if(distance < 260) //pro is at the right
			            			{
			            				if(proPos[1] >= charPos[1]) //pro is below char
			            				{
			            					if(proPos[1]>(lowBound)*0.8)
			            					{
			            						proPos[1] -= (int)((deltaTimeMS / 2) * 3);
			            						proDirection = "up";	
			            					}
			            					else
			            					{
			            						if(proPos[1]<lowBound)
			            						{
			            							proPos[1] += (int)((deltaTimeMS / 2) * 1.5);
			            							proDirection = "down";
			            						}
			            					}
			            				}
			            				else //pro is above char
			            				{
			            					if(proPos[1] <= (lowBound)*0.2)
			            					{
			            						proPos[1] += (int)((deltaTimeMS / 2) * 3);
		            							proDirection = "down";	
			            					}
			            					else
			            					{
			            						if(proPos[1]>upBound)
			            						{
			            							proPos[1] -= (int)((deltaTimeMS / 2) * 1.5);
				            						proDirection = "up";
			            						}
			            					}
			            				}
			            			}
			            			 
			            			proBox.updateBox(proPos[0], proPos[1]);
		            			}
		            			
		            			if (room.equalsIgnoreCase("grass"))
		            			{
		            				if (animationBox.Collision(ivysaurBox))
		            					charPos[0] -= deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("fire"))
		            			{
		            				if (animationBox.Collision(charmanderBox))
			            				charPos[0] -= deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("water"))
		            			{
		            				if (animationBox.Collision(squirtleBox))
		            					charPos[0] -= deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("pro"))
		            			{
		            				if (!battleStart)
		            				{
			            				if (animationBox.Collision(proBox))
				            				charPos[0] -= deltaTimeMS / 2;
		            				}
		            			}
		            		}
		            	}
		            	else
		            		charPos[0] = rightBound;	
		            }
		            else if (kbState[KeyEvent.VK_UP]) {
		            	if (charPos[1] > upBound)
		            	{
		            		direction = "up";
		            		walk = true;
		            		
		            		if (!inRoom)
		            		{
		            			charPos[1] -= deltaTimeMS / 2;
		            			
		            			if (camPos[1] < 0 && (charPos[1] + camPos[1]) < 200)
		                            camPos[1] += deltaTimeMS / 2;
		            			
		            			tileULX = (int) Math.floor(charPos[0] / mainBackground.getTileSize()[0]);
			                	tileULY = (int) Math.floor(charPos[1] / mainBackground.getTileSize()[1]);
			                	tileURX = (int) Math.floor((charPos[0] + animation.getSpriteSize()[0]) / mainBackground.getTileSize()[0]);
			                	tileURY = (int) Math.floor(charPos[1] / mainBackground.getTileSize()[1]);
			            		
			            		if (mainBackground.getTile(tileULX, tileULY).Coll() || mainBackground.getTile(tileURX, tileURY).Coll())
			            			charPos[1] = (tileULY + 1) * mainBackground.getTileSize()[1] + 5;
			            		
			            		if (mainBackground.getTile(tileULX, tileULY).isDoor() || mainBackground.getTile(tileURX, tileURY).isDoor())
			            		{
			            			if (mainBackground.getTile(tileULX, tileULY).isDoor())
			            			{
			            				room= mainBackground.getTile(tileULX, tileULY).room();
			            				roomBackground.setImage(mainBackground.getTile(tileULX, tileULY).room());
			            			}
			            			else
			            			{
			            				room = mainBackground.getTile(tileURX, tileURY).room();
			            				roomBackground.setImage(mainBackground.getTile(tileURX, tileURY).room());
			            			}
			            			
			            			if (haveChatWithPro || room.equalsIgnoreCase("pro"))
			            			{
				            			inRoom = true;
				            			
				            			camTemX = camPos[0];
				            			camTemY= camPos[1];
				            			charTemX = charPos[0];
				            			charTemY = charPos[1];
				            			
				            			charPos[0] = 320;
				            			charPos[1] = 576;
				            		    camPos[0] = 0;
				            		    camPos[1] = 0;
				            		    
				            		    rightBound = roomBackground.getWorldSize()[0] - animation.getSpriteSize()[0] - 5;
				            		    lowBound = roomBackground.getWorldSize()[1] - animation.getSpriteSize()[1] - 5;
			            			}
			            		}
		            		}
		            		else
		            		{
		            			distance = Math.sqrt(Math.pow((charPos[0]-proPos[0]), 2)+(Math.pow((charPos[1]-proPos[1]), 2)));
		            			charPos[1] -= deltaTimeMS / 2;  
		            			animationBox.updateBox(charPos[0], charPos[1]);
		            			
		            			if (battleStart)
		            			{
			            			//dodge
			            			if(proPos[1] < upBound)
			            				proPos[1] = upBound;
		 
			            			if(distance < 260 && proPos[1] > upBound)
			            			{
			            				if(proPos[1] < charPos[1])
			            				{
			            					proPos[1] -= (int)((deltaTimeMS / 2) * 1.5);
			            					proDirection = "up";
			            				}
			            			}
			            			else if(distance < 260) // pro is at the top
			            			{
			            				if(proPos[0] >= charPos[0]) //pro is at right
			            				{
			            					if(charPos[0]>(rightBound)*0.8)
			            					{
			            						proPos[0] -= (int)((deltaTimeMS / 2) * 3);
			            						proDirection = "left";
			            				
			            					}
			            					else {
			            							if(proPos[0]<rightBound)
			            							{
			            								proPos[0] += (int)((deltaTimeMS / 2) * 1.5);
			            								proDirection = "right";		            					}
			            							}
			            				}

			            				else //pro is at left
			            				{
			            					if(charPos[0]<(rightBound)*0.2)
			            					{
			            						proPos[0] += (int)((deltaTimeMS / 2) * 3);
			            						proDirection = "right";	
			            					}		            					
			            					else
			            					{
			            						if(proPos[0]>leftBound)
			            						{
			            							proPos[0] -= (int)((deltaTimeMS / 2) * 1.5);
			            							proDirection = "left";
			            						}
			            					}
			            				}
			            			}
			            			
			            			proBox.updateBox(proPos[0], proPos[1]);
		            			}
	
		            			if (room.equalsIgnoreCase("grass"))
		            			{
		            				if (animationBox.Collision(ivysaurBox))
			            				charPos[1] += deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("fire"))
		            			{
		            				if (animationBox.Collision(charmanderBox))
			            				charPos[1] += deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("water"))
		            			{
		            				if (animationBox.Collision(squirtleBox))
			            				charPos[1] += deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("pro"))
		            			{
		            				if (!battleStart)
		            				{
			            				if (animationBox.Collision(proBox))
				            				charPos[1] += deltaTimeMS / 2;
		            				}
		            			}
		            		}
		            	}
		            	else
		            		charPos[1] = upBound;	
		            }
		            else if (kbState[KeyEvent.VK_DOWN]) {
		            	
		            	if (charPos[1] < lowBound)
		            	{
		            		direction = "down";
		            		walk = true;
		
		            		if (!inRoom)
		            		{
		            			charPos[1] += deltaTimeMS / 2;
		            			
		            			if (camPos[1] > -950 && (charPos[1] + camPos[1]) > 400)
		                            camPos[1] -= deltaTimeMS / 2;
		            			
			                	tileLLX = (int) Math.floor(charPos[0] / mainBackground.getTileSize()[0]);
			                	tileLLY = (int) Math.floor((charPos[1] + animation.getSpriteSize()[1]) / mainBackground.getTileSize()[1]);
			                	tileLRX = (int) Math.floor((charPos[0] + animation.getSpriteSize()[0]) / mainBackground.getTileSize()[0]);
			                	tileLRY = (int) Math.floor((charPos[1] + animation.getSpriteSize()[1]) / mainBackground.getTileSize()[1]);
			            		
			            		if (mainBackground.getTile(tileLLX, tileLLY).Coll() || mainBackground.getTile(tileLRX, tileLRY).Coll())
			            			charPos[1] = tileLLY * mainBackground.getTileSize()[0] - 5 - animation.getSpriteSize()[1];
		            		}
		            		else
		            		{	
		            			distance = Math.sqrt(Math.pow((charPos[0] - proPos[0]), 2) + (Math.pow((charPos[1] - proPos[1]), 2)));
		            			charPos[1] += deltaTimeMS / 2;
		            			animationBox.updateBox(charPos[0], charPos[1]);
		            			
		            			if (battleStart)
		            			{
			            			//dodge
			            			if(proPos[1] > lowBound)
			            				proPos[1] = lowBound;
		                
			            			if(distance < 260 && proPos[1] < lowBound)
			            			{
			            				if(proPos[1] > charPos[1])
			            				{
			            					proPos[1] += (int)((deltaTimeMS / 2) * 1.5);
			            					proDirection = "down";
			            				}
			            			}
			            			else if(distance<260) //pro reach bottom.
			            			{
			          
			            				if(proPos[0] >= charPos[0]) //pro is at right
			            				{
			            					if(charPos[0]>(rightBound)*0.8)
			            					{
			            						proPos[0] -= (int)((deltaTimeMS / 2) * 3);
			            						proDirection = "left";
			            				
			            					}
			            					else
			            					{
			            						if(proPos[0]<rightBound)
			            						{
			            							proPos[0] += (int)((deltaTimeMS / 2) * 1.5);
			            							proDirection = "right";		            					}
			            					}
			            				}
			            				else //pro is at left
			            				{
			            					if(charPos[0]<(rightBound)*0.2)
			            					{
			            						proPos[0] += (int)((deltaTimeMS / 2) * 3);
			            						proDirection = "right";	
			            					}
			            					else
			            					{
			            						if(proPos[0]>leftBound)
			            						{
			            							proPos[0] -= (int)((deltaTimeMS / 2) * 1.5);
			            							proDirection = "left";
			            						}
				            				}	
			            				}
			            			}
			            			 
			            			proBox.updateBox(proPos[0], proPos[1]);
		            			}
		            			else
		              			{
			            			tileLLX = (int) Math.floor(charPos[0] / roomBackground.getTileSize()[0]);
				                	tileLLY = (int) Math.floor((charPos[1] + animation.getSpriteSize()[1]) / roomBackground.getTileSize()[1]);
				                	tileLRX = (int) Math.floor((charPos[0] + animation.getSpriteSize()[0]) / roomBackground.getTileSize()[0]);
				                	tileLRY = (int) Math.floor((charPos[1] + animation.getSpriteSize()[1]) / roomBackground.getTileSize()[1]);
				                	
				                	if (roomBackground.getTile(tileLLX, tileLLY).isDoor() || roomBackground.getTile(tileLRX, tileLRY).isDoor())
				                	{
				                		inRoom = false;
				                		
				            			charPos[0] = charTemX;
				            			charPos[1] = charTemY;
				            		    camPos[0] = camTemX;
				            		    camPos[1] = camTemY;
				                		
				                		rightBound = mainBackground.getWorldSize()[0] - animation.getSpriteSize()[0];
				                		lowBound = mainBackground.getWorldSize()[1] - animation.getSpriteSize()[1];
				                	}
		            			}
		            			
		            			if (room.equalsIgnoreCase("grass"))
		            			{
		            				if (animationBox.Collision(ivysaurBox))
			            				charPos[1] -= deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("fire"))
		            			{
		            				if (animationBox.Collision(charmanderBox))
			            				charPos[1] -= deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("water"))
		            			{
		            				if (animationBox.Collision(squirtleBox))
			            				charPos[1] -= deltaTimeMS / 2;
		            			}
		            			else if (room.equalsIgnoreCase("pro"))
		            			{
		            				if (!battleStart)
		            				{
			            				if (animationBox.Collision(proBox))
				            				charPos[1] -= deltaTimeMS / 2;
		            				}
		            			}
		            		}
		            	}
		            	else
		            		charPos[1] = lowBound;
		            }
		            else
		            	walk = false;    
	            }
            }
            
            
            if (kbState[KeyEvent.VK_ENTER])
            {	
            	if (!battleStart)
            	{
            		if (conWithPro)
            		{      
	            		if (counter == (10 - 1))
	            			chatCounter = (chatCounter + 1) % (chatPro.length + 1);	 

	            		// update the counter
	            		counter = (counter + 1) % 10;
	            		//chatCounter++;
	            	}
	            	
	            	if (!hasPoke)
	            	{
		            	if (room.equalsIgnoreCase("grass"))
		            	{
		            		if (Math.sqrt(Math.pow(((pokePos[0] + ivysaurSize[0] / 2) - (charPos[0] + animation.getSpriteSize()[0] / 2)), 2) 
		            				+ Math.pow(((pokePos[1] + ivysaurSize[1] / 2) - (charPos[1] + animation.getSpriteSize()[1] / 2)), 2)) < 70)
		            		{
		            			chatBox = "ivysaur";
		            			chatBoxExist = true;
		            		}
		            	}
		            	else if (room.equalsIgnoreCase("fire"))
		            	{
		            		if (Math.sqrt(Math.pow(((pokePos[0] + charmanderSize[0] / 2) - (charPos[0] + animation.getSpriteSize()[0] / 2)), 2) 
		            				+ Math.pow(((pokePos[1] + charmanderSize[1] / 2) - (charPos[1] + animation.getSpriteSize()[1] / 2)), 2)) < 70)
		            		{
		            			chatBox = "charmander";
		            			chatBoxExist = true;
		            		}
		            	}
		            	else if (room.equalsIgnoreCase("water"))
		            	{
		            		if (Math.sqrt(Math.pow(((pokePos[0] + squirtleSize[0] / 2) - (charPos[0] + animation.getSpriteSize()[0] / 2)), 2) 
		            				+ Math.pow(((pokePos[1] + squirtleSize[1] / 2) - (charPos[1] + animation.getSpriteSize()[1] / 2)), 2)) < 70)
		            		{
		            			chatBox = "squirtle";
		            			chatBoxExist = true;
		            		}
		            	}
		            	else if (room.equalsIgnoreCase("pro"))
		            	{
		            		if (Math.sqrt(Math.pow(((pokePos[0] + squirtleSize[0] / 2) - (charPos[0] + animation.getSpriteSize()[0] / 2)), 2) 
		            				+ Math.pow(((pokePos[1] + squirtleSize[1] / 2) - (charPos[1] + animation.getSpriteSize()[1] / 2)), 2)) < 120)
		            		{
		            			haveChatWithPro = true;
		            			chatPro = beforeBa;
		            			chatBox = "pro";
		            			conWithPro = true;
		            			chatBoxExist = true;
		            			
		            			if (chatCounter == 3)
		            			{
		            				drawCG = true;;
		            			}
		            			else
		            				drawCG = false;
		            			
		            			if (chatCounter == 5)
		            			{
		            				conWithPro = false;
			            			chatBoxExist = false;
			            			chatCounter = 0;
			            			
			                		inRoom = false;
			                		
			                		charPos[0] = charTemX;
			                		charPos[1] = charTemY;
			                		camPos[0] = camTemX;
			                		camPos[1] = camTemY;
				                		
			                		rightBound = mainBackground.getWorldSize()[0] - animation.getSpriteSize()[0];
			                		lowBound = mainBackground.getWorldSize()[1] - animation.getSpriteSize()[1];
		            			}
		            		}
		            	}
	            	}
	            	else
	            	{
	                	if (room.equalsIgnoreCase("pro"))
	                	{
	                		if (Math.sqrt(Math.pow(((proPos[0] + pro.getSpriteSize()[0] / 2) - (charPos[0] + animation.getSpriteSize()[0] / 2)), 2) 
		            				+ Math.pow(((proPos[1] + pro.getSpriteSize()[1] / 2) - (charPos[1] + animation.getSpriteSize()[1] / 2)), 2)) < 100)
		            		{
	                			chatPro = afterBa;
		            			chatBox = "pro";
		            			conWithPro = true;
		            			chatBoxExist = true;
		            			battleCG = true;
		            			
		            			if (chatCounter == 2)
		            			{
		            				conWithPro = false;
			            			chatBoxExist = false;
			            			battleStart = true;
			            			battleCG = false;
			            			
			            			chatCounter = 0;
		            			}
		            		}
	                	}
	            	}
	            }
            	else
            	{
            		if (gameOver)
            		{
            			shouldExit = true;
            		}
            	}
            }
            
            if (chatBoxExist)
            {
	            if (kbState[KeyEvent.VK_Y])
	            {
	            	if (!conWithPro)
	            	{
		            	hasPoke = true;
		            	chatBoxExist = false;
		            	pokePos[0] = 800;
		            	pokePos[1] = 800;
		            	
		            	charmanderBox.updateBox(pokePos[0], pokePos[1]);
		            	ivysaurBox.updateBox(pokePos[0], pokePos[1]);
		            	squirtleBox.updateBox(pokePos[0], pokePos[1]);
		            	
		            	if (room.equalsIgnoreCase("fire"))
		            		pokemon = "charmander";
		            	else if (room.equalsIgnoreCase("water"))
		            		pokemon = "squirtle";
		            	else if (room.equalsIgnoreCase("grass"))
		            		pokemon = "ivysaur";
	            	}
	            }
	            else if (kbState[KeyEvent.VK_N])
	            {
	            	if (!conWithPro)
	            	{
	            		chatBoxExist = false;
	            	}
	            }
            }

            gl.glClearColor(0, 0, 0, 1);
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

            // Draw the sprite
            animationBox.updateBox(charPos[0], charPos[1]);
            cameraBox.updateBox(-1 * camPos[0], -1 *  camPos[1]);
            
            cam.moveCamera(camPos[0], camPos[1]);
            
            
            if (!inRoom)
            	mainBackground.drawTile(cam.getCameraCoor()[0], cam.getCameraCoor()[1]);
            else 
            {
            	roomBackground.drawTile();
            	
            	if (!hasPoke)
            	{
	            	if (room.equalsIgnoreCase("grass"))
	            		img.glDrawSprite(gl, ivysaur, pokePos[0], pokePos[1], ivysaurSize[0], ivysaurSize[1]);
	            	else if (room.equalsIgnoreCase("fire"))
	            		img.glDrawSprite(gl, charmander, pokePos[0], pokePos[1], charmanderSize[0], charmanderSize[1]);
	            	else if (room.equalsIgnoreCase("water"))
	            		img.glDrawSprite(gl, squirtle, pokePos[0], pokePos[1], squirtleSize[0], squirtleSize[1]);	
            	}
            	
            	if (room.equalsIgnoreCase("pro"))
            	{
            		pro.update(deltaTimeMS, proDirection, true);
                	pro.draw(proPos[0] + camPos[0] , proPos[1] + camPos[1]);  
            	}	
            }
 
            if (cameraBox.Collision(animationBox) || cameraBox.Contain(animationBox))
            {
            	animation.update(deltaTimeMS, direction, walk);
            	animation.draw(charPos[0] + camPos[0] , charPos[1] + camPos[1]);  
            }
            
            if (chatBoxExist)
            {
            	if (chatBox.equalsIgnoreCase("ivysaur"))
            	{
            		img.glDrawSprite(gl, chatIvysaur, conPos[0], conPos[1], conSize[0], conSize[1]);
            		img.glDrawSprite(gl, ivysaurCG, cgPos[0], cgPos[1], cgSize[0], cgSize[1]);
            	}
            	else if (chatBox.equalsIgnoreCase("charmander"))
            	{
            		img.glDrawSprite(gl, chatCharmander, conPos[0], conPos[1], conSize[0], conSize[1]);
            		img.glDrawSprite(gl, charmanderCG, cgPos[0], cgPos[1], cgSize[0], cgSize[1]);
            	}
            	else if (chatBox.equalsIgnoreCase("squirtle"))
            	{
            		img.glDrawSprite(gl, chatSquirtle, conPos[0], conPos[1], conSize[0], conSize[1]);
            		img.glDrawSprite(gl, squirtleCG, cgPos[0], cgPos[1], cgSize[0], cgSize[1]);
            	}
            	else if (chatBox.equalsIgnoreCase("pro"))
            	{
            		img.glDrawSprite(gl, chatPro[chatCounter], conPos[0], conPos[1], conSize[0], conSize[1]);
            	}
            	
            	if (drawCG)
            	{
            		img.glDrawSprite(gl, CG, 230, 100, cgSize[0], cgSize[1]);
            	}
            	
            	if (battleCG)
            	{
            		if (pokemon.equalsIgnoreCase("ivysaur"))
                	{
                		img.glDrawSprite(gl, ivysaurCG, 400, 300, cgSize[0], cgSize[1]);
                		img.glDrawSprite(gl, charmanderCG, 50, 150, cgSize[0], cgSize[1]);
                	}
                	else if (pokemon.equalsIgnoreCase("charmander"))
                	{
                		img.glDrawSprite(gl, charmanderCG, 400, 300, cgSize[0], cgSize[1]);
                		img.glDrawSprite(gl, squirtleCG, 50, 150, cgSize[0], cgSize[1]);
                	}
                	else if (pokemon.equalsIgnoreCase("squirtle"))
                	{
                		img.glDrawSprite(gl, squirtleCG, 400, 300, cgSize[0], cgSize[1]);
                		img.glDrawSprite(gl, ivysaurCG, 50, 150, cgSize[0], cgSize[1]);
                	}
            	}
            }
            
            if (battleStart)
            {
            	if(bgm2)
            	{
            	music.playSound2("inBattle");
            		bgm2=false;
            	}
            	if (animationBox.Collision(proBox))
            	{
            		img.glDrawSprite(gl, endChat, conPos[0], conPos[1], conSize[0], conSize[1]);
            		gameOver = true;
            	}
            }
        }
        
        if (gameOver)
        	JOptionPane.showMessageDialog(null, "Game over! if you want to continue your advanture, please buy the full version.");
    }
}