/**
 * Created by Денис on 30.01.2017.
 */
public class GameMap {

    private final int msx = 20;
    private final int msy = 6;
    private String[][] map = new String[msy][msx];
    private int[][] mapOfDanger = new int[msy][msx];

    public boolean isCellEmpty(int _x, int _y){
        if (_x < 0 || _y < 0 || _x > msx -1 || _y > msy -1) return false;
        if (map[_y][_x] == "*") return true;
        return false;
    }

    public int getMapOfDangerCoordinates(int _y, int _x){
        return mapOfDanger[_y][_x];
    }

    public void updateMap(int _hx, int _hy){
        for (int i = 0; i < msy; i++){
            for (int j = 0; j < msx; j++){
                map[i][j] = "*";
                if(j == 4) map[i][j] = "X";
            }
        }
        map[_hy][_hx] = "H";
    }

    public void showMap(){
        for(int i = 0; i < msy; i++){
            for (int j = 0; j < msx; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void buildMapOfDanger(int _cx, int _cy){
        for (int i = 0; i < msy; i++){
            for (int j = 0; j < msx; j++){
                int dng = (int)Math.sqrt(Math.pow(_cy - i, 2) + Math.pow(_cx - j, 2));
                mapOfDanger[i][j] = dng;
            }
        }
    }





}
