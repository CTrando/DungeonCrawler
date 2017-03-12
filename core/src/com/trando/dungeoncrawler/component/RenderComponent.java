package com.trando.dungeoncrawler.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Cameron on 3/11/2017.
 */
public class RenderComponent implements Component{

    private Sprite sprite;

    public RenderComponent(Sprite sprite){
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
