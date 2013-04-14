package ru.spbau.mayorov.controlwork;

import java.util.Random;

public class XOPlayer extends Player {
	public final static Class<?> GAME = XOGame.class;

    private Random rnd = new Random();
    private final int fieldSize;
    private int playerSign;

    public XOPlayer(Integer fieldSize, Integer numberInRow, Boolean isX) {
		this.fieldSize = fieldSize;
        if (isX == true) {
            this.playerSign = 1;
        } else {
            this.playerSign = 2;
        }
	}
	
	public Class<?> getGameType() {
		return GAME;
	}

    @Override
    public String getName() {
        return "Dumb XO player";
    }

    @Override
    public Position move(Position field) {
        int moveX = rnd.nextInt(fieldSize);
        int moveY = rnd.nextInt(fieldSize);

        int[][] _pos = field.toArray();

        if (_pos[moveX][moveY] != 0) {
            return move(field);
        } else
        {



            Position newField;
            newField = field.clone();
            newField.toArray()[moveX][moveY] = playerSign;

            for(int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    System.out.printf("%d ", _pos[i][j]);
                }
                System.out.println("");
            }
            System.out.println(this.toString());

            return newField;
        }
    }
}
