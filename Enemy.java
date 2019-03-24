import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int speed;
    
    public Enemy(){
        setRotation(90);
        speed = new Random().nextInt(5) + 3;
    }
    
    public void act() 
    {
        move(speed);
        checkCollision();
    }
    
    private void checkCollision(){
        edgeCollision();
        if(getWorld() == null) return; //Menghindari Null Actor Error
        bulletCollision();
        if(getWorld() == null) return;
    }
    
    //Agar instance hancur saat akan mencapai border 
    private void edgeCollision(){
        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }
    
    private void bulletCollision(){
        Actor bullet = getOneIntersectingObject(PlayerBullet.class);
        if(bullet != null){
            getWorldOfType(MyWorld.class).increaseScore(5);
            getWorld().removeObject(bullet);
            getWorld().removeObject(this);
        }
    }
}
