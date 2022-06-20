package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.TowerColor;

import java.io.Serializable;

public class Tower implements Serializable {
    private TowerColor color;

    public Tower(TowerColor towerColor){
        setColor(towerColor);
    }

    public TowerColor getColor() {
        return color;
    }

    public void setColor(TowerColor color) {
        this.color = color;
    }
}
