package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import sprites.Animation;
import sprites.Duck;
import sprites.Geese;
import com.mygdx.game.states.Play;

public class Play extends State {
    private static final int duckspacing = 80;
    private static final int duckcount  = 5 ;
    private static final int groundoff = -3;
    private Geese goose;
    private Texture plays;
    private Duck duck;
    private Texture jumping;
    private Array<Duck> ducks;
    private Texture ground;
    private Animation ani;
    private Vector2 groundpos1, groundpos2;
    public Play(GameStateManager gsm) {
        super(gsm);
        goose = new Geese(5,5);
        plays = new Texture("sky and background clone.png");
        jumping = new Texture("goose jump clone.png");
        cam.setToOrtho(false , MyGdxGame.Width, MyGdxGame.Height);
        ground = new Texture("New Piskel (1).png");
        groundpos1 = new Vector2(cam.position.x - (cam.viewportWidth /2) , groundoff);
        groundpos2 = new Vector2((cam.position.x - (cam.viewportWidth/2)) + ground.getWidth(), groundoff);


        ducks = new Array<Duck>();

        for(int i =1; i <= duckcount; i++) {
            ducks.add(new Duck(i*(duckspacing + Duck.duckwidth)));
        }
    }

    @Override
    protected void handleInput() {
        if(goose.getPosition().y <= ground.getHeight()+groundoff-23){
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            goose.jump();

            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateground();
        goose.update(dt);
        cam.position.x = goose.getPosition().x + 140;

        for(Duck duck : ducks) {
            if(cam.position.x - (cam.viewportWidth/2) > duck.getPosducks().x + duck.getDuck().getWidth()) {
                duck.repos(duck.getPosducks().x + ((Duck.duckwidth + duckspacing)* duckcount));
            }
            if(duck.collides(goose.getBounds())) {
                gsm.set(new Play(gsm));
                break;
            }
            System.out.println(cam.position.x);
        }

        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(plays , cam.position.x - (cam.viewportWidth/2) , cam.position.y - (cam.viewportHeight/2), MyGdxGame.Width, MyGdxGame.Height );
        if(goose.getPosition().y <= ground.getHeight()+groundoff-23){
            sb.draw(goose.getTexture(), goose.getPosition().x, goose.getPosition().y +1);}
        if(goose.getPosition().y > ground.getHeight()+groundoff-23){
            sb.draw(jumping, goose.getPosition().x, goose.getPosition().y +1);
        }
        for(Duck duck : ducks){
            sb.draw(duck.getDuck(), duck.getPosducks().x, 4);
        }
        sb.draw(ground, groundpos1.x -67, groundpos1.y);
        sb.draw(ground, groundpos2.x -67 ,groundpos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        plays.dispose();
        goose.dispose();
        ground.dispose();
        for(Duck duck : ducks) {
            duck.dispose();
        }
    }
    private void updateground(){
        if(cam.position.x - (cam.viewportWidth /2) +67 > groundpos1.x + ground.getWidth()){
                groundpos1.add(ground.getWidth()*2, 0);}
        if(cam.position.x - (cam.viewportWidth /2) +67 > groundpos2.x + ground.getWidth()){
                groundpos2.add(ground.getWidth()*2, 0);
        }
    }
}
