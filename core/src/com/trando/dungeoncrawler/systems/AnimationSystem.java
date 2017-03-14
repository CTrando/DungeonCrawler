package com.trando.dungeoncrawler.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.trando.dungeoncrawler.Mapper;
import com.trando.dungeoncrawler.components.*;

/**
 * Created by Cameron on 3/13/2017.
 */
public class AnimationSystem extends IteratingSystem {

    private Array<Entity> updateQueue;

    public AnimationSystem() {
        super(Family.all(StateComponent.class, RenderComponent.class, AnimationComponent.class).get());
        updateQueue = new Array<Entity>();
    }

    @Override
    public void update(float delta){
        super.update(delta);

        for(Entity entity: updateQueue){
            StateComponent sc = Mapper.sm.get(entity);
            RenderComponent rc = Mapper.rm.get(entity);
            AnimationComponent ac = Mapper.am.get(entity);

            Sprite sprite = ac.getCurrentSprite(sc.getState(), sc.getTime());
            rc.setSprite(sprite);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        updateQueue.add(entity);
        StateComponent sc = Mapper.sm.get(entity);
        sc.increment(deltaTime);
    }
}
