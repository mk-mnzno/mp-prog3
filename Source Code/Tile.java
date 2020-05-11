package sample;
/**
 * The class Tile represents [..] and can [..]
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */

public abstract class Tile {

    private final String name;

    /** This constructor receives the tile's name.
     *
     *  @param  name String name of the tile
     */
    public Tile(String name) {
        this.name = name;
    }

    //methods
//	public abstract void doAction();
    /** This method returns the name of the tile.
     *
     *  @return String name of the tile
     */
    public String displayTileName (){ //For view.
        return name.replaceAll(" ", "\n");
    }

    //getters

    /** This method returns the name of the tile.
     *
     * @return name of the tile.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString ()
    {
        String strData = name;
        return strData;
    }
}
