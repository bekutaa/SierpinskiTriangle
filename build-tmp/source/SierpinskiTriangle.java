import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SierpinskiTriangle extends PApplet {

public float triSize = 150;
public float maxSize = 600;
public float minSize = 20;
public float sizeChange = 5;

public float oldX = 300;
public float oldY = 300;

public boolean animateExpand = false;
public boolean expanding = false;
public boolean followMouse = false;

public void setup()
{
  size(600,600);
  background(255);
}
public void draw()
{
  background(255);
//  noStroke();
//  fill(255,255,255,100);
//  rect(-50,-50,width+100,height+100);
  
  if(animateExpand)
  {
    if(expanding)
    {
      triSize+=sizeChange;
    }
    else
    {
      if(triSize > minSize)
      {
        triSize-=sizeChange;
      }
    }
    if(triSize >= maxSize || triSize <= minSize)
    {
      if(triSize > maxSize) { triSize-=sizeChange; }
      if(triSize < minSize) { triSize+=sizeChange; }
      expanding = !expanding;
    }
  }
  
  if(followMouse)
  {
    sierpinski(mouseX-triSize/2,mouseY + triSize/3, triSize);
  }
  else
  {
    sierpinski(oldX-triSize/2,oldY + triSize/3, triSize);
  }
}

public void keyPressed()
{
  if(key == ' ')
  {
    animateExpand = !animateExpand;
  }
  if(key == 'z')
  {
    if(followMouse)
    {
      oldX = mouseX;
      oldY = mouseY;
    }
    followMouse = !followMouse;
  }
}

public void mouseDragged()
{
  if(followMouse)
  {
    if(pmouseX < mouseX)
    {
      triSize+=sizeChange;
    }
    if(pmouseX > mouseX)
    {
      if(triSize > minSize)
      {
        triSize-=sizeChange;
      }
    }
  }
}

public void sierpinski(float x, float y, float len) 
{
  if(len > 50)
  {
    sierpinski(x,y,len/2);
    sierpinski(x+(len/2),y,len/2);
    sierpinski(x+(len/4),y-((len/4)*sqrt(3)),len/2);
  }
  else
  {
    noStroke();
    
    fill(210,140,255);
    
    triangle(x,y,x+(len/2),y-((len/2)*sqrt(3)),x+len,y);
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SierpinskiTriangle" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
