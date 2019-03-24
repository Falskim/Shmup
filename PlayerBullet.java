import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerBullet extends Actor
{
    private final int SPEED = 10;
    
    public PlayerBullet(){
        setRotation(270);
    }
    
    public void act() 
    {
        move(SPEED);
        edgeSelfDestroy();
    }
    //Agar instance hancur saat akan mencapai border 
    private void edgeSelfDestroy(){
        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }
    
}
