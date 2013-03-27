package ru.spbau.mayorov.controlwork;


public abstract class Player {
	public abstract Class<?> getGameType();
	
	public abstract String getName();
	
	public abstract Position move(Position field);
}
