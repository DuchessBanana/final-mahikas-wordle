public class Keyboard {
    private int[][] grid;
    String[][] keyboardLetters = new String[][]{{"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"}, {"A", "S", "D", "F", "G", "H", "J", "K", "L", " "}, {"Z", "X", "C", "V", "B", "N", "M", " ", " ", " "}};
    public Keyboard(int width, int height){
        grid = new int[width][height];
    }
    public int[][] getGrid() {
        return grid;
    }

    public void turnNo(String letter){
        Location loc = findLetterPosition(letter);
        if(isInGrid(loc.getRow(), loc.getCol())) {
            grid[loc.getRow()][loc.getCol()] = 3;
        }
    }
    public void turnMaybe(String letter){
        Location loc = findLetterPosition(letter);
        int row = loc.getRow();
        int col = loc.getCol();
        if(isInGrid(row, col)) {
            if(grid[row][col] != 1) {
                grid[row][col] = 2;
            }
        }
    }
    public void turnYes(String letter){
        Location loc = findLetterPosition(letter);
        if(isInGrid(loc.getRow(), loc.getCol())) {
            grid[loc.getRow()][loc.getCol()] = 1;
        }
    }

    public Location findLetterPosition(String letter){
        Location loc = new Location(-1,-1);;
        for(int r = 0; r < grid.length; r ++){
            for(int c = 0; c < grid[0].length; c ++){
                if(keyboardLetters[r][c].equalsIgnoreCase(letter)){
                    loc = new Location(r,c);
                }
            }
        }
        return loc;
    }

    public boolean isInGrid(int row, int col) {
        return row > -1 && row < grid.length && col > -1 && col < grid[0].length;
    }

}
