package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.TowerColor;

public class Tower {
    private TowerColor color;

    public Tower(){}

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
