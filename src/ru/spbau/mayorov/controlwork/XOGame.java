package ru.spbau.mayorov.controlwork;


public class XOGame extends Game {

    //private Random rnd = new Random();
    private int fieldSize;


	public XOGame(int fieldSize, int numberInRow, Player player1, Player player2) {
		super(player1, player2);

        Position pos = new Position(fieldSize);
        positions.add(pos);
        this.fieldSize = fieldSize;

        /*if (rnd.nextBoolean()) {
            Player tmp = player2;
            player2 = player1;
            player1 = tmp;
        }  */

        System.out.printf("player1 = %s\nplayer2 = %s\n", player1.toString(), player2.toString());


        while (!isGameOver()) {
            playerMove(player1, numberInRow);

            if (isGameOver()){
                break;
            }

            playerMove(player2, numberInRow);
        }
        System.out.println(winner == null ? "null" : getWinner().toString());

	}

    private boolean isGameOver() {

        if (winner != null) {
            return true;
        }

        int[][] _pos = getLastPosition().toArray();

        for(int i = 0; i < _pos.length; i++) {
            for(int j = 0; j < _pos.length; j++) {
                if (_pos[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;  //No empty cells left.
    }

    private void playerMove(Player player1, int numberInRow) {
        Position pos = getLastPosition();
        positions.add(player1.move(pos));
        if (checkWin(getLastPosition(), numberInRow))
        {
            winner = player1;
        }
    }

    private Position getLastPosition() {
        return positions.get(positions.size() - 1);
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
                if ((_pos[j][i] != 0) && (_pos[j][i] == _pos[j-1][i])) {
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

        int last = 0;
        for (int slice = 0; slice < 2 * fieldSize - 1; slice++) {
            int inLine = 1;
            int z = slice < fieldSize ? 0 : slice - fieldSize + 1;
            for (int j = z; j <= slice - z; ++j) {
                if ((_pos[j][slice - j] != 0) && (_pos[j][slice - j] == last)) {
                    inLine++;
                } else {
                    inLine = 1;
                }
                last = _pos[j][slice - j];
            }
            if (inLine >= numInLine) {
                return true;
            }
        }

        last = 0;
        for (int slice = 0; slice < 2 * fieldSize - 1; slice++) {
            int inLine = 1;
            int z = slice < fieldSize ? 0 : slice - fieldSize + 1;
            for (int j = z; j <= slice - z; ++j) {

                if ((_pos[slice - j][fieldSize - j - 1] != 0) && (_pos[slice - j][fieldSize - j - 1] == last)) {
                    inLine++;
                } else {
                    inLine = 1;
                }
                last = _pos[slice - j][fieldSize - j - 1];
            }
            if (inLine >= numInLine) {
                return true;
            }
        }

        return false;
    }
}
