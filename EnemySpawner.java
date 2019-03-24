import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class EnemySpawner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemySpawner extends Actor
{
    /**
     * Act - do whatever the EnemySpawner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int SPAWN_DELAY = 20;
    private int spawnTimer = 0;
    private boolean isSpawning = false;
    
    public void act(){
        spawnRandom();
        spawnTimer();
    }
    
    private void spawnTimer(){
        if(isSpawning){
            spawnTimer++;
            if(spawnTimer == SPAWN_DELAY){
                isSpawning = false;
                spawnTimer = 0;
            }
        }
    }
    
    private void spawnRandom(){
        if(!isSpawning){
            int xPosition = new Random().nextInt(getWorld().getWidth());
            getWorld().addObject(new Enemy(), xPosition, 0);
            isSpawning = true;
        }
    }    
}
