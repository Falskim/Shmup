import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Boss here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Boss extends Actor
{
    private int STRAFE_SPEED = 3;
    private int health = 5;
    private boolean isReloading = false;
    private int reloadTimer = 0;
    private int RELOAD_DELAY = 15;
    private int xPos;
    private int yPos;
    private int patternStyle;
    
    public Boss(){
        setRotation(90);
    }
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
        bulletCollision();
        if(getWorld() == null) return;
        shooting();
    }
    
    private void movement(){
        //Gerakan Spawn
        if(getY() <= 60){
            move(1);
            return;
        }
        //Gerakan Strafing utama
        if(getX() <= 100 || getX() >= getWorld().getWidth()-100){
           STRAFE_SPEED *= -1;
        }
        setLocation(getX() + STRAFE_SPEED, getY());
    }
    
    private void bulletCollision(){
        Actor bullet = getOneIntersectingObject(PlayerBullet.class);
        if(bullet != null){
            getWorldOfType(MyWorld.class).increaseScore(25);
            getWorld().removeObject(bullet);
            health--;
            if(health == 0){
                getWorldOfType(MyWorld.class).increaseScore(250000);
                getWorld().removeObject(this);
            }    
        }
    }
    
    private void shooting(){
        if(!isReloading){
            int pattern = new Random().nextInt(3) + 1;
            if(pattern == 1){
                shootPatternBurst();
            }else if(pattern == 2){
                shootPatternSpread();
            }else if(pattern == 3){
                shootPatternFast();
            }
            isReloading = true;
        }
        reloading();
    }
    
    private void reloading(){
        if(isReloading){
            reloadTimer++;
            if(reloadTimer == RELOAD_DELAY){
                isReloading = false;
                reloadTimer = 0;
            }
        }
    }
    
    private void shootPatternSpread(){
        getWorld().addObject(new EnemyBullet(-5), getX(), getY());
        getWorld().addObject(new EnemyBullet(0), getX(), getY());
        getWorld().addObject(new EnemyBullet(5), getX(), getY());
    }
    
    private void shootPatternBurst(){
        getWorld().addObject(new EnemyBullet(0), getX()-30, getY());
        getWorld().addObject(new EnemyBullet(0), getX(), getY());
        getWorld().addObject(new EnemyBullet(0), getX()+30, getY());
    }
    
    private void shootPatternFast(){
        getWorld().addObject(new EnemyBullet(0, 10), getX(), getY()+5);
        getWorld().addObject(new EnemyBullet(0, 10), getX(), getY()+10);
        getWorld().addObject(new EnemyBullet(0, 10), getX(), getY()+15);
        getWorld().addObject(new EnemyBullet(0, 10), getX(), getY()+20);
        getWorld().addObject(new EnemyBullet(0, 10), getX(), getY()+25);
        getWorld().addObject(new EnemyBullet(0, 10), getX(), getY()+30);
    }
}
