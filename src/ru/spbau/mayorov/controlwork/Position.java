package ru.spbau.mayorov.controlwork;



public class Position implements Cloneable {
	private int[][] field;
	
	public Position(int fieldSize) {
		field = new int[fieldSize][];
		for (int i = 0; i < fieldSize; i++) {
			field[i] = new int[fieldSize];
		}
	}

    public Position clone() {
        Position newPos = new Position(field.length);
        newPos.field = field.clone();
        return newPos;
    }
	
	public int[][] toArray() {
		return field;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder(field.length*field.length*2);
        for (int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                sb.append(field[i][j]);
            }
            sb.append(" ");
        }
        return sb.toString();
	}
}
