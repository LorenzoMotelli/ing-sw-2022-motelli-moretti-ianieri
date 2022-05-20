package it.polimi.ingsw.model.enumeration;

public enum Phases {
    STARTING, //start the turn
    PLANNING, //use the assistant card
    PLACE_STUDENT, //place students in hall, on island
    PLACE_MOTHER_NATURE,//move mother nature on an island
    ENDING //end the turn
}