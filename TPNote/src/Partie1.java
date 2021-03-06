package fousfous;


public interface Partie1 
{
	public void setFromFile(String fileName);

	public void saveToFile(String fileName);
	
	public boolean estValide(String move, String player);
	
	public String[] mouvementPossibles(String player);
	
	public void play(String move, String player);
	
	public boolean finDePartie();
	
}