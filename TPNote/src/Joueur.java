
package fousfous;

import fousfous.Heuristique;

public class Joueur 
{
    private int color;
    private int score;

    private String name;
    private char character;

    Heuristique brain;

    public Joueur(int color, String name, char character)
    {
        this.color = color;
        this.name = name;
        this.character = character;

        this.score = 0;
    }

    public Joueur(int color, String name, char character, int score)
    {
        this.color = color;
        this.name = name;
        this.character = character; 

        this.score = score;
    }

    /**************************** Getters  ****************/

    public char getCharacter()
    {
        return this.character;
    }

    public int getColor()
    {
        return this.color;
    }

    public String getName()
    {
        return this.name;
    }

    public int getScore()
    {
        return this.score;
    }
    
    /******************** Setters *******************
    */
    public void setScore(int score)
    {
        this.score = score;
    }

    public void setBrain(Heuristique brain)
    {
        this.brain = brain;
    }

    public void incrementScore()
    {
        this.score++;
    }

    public void decrementScore()
    {
        this.score--;
    }

}