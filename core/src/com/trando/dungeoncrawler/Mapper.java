package com.trando.dungeoncrawler;

import com.badlogic.ashley.core.ComponentMapper;
import com.trando.dungeoncrawler.component.*;

/**
 * Created by Cameron on 3/11/2017.
 */
public class Mapper {
    public static ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
    public static ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);
    public static ComponentMapper<CameraFocusComponent> cfm = ComponentMapper.getFor(CameraFocusComponent.class);
}
