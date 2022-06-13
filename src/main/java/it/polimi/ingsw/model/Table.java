package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.TowerColor;
import it.polimi.ingsw.model.enumeration.Variant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;

public class Table implements Serializable {
    private final int MAX_STUDENT = 26;
    private List<Island> islands;
    private List<Cloud> clouds;
    private List<Student> studentBag;
    private List<Professor> professors;
    //variables for the expert game
    //private int coins = 20;
    //private Character[] playableCharacters;


    /**
     * new constructor, initialize the main variables
     * @param numberOfPlayers the number of player in the game, used for the number of clouds and eventually coins
     */
    public Table(int numberOfPlayers){
        //creation of the 12 islands of the game
        islands = new ArrayList<>(12);
        for(int i = 0; i < 12; i++){
            islands.add(new Island());
        }
        //creation of the clouds based on the number of player
        clouds = new ArrayList<>(2);
        for(int i = 0; i < numberOfPlayers; i++){
            clouds.add(new Cloud());
        }
        //creation of the 130 students, 26 for each of the 5 color in the game
        studentBag = new ArrayList<>();
        //26 blue students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student blueStudent = new Student();
            blueStudent.setColor(BLUE);
            studentBag.add(blueStudent);
        }
        //26 green students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student greenStudent = new Student();
            greenStudent.setColor(GREEN);
            studentBag.add(greenStudent);
        }
        //26 pink students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student pinkStudent = new Student();
            pinkStudent.setColor(PINK);
            studentBag.add(pinkStudent);
        }
        //26 red students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student redStudent = new Student();
            redStudent.setColor(RED);
            studentBag.add(redStudent);
        }
        //26 yellow students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student yellowStudent = new Student();
            yellowStudent.setColor(YELLOW);
            studentBag.add(yellowStudent);
        }
        //creation of the 5 professors
        professors = new ArrayList<>();
        professors.add(0,new Professor(BLUE));
        professors.add(1,new Professor(GREEN));
        professors.add(2,new Professor(PINK));
        professors.add(3,new Professor(RED));
        professors.add(4,new Professor(YELLOW));
        //if it is an expert game there are characters and coins
        /*if(variantOfTheGame.equals(Variant.EXPERT)){
            setCoins(20-numberOfPlayers);
            playableCharacters = new Character[3];
            for(int i = 0; i < 3; i++){
                playableCharacters[i] = charactersOfTheGame[i];
            }
        }*/
        initializeIslands();
        initializeClouds();
    }

    /**
     * initialize each island with 2 students unless the one with mother nature and its opposite
     */
    public void initializeIslands(){
        Random random = new Random();
        int randIndex = random.nextInt(12);
        islands.get(randIndex).setMotherNature(true);
        int indexSpecularIsland;
        if(randIndex < 6){
            indexSpecularIsland = randIndex + 6;
        }
        else{
            indexSpecularIsland = randIndex - 6;
        }
        for(Island island: islands){
            if(!(island.hasMotherNature()) && !(island.equals(islands.get(indexSpecularIsland)))){
                island.addStudents(randomStudentFromBag(2));
            }
        }
    }

    /**
     * place random students from the bag to the clouds based on the number of players
     */
    public void initializeClouds(){
        //with 2 or 4 clouds each cloud has 3 students
        if(clouds.size() % 2 == 0){
            for(Cloud cloud : clouds){
                cloud.setCloudStudents(randomStudentFromBag(3));
            }
        }
        //with 3 clouds they have 4 students
        else{
            for(Cloud cloud : clouds){
                cloud.setCloudStudents(randomStudentFromBag(4));
            }
        }
    }

    /**
     * select random students from the bag and remove them from the bag
     * @param repetitions the number of students to take from the brag
     * @return the two students randomly selected
     */
    public List<Student> randomStudentFromBag(int repetitions){
        List<Student> studentsList = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < repetitions; i++){
            studentsList.add(studentBag.get(random.nextInt(studentBag.size())));
        }
        studentBag.removeAll(studentsList);
        return studentsList;
    }

    //---------------- GETTERS AND SETTERS --------------\\

    public List<Island> getIslands() {
        return islands;
    }

    public List<Cloud> getClouds() {
        return clouds;
    }

    public List<Student> getStudentBag() {
        return studentBag;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public Professor getBlueProfessor(){
        for(Professor professor : professors){
            if(professor.getColor().equals(BLUE)){
                return professor;
            }
        }
        return null;
    }

    public Professor getGreenProfessor(){
        for(Professor professor : professors){
            if(professor.getColor().equals(GREEN)){
                return professor;
            }
        }
        return null;
    }

    public Professor getPinkProfessor(){
        for(Professor professor : professors){
            if(professor.getColor().equals(PINK)){
                return professor;
            }
        }
        return null;
    }

    public Professor getRedProfessor(){
        for(Professor professor : professors){
            if(professor.getColor().equals(RED)){
                return professor;
            }
        }
        return null;
    }

    public Professor getYellowProfessor(){
        for(Professor professor : professors){
            if(professor.getColor().equals(YELLOW)){
                return professor;
            }
        }
        return null;
    }

    public Island getIslandWithMotherNature(){
        for(Island island : islands){
            if(island.hasMotherNature()){
                return island;
            }
        }
        return null;
    }

    //expert game not implemented
    /*public int getCoins() {
        return coins;
    }*/

    //expert game not implemented
    /*public void setCoins(int coins) {
        this.coins = coins;
    }*/

    //expert game not implemented
    /*public Character[] getPlayableCharacters() {
        return playableCharacters;
    }*/

    //public void useCharacterAbility(Character characterSelected){}

    //------------------- ISLANDS MANAGEMENT -----------------\\

    /**
     * place a student in the selected island
     * @param studentToPlace the student selected by the player form its school
     * @param islandSelected the island selected by the player
     */
    public void placeStudentOnIsland(Student studentToPlace, Island islandSelected){
        islandSelected.getStudents().add(studentToPlace);
    }

    /**
     * link two islands to form an archipelago
     * take all the students and towers in the two islands/archipelagos and merge them into a new island
     * @param islandTarget the first island/archipelago to be linked, this will have all the students and towers
     * @param islandOrigin the second island/archipelago to be linked, this will be removed
     */
    public void linkIslands(Island islandTarget, Island islandOrigin){
        islandTarget.addTower(islandOrigin.getTowers());
        islandTarget.addStudents(islandOrigin.getStudents());
        //add other things in expert game
    }
    /**
     * this method is used only when the is no tower in the island
     * @param island island selected
     * @param tower tower of the player
     */
    public void placeTower(Island island, Tower tower){
        island.getTowers().add(tower);
    }

    /**
     * when the conqueror of the island/archipelago changed all the towers are replaced
     * @param island the island in which there is the change of the conqueror
     * @param towerColor the new color of the tower on the island
     */
    public void replaceTower(Island island, TowerColor towerColor){
        for(Tower t: island.getTowers()){
            t.setColor(towerColor);
        }
    }

    //------------------- CLOUDS MANAGEMENT -----------------\\

    /**
     * give the students from the selected cloud
     * @param cloud the cloud that has been selected
     * @return the students placed on it
     */
    public List<Student> giveStudentsFromCloud(Cloud cloud){
        List <Student> studentToGive = new ArrayList<>(cloud.getCloudStudents());
        cloud.getCloudStudents().removeAll(studentToGive);
        return studentToGive;
    }

    /**
     * take the professor based on the color selected if the table's bag has it
     * @param color the color selected
     * @return the professor, if it's present, null otherwise
     */
    public Professor getProfessorByColor(PawnColor color) {
        switch (color){
            case BLUE -> {
                return getBlueProfessor();
            }
            case GREEN -> {
                return getGreenProfessor();
            }
            case PINK -> {
                return getPinkProfessor();
            }
            case RED -> {
                return getRedProfessor();
            }
            case YELLOW -> {
                return getYellowProfessor();
            }
        }
        return null;
    }

    //------------------- TESTS METHODS -----------------\\

    /**
     * place random students from the bag to the clouds
     * @param numberOfPlayer determines the number of students in the clouds
     */
    public void placeStudentsInCloud(int numberOfPlayer){
        for(Cloud cloud : clouds){
            List<Student> studentList;
            if(numberOfPlayer % 2 == 0){
                studentList = studentBag.stream().limit(3).collect(Collectors.toList());
            }
            else{
                studentList = studentBag.stream().limit(4).collect(Collectors.toList());
            }
            cloud.setCloudStudents(studentList);
            studentBag.removeAll(studentList);
        }
    }
}
