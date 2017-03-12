package com.trando.dungeoncrawler.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.trando.dungeoncrawler.*;
import com.trando.dungeoncrawler.component.*;

/**
 * Created by Cameron on 3/11/2017.
 */
public class PlayerControlledSystem extends IteratingSystem {
    private Array<Entity> updateQueue;
    private InputHandler inputHandler;

    public PlayerControlledSystem(InputHandler inputHandler) {
        super(Family.all(PlayerControlledComponent.class, BodyComponent.class, RenderComponent.class).get());
        this.inputHandler = inputHandler;
        this.updateQueue = new Array<Entity>();
    }

    @Override
    public void update(float delta){
        super.update(delta);
        for(Entity entity: updateQueue){
            BodyComponent bc = Mapper.bm.get(entity);
            System.out.println(bc.getBody().getPosition());

            if(inputHandler.isKeyPressed(Input.Keys.UP)){
                bc.getBody().setLinearVelocity(0,15f);
            }

            if(inputHandler.isKeyPressed(Input.Keys.DOWN)){
                bc.getBody().setLinearVelocity(0,-15f);
            }

            if(inputHandler.isKeyPressed(Input.Keys.RIGHT)){
                bc.getBody().setLinearVelocity(15f,0);
            }

            if(inputHandler.isKeyPressed(Input.Keys.LEFT)){
                bc.getBody().setLinearVelocity(-15f,-0);
            }
        }
        updateQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        updateQueue.add(entity);
    }
}
