package ru.spbau.mayorov.controlwork;


public class XOGame extends Game {

    //private Random rnd = new Random();
    private int fieldSize;


	public XOGame(int fieldSize, int numberInRow, Player player1, Player player2) {
		super(player1, player2);

        Position pos = new Position(fieldSize);
        this.fieldSize = fieldSize;

        /*if (rnd.nextBoolean()) {
            Player tmp = player2;
            player2 = player1;
            player1 = tmp;
        }  */

        for(;;) {
            playerMove(player1, pos, numberInRow);
            playerMove(player2, pos, numberInRow);
        }

	}

    private void playerMove(Player player1, Position pos, int numberInRow) {
        positions.add(player1.move(pos));
        if (checkWin(pos, numberInRow))
        {
            winner = player1;
        }
    }

    private boolean checkWin(Position pos, int numInLine) {

      int[][] _pos = pos.toArray();

      for(int i = 0; i < fieldSize; i++) {
          int inLine = 1;
          for(int j = 1; j < fieldSize; j++) {
              if ((_pos[i][j] != 0) && (_pos[i][j] == _pos[i][j-1])) {
                  inLine++;
              }
              else {
                  inLine = 1;
              }
          }
          if (inLine >= numInLine) {
              return true;
          }
      }

        for(int i = 0; i < fieldSize; i++) {
            int inLine = 1;
            for(int j = 1; j < fieldSize; j++) {
                if ((_pos[j][i] != 0) && (_pos[j][i] == _pos[j][i-1])) {
                    inLine++;
                }
                else {
                    inLine = 1;
                }
            }
            if (inLine >= numInLine) {
                return true;
            }
        }

        for (int slice = 1; slice < 2 * fieldSize - 1; slice++) {
            int inLine = 1;
            int z = slice < fieldSize ? 0 : slice - fieldSize + 1;
            for (int j = z+1; j <= slice - z; ++j) {
                if ((_pos[j][slice - j] != 0) && (_pos[j][slice - j] == _pos[j-1][slice - j - 1])) {
                    inLine++;
                } else {
                    inLine = 1;
                }
            }
            if (inLine >= numInLine) {
                return true;
            }
        }

        for (int slice = 1; slice < 2 * fieldSize - 1; slice++) {
            int inLine = 1;
            int z = slice < fieldSize ? 0 : slice - fieldSize + 1;
            for (int j = z+1; j <= slice - z; ++j) {
                if ((_pos[slice - j][j] != 0) && (_pos[slice - j][j] == _pos[slice- j - 1][j - 1])) {
                    inLine++;
                } else {
                    inLine = 1;
                }
            }
            if (inLine >= numInLine) {
                return true;
            }
        }

        return false;
    }
}
