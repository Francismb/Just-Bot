package org.agile.bot.script.interfaces;

public interface ClientListeningScript {
	public enum PAUSE_REASON {
			
	}
	public boolean onPause(PAUSE_REASON pr);
	public boolean onResume();
	public boolean onStart();
	public boolean onStop();
}