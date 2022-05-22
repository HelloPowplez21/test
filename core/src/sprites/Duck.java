package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Duck {
    public static final int duckwidth = 27;
    private static final int fluct = 100;
    private static final int fluct2 = 0;
    private static final int lowest = 70;
    private Texture duck;
    private Vector2 posducks;
    private Random rand;
    private Rectangle boundsduck;
    public Duck(float x) {
        duck = new Texture("duck clone.png");
        rand = new Random();
        posducks = new Vector2(x + rand.nextInt(( (fluct - fluct2 +1) + fluct2)-duckwidth), 0);
        boundsduck = new Rectangle(posducks.x,posducks.y, 2*duck.getWidth()/3, 2*duck.getHeight()/3);
    }

    public Texture getDuck() {
        return duck;
    }

    public Vector2 getPosducks() {
        return posducks;
    }
    public void repos(float x){
        posducks.set(x,0);
        boundsduck.setPosition(posducks.x,posducks.y);
    }
    public boolean collides(Rectangle player) {
        return player.overlaps(boundsduck);
    }
    public void dispose() {
        duck.dispose();
    }
}
