import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBullet extends Actor
{
    private final int DEFAULT_SPEED = 5; 
    private int vecX = DEFAULT_SPEED;
    private int vecY = DEFAULT_SPEED;
    
    public EnemyBullet(){
    }
    public EnemyBullet(int vecX){
        this.vecX = vecX;
    }
    public EnemyBullet(int vecX, int vecY){
        this.vecX = vecX;
        this.vecY = vecY;
    }
    /**
     * Act - do whatever the EnemyBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
        edgeSelfDestroy();
    }
    
    public void movement(){
        setLocation(getX()+vecX, getY()+vecY);
    }
    
    private void edgeSelfDestroy(){
        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }
}
