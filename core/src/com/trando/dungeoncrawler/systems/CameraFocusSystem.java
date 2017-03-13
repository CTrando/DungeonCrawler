package com.trando.dungeoncrawler.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.Array;
import com.trando.dungeoncrawler.Mapper;
import com.trando.dungeoncrawler.components.*;

/**
 * Created by Cameron on 3/11/2017.
 */
public class CameraFocusSystem extends IteratingSystem {

    private OrthographicCamera camera;
    private Array<Entity> updateQueue;

    public CameraFocusSystem(OrthographicCamera camera) {
        super(Family.all(BodyComponent.class, CameraFocusComponent.class).get());
        this.camera = camera;
        this.updateQueue = new Array<Entity>();
    }

    @Override
    public void update(float delta){
        super.update(delta);
        for(Entity entity: updateQueue){
            BodyComponent bc = Mapper.bm.get(entity);
            float x = bc.getBody().getPosition().x;
            float y = bc.getBody().getPosition().y;

            camera.position.set(x,y,0);
            camera.update();
        }

        updateQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        updateQueue.add(entity);
    }
}
