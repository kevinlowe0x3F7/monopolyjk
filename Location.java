/** Location object for a board piece, indicating the X and Y coordinate
 *  along with which side of the board it is on.
 *  @author Kevin Lowe
 */
public class Location {
    /** X coordinate. */
    public int x;
    /** Y coordinate. */
    public int y;
    /** Orientation. */
    public String side;

    public Location(int x, int y, String side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }
}
