import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int RELOAD_DELAY = 10;
    private boolean isReloading = false;
    private int reloadTimer = 0;
    
    public Player(){
        setRotation(270);
    }
    
    public void act(){
        movement();
        shooting();
        enemyCollision();
        bulletCollision();
    }

    private void movement(){
        //Player movement
        if(Greenfoot.isKeyDown("A")){
            this.setLocation(getX()-5, getY());
        }
        if(Greenfoot.isKeyDown("D")){
            this.setLocation(getX()+5, getY());
        }
    }
    
    private void shooting(){
        //Player shooting
        if(Greenfoot.isKeyDown("SPACE") && !isReloading){
            getWorld().addObject(new PlayerBullet(), getX(), getY()-60);
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
    
    private void enemyCollision(){
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if(enemy != null){
            getWorld().removeObject(enemy);
            respawn();
        }
    }
    
    private void bulletCollision(){
        Actor bullet = getOneIntersectingObject(EnemyBullet.class);
        if(bullet != null){
            getWorld().removeObject(bullet);
            respawn();
        }
    }
    private void respawn(){
        ((MyWorld)getWorld()).decreaseLives();
        getWorld().removeObjects(getWorld().getObjects(Enemy.class));
        getWorld().removeObjects(getWorld().getObjects(EnemyBullet.class));
        setLocation(getWorld().getWidth()/2, 530);
    }
    
}
