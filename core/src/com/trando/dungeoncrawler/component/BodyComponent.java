package com.trando.dungeoncrawler.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.*;

import static com.trando.dungeoncrawler.DungeonCrawler.PPM;

/**
 * Created by Cameron on 3/11/2017.
 */
public class BodyComponent implements Component {

    private World world;

    //this will be created outside of the body component in the entity constructor - this way position can be set at startup
    private Body body;

    public BodyComponent(World world){
        this.world = world;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody(){
        if(body == null){
            throw new NullPointerException("Body has not been initialized");
        } else return body;
    }
}
