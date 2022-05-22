package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    public Array<TextureRegion> frames;
    private float maxFrametime;
    private float currentFrametime;
    public int framecount;
    private int frame;
    private Geese goose;
    private Texture jump;

    public Animation(TextureRegion region, int framecount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int framewidth = region.getRegionWidth()/framecount;
        for(int i =0; i <framecount; i++) {
            frames.add(new TextureRegion(region, i *framewidth, 0, framewidth, region.getRegionHeight()));
        }
        jump = new Texture("goose jump clone.png");
        this.framecount = framecount;
        maxFrametime = cycleTime/ framecount;
        frame = 0;
    }
    public void update(float dt) {
        currentFrametime += dt;
        if(currentFrametime > maxFrametime) {
            frame ++;
            currentFrametime = 0;
        }
        if(frame == framecount){
            frame=0;
        }
        }
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
