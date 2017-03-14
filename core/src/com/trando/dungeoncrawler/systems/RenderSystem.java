package com.trando.dungeoncrawler.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.trando.dungeoncrawler.Mapper;
import com.trando.dungeoncrawler.components.*;

import static com.trando.dungeoncrawler.DungeonCrawler.PPM;

/**
 * Created by Cameron on 3/11/2017.
 */
public class RenderSystem extends IteratingSystem {

    private SpriteBatch batch;

    private Array<Entity> renderQueue;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(RenderComponent.class, BodyComponent.class).get());

        this.batch = batch;
        this.renderQueue = new Array<Entity>();
    }

    @Override
    public void update(float delta){
        super.update(delta);
        for(Entity entity: renderQueue){
            BodyComponent bcm = Mapper.bm.get(entity);
            RenderComponent rcm = Mapper.rm.get(entity);

            Sprite sprite = rcm.getSprite();
            float spriteWidth = sprite.getWidth();
            float spriteHeight = sprite.getHeight();

            float x = bcm.getBody().getPosition().x;
            float y = bcm.getBody().getPosition().y;

            batch.draw(sprite, x - spriteWidth/PPM/2, y - spriteHeight/PPM/2, spriteWidth/PPM/2,
                       spriteHeight/PPM/2, spriteWidth/PPM, spriteHeight/PPM,
                       1, 1, MathUtils.radiansToDegrees*bcm.getBody().getAngle());
        }
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
