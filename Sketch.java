import processing.core.PApplet;

public class Sketch extends PApplet {

  // create variables
  float[] circleX = new float[25];
  float[] circleY = new float[25];
  
  int intSpeed = 2;
  float playerX = 400;
  float playerY = 375;
  
  boolean upPress = false;
  boolean downPress = false;
  boolean leftPress = false;
  boolean rightPress = false;
  
  boolean blnAlive = true;
  int intLives = 3;
  boolean mouseClicked = false;
  
  boolean[] ballHideStatus = new boolean[55];

  
  public void settings() {
	// put your size call here
    size(800, 600);
  }


  public void setup() {
  // create random snowball locations
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }
  }

  public void draw() {

    // checks to see if player is alive
    if (blnAlive == true) {
      background(0);
    
      fill(255);

      // draw snowballs to the screen if ballHideStatus is false
      for (int i = 0; i < circleY.length; i++) {
        if (ballHideStatus[i] == false) {
          ellipse(circleX[i], circleY[i], 40, 40);

          circleY[i] += intSpeed;
        }
    
        // resets snowball after the end of screen
        if (circleY[i] > height - 25) {
          circleY[i] = 0;
        }

        // if player collides with a snowball, a life is removed and snowballs stop drawing to the screen
        if (dist(playerX, playerY, circleX[i], circleY[i]) <= 37.5 && ballHideStatus[i] == false) {
          ballHideStatus[i] = true;
          intLives--;
        }
        
        // if a snowball is clicked it disappears 
        if (dist(mouseX, mouseY, circleX[i], circleY[i]) <= 25 && mouseClicked) {
            ballHideStatus[i] = true;
        }
        
      }

      fill(255, 255, 0);

      // draw player circle
      ellipse(playerX, playerY, 40, 40);
      
      // set player speed if w, a, s, or d keys are pressed.
      if (leftPress) {
        playerX += -3;
      }
      if (rightPress) {
        playerX += 3;
      }
      if (upPress) {
        playerY += -3;
      }
      if (downPress) {
        playerY += 3;
      }
      // set player color to yellow
      fill(255, 255, 0);

      // create squares in the top right corner to show the amount of lives the player has
      for (int i = 1; i <= intLives; i++) {
        rect(70 * i, 50, 50, 50);
      }

      if (intLives == 0) {
        blnAlive = false;
      }
    }

    // when all lives are lost, the screen turns to white
    else {
      background(255);
    }
  }

  
  // allow handling multiple keyboard functions to control the player ball

  public void keyPressed() {
    // move left
    if (key == 'a')  {
      leftPress = true;
    }
    // move right
    else if (key == 'd') {
      rightPress = true;
    }
    // move up
    else if (key == 'w') {
      upPress = true;
    }
    // move down
    else if (key == 's') {
      downPress = true;
    }
    
  }

  public void keyReleased() {
    if (key == 'a')  {
      leftPress = false;
    }
    else if (key == 'd') {
      rightPress = false;
    }
    else if (key == 'w') {
      upPress = false;
    }
    else if (key == 's') {
      downPress = false;
    }

  }

  // mouse functions
  public void mousePressed() {
    mouseClicked = true;
  }

  public void mouseReleased() {
    mouseClicked = false;
  }

}
