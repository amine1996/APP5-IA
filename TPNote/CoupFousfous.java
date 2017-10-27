package fousfous;

public class CoupFousfous
{
    public String move;

    public int startColumn;
    public int startRow;

    public int endColumn;
    public int endRow;

    public boolean converted;

    public String player;

    public CoupFousfous(String move)
    {
        this.move = move;
        this.converted = false;
        this.player = "";

        moveToInt();
    }

    public CoupFousfous(int startColumn, int startRow, int endColumn, int endRow)
    {
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.endColumn = endColumn;
        this.endRow = endRow;
        this.player = "";
        this.converted = false;

        moveToString();
    }

    public void setPlayer(String player)
    {
        this.player = player;
    }
    
    public void moveToString()
    {
        String res = "";

        res += Character.toString((char)(((int) 'A') + this.startColumn));
        res += String.valueOf(this.startRow + 1);
        res += "-";
        res += Character.toString((char)(((int) 'A') + this.endColumn));
        res += String.valueOf(this.endRow + 1);

        this.converted = true;
        this.move = res;
    }


    public void moveToInt()
    {

		String[] positions = this.move.split("-");

        if(positions.length == 2)
        {
            String startPos = positions[0];
            String endPos = positions[1];

            if(startPos.length() == 2 && endPos.length() == 2)
            {    
                this.startColumn = (int) startPos.charAt(0) - ((int) 'A');
                this.startRow = Character.getNumericValue(startPos.charAt(1)) - 1;

                this.endColumn = (int) endPos.charAt(0) - ((int) 'A');
                this.endRow = Character.getNumericValue(endPos.charAt(1)) - 1;

                this.converted = true;
            }
        }

        if(!this.converted)
        {
            System.out.println("Could not parse move, format should be : A1-B2. String given : "+this.move);
        }
    }

    public String toString()
    {
        return this.move;
    }
}