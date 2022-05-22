package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }
    public void push(State cool) {
        states.push(cool);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(State cool) {
        states.pop().dispose();
        states.push(cool);
    }

    public void Update(float dt) {
        states.peek().update(dt);
    }
    public void Render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
