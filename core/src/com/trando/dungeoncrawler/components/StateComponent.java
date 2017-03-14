package com.trando.dungeoncrawler.components;

import com.badlogic.ashley.core.Component;
import com.trando.dungeoncrawler.State;

/**
 * Created by Cameron on 3/13/2017.
 */
public class StateComponent implements Component {

    private State state = State.idle;
    private float time = 0.0f;

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
        this.resetTime();
    }

    public float getTime(){
        return time;
    }

    public void setTime(float time){
        this.time = time;
    }

    public void resetTime(){
        this.time = 0;
    }

    public void increment(float dt){
        this.time+=dt;
    }
}
