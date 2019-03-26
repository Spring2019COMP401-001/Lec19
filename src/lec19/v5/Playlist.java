package lec19.v5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {

	private List<Song> songs;
	private List<PlaylistObserver> observers;
	
	public Playlist() {
		songs = new ArrayList<Song>();
		observers = new ArrayList<PlaylistObserver>();
	}
	
	public int getSize() {
		return songs.size();
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[songs.size()]);
	}

	public void addSong(Song s) {
		songs.add(s);
		notifyObservers();
	}
	
	public void removeSong(Song s) {
		songs.remove(s);
		notifyObservers();
	}

	public Song getSong(int index) {
		return songs.get(index);
	}

	public void shuffle() {
		Collections.shuffle(songs);
		notifyObservers();
	}
	
	public void addObserver(PlaylistObserver o) {
		observers.add(o);
	}
	
	private void notifyObservers() {
		for (PlaylistObserver o : observers) {
			o.playlistChanged(this);
		}
	}
}
