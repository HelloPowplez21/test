package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class Menu extends State {
    private Texture background;
    private Texture playbtn;
    public Menu(GameStateManager gsm) {

        super(gsm);
        background = new Texture("background sky clone.png");
        playbtn = new Texture("play button clone.png");
        }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new Play(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth/2) , cam.position.y - (cam.viewportHeight/2), MyGdxGame.Width , MyGdxGame.Height);
        sb.draw(playbtn, (MyGdxGame.Width/2) - (playbtn.getWidth()/2), 4 );
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
    }
}
