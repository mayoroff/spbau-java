package ru.spbau.mayorov.controlwork;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Game implements Iterable<Position> {

    protected ArrayList<Position> positions = new ArrayList<Position>();
    protected Player winner = null;

	public Game(Player player1, Player player2) {
        if ((player1.getGameType() != this.getClass()) || (player2.getGameType() != this.getClass())) {
            throw new RuntimeException();
        }
	}

    @Override
    public Iterator<Position> iterator() {
        Iterator<Position> it = new Iterator<Position>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < positions.size();
            }

            @Override
            public Position next() {
                if (i < positions.size())  {
                    return positions.get(i++);
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    public Player getWinner() {
        return winner;
    }
}
