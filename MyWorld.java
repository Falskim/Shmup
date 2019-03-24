    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.Random;
    
    /**
     * Write a description of class MyWorld here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
 */
public class MyWorld extends World
{
    Player player;
    EnemySpawner enemySpawner;
    Boss boss;
    
    int score = 0;
    int lives = 3;
    int PROGRESS_DELAY = 1;
    int progressTimer = 0;
    int progressStatus = 50;
    boolean isBossSpawned = false;
    boolean isEnd = false;
    
    public MyWorld()
    {    
        super(400, 600, 1); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        player = new Player();
        enemySpawner = new EnemySpawner();
        boss = new Boss();
        
        addObject(player,getWidth()/2,530);
        addObject(enemySpawner,188,33);
    }
    
    public void act(){
        displayInterface();
        increaseProgress();
        spawnBoss();
        winLose();
    }
    
    private void spawnBoss(){
        if(progressStatus == 100 && !isBossSpawned){
            removeObject(enemySpawner);
            addObject(boss, getWidth()/2, 0);
            isBossSpawned = true;
        }
    }
    
    private void displayInterface(){
        showText("Score : " + score, 50, 15);
        showText("Lives : " + lives, 350, 15);
        showText(progressStatus + "%", 200, 15);
    }
    
    private void increaseProgress(){
        if(progressStatus < 100){
            progressTimer++;
            if(progressTimer == PROGRESS_DELAY){
                progressStatus++;
                progressTimer = 0;
            }
        }    
    }
    
    public void increaseScore(int score){
       this.score += score;
    }
    
    public void decreaseLives(){
        lives--;
    }
    
    public void winLose(){
        if(lives == 0){
            removeObject(player);
        } 
    }
}
