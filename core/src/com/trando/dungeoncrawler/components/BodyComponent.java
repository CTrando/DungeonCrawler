package com.trando.dungeoncrawler.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Cameron on 3/11/2017.
 */
public class BodyComponent implements Component {

    private World world;

    //this will be created outside of the body components in the entity constructor - this way position can be set at startup
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
