package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Geese {
    private static final int gravity = -10;
    private  static final int movement = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    public Animation gooseanimation;
    private Texture texture;

    private Texture geese;

    public Geese(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("goose run background clone (1).png");
        gooseanimation = new Animation(new TextureRegion(texture), 4 ,0.4f);
        bounds= new Rectangle(x, y, 2*texture.getWidth()/12, 2*texture.getHeight()/12);

    }
    public void update(float dt){
        gooseanimation.update(dt);
        if(position.y > 0) {
            velocity.add(0, gravity, 0);
        }
        velocity.scl(dt);
        position.add(movement*dt,velocity.y,0);
        if(position.y < 0){
            position.y =0;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x , position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return gooseanimation.getFrame();
    }
    public void jump() {
        velocity.y = 250;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void dispose() {
        texture.dispose();
    }
}
