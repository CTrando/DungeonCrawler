package com.trando.dungeoncrawler.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.trando.dungeoncrawler.State;

import java.util.HashMap;

/**
 * Created by Cameron on 3/13/2017.
 */
public class AnimationComponent implements Component {

    private HashMap<State, Animation<Sprite>> animations;

    public AnimationComponent(){
        animations = new HashMap<State, Animation<Sprite>>();
        animations.put(State.idle, new Animation<Sprite>(
                .2f,
                new Sprite(new Texture(Gdx.files.internal("player.png"))),
                           new Sprite(new Texture(Gdx.files.internal("badlogic.jpg"))),
                                      new Sprite(new Texture(Gdx.files.internal("player.png"))),
                                                 new Sprite(new Texture(Gdx.files.internal("player.png")))
        ));
    }

    public Sprite getCurrentSprite(State state, float time){
        return animations.get(state).getKeyFrame(time, true);
    }
}
