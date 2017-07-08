# Music-Player

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
	static Song song1 = new Song("Apple", "2Face", 3.5);
	static Song song2 = new Song("Beans", "Davido", 3.5);
	static Song song3 = new Song("Church", "Banky", 3.5);
	static Song song4 = new Song("Desk", "Wizkid", 3.5);
	static Song song5 = new Song("Egg", "Drake", 3.5);
	static Song song6 = new Song("Fish", "Jhene", 3.5);
	static Song song7 = new Song("Goat", "Ycee", 3.5);
	static Song song8 = new Song("Horse", "Khaled", 3.5);
	static Song song9 = new Song("Indian", "Sean Paul", 3.5);
	static Song song10 = new Song("Juice", "MGK", 3.5);
	static Song song11 = new Song("Kink", "Justin Beiber", 3.5);
	static Song song12 = new Song("Lemon", "Weeknd", 3.5);
	static Song song13 = new Song("Moon", "French Montana", 3.5);
	static Song song14 = new Song("Never", "Big Sean", 3.5);
	static Song song15 = new Song("Open", "Kodak", 3.5);

	
	static Album album1 = new Album("A to C");
	static Album album2 = new Album("D to F");
	static Album album3 = new Album("G to I");
	static Album album4 = new Album("J to L");
	static Album album5 = new Album("M to O");
	
	static Album.SongLister inner1 = album1.new SongLister();
	


	static AlbumCollection myAlbums = new AlbumCollection("Tunwas Music");
	ListIterator<Song> i = myAlbums.getPlayList().listIterator();

	public static void main(String[] args) {

		album1.addSongToAlbum(song1);
		album1.addSongToAlbum(song2);
		album1.addSongToAlbum(song3);
		inner1.addSongs(song10);

		album2.addSongToAlbum(song4);
		album2.addSongToAlbum(song5);
		album2.addSongToAlbum(song6);

		album3.addSongToAlbum(song7);
		album3.addSongToAlbum(song8);
		album3.addSongToAlbum(song9);

		album4.addSongToAlbum(song10);
		album4.addSongToAlbum(song11);
		album4.addSongToAlbum(song12);

		album5.addSongToAlbum(song13);
		album5.addSongToAlbum(song14);
		album5.addSongToAlbum(song15);

		myAlbums.addAlbumToCollection(album1);
		myAlbums.addAlbumToCollection(album2);
		myAlbums.addAlbumToCollection(album3);
		myAlbums.addAlbumToCollection(album4);
		myAlbums.addAlbumToCollection(album5);

		myAlbums.addSongtoPlayList(song10);

		myAlbums.addSongtoPlayList(song1);
		myAlbums.addSongtoPlayList(song3);
		myAlbums.addSongtoPlayList(song5);
		myAlbums.addSongtoPlayList(song14);

		player(myAlbums.getPlayList());

		// myAlbums.printPlayList();

	}

	public static void player(ArrayList<Song> arrayList) {
		ListIterator<Song> i = arrayList.listIterator();

		System.out.println("1. to quit program");
		System.out.println("2. to Skip to next song");
		System.out.println("3. to Skip to previous song");
		System.out.println("4. to repeat song");

		if(arrayList.size() == 0){
			System.out.println("The playlist is empty");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		boolean goingForward = true;
		String songOnRepeat = null;
		

		boolean quit = true;
		while (quit == true) {
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				quit = false;
				break;
			case 2:

				if (i.hasNext()) {
					System.out.println("Next: " + i.next().getSongName());

					goingForward = true;

				}

				break;

			case 3:
				if (i.hasPrevious()) {

					if(goingForward == false){
						System.out.println("Repeat: " + songOnRepeat);

					} else{
						songOnRepeat = i.previous().getSongName();

						System.out.println("Repeat: " + songOnRepeat);
						goingForward = false;

					}
				}
				
				break;

			case 4:
				if (i.hasPrevious()) {
					if(goingForward == true){
						i.previous();
						System.out.println("Previous: " + i.previous().getSongName());
						goingForward = false;

					} else {
						System.out.println("Previous: " + i.previous().getSongName());

					}
				}

				break;
			case 5:
				printEntirePlaylist();
				
			case 6:
				i.remove();
				System.out.println("the current song has been removed ");
				
			}
		}
	}

	public static void printEntirePlaylist() {
		ListIterator<Song> i = myAlbums.getPlayList().listIterator();

		while (i.hasNext()) {
			System.out.println(i.next().getSongName());
		}
	}

	public static void repeatSong() {
		ListIterator<Song> i = myAlbums.getPlayList().listIterator();

		if (i.hasPrevious()) {
			System.out.println("Repeating Song" + i.previous());
		}

	}
}

import java.util.ArrayList;
import java.util.LinkedList;

public class AlbumCollection {
	ArrayList<Album> albumCollection;
	private String collectionName;
	ArrayList<Song> playList;

	public AlbumCollection(String collectionNameIn) {
		this.albumCollection = new ArrayList<Album>();
		this.collectionName = collectionNameIn;
		this.playList = new ArrayList<Song>();
	}
	
	public boolean addSongtoPlayList(Song songIn){
		boolean songInAlbumFound = findSongInAlbum(songIn);
		if(songInAlbumFound == true){
			playList.add(songIn);
			return true;
		} else {
			return false;
		}
	}
	
//	public void createNewPlayList(){
//		Scanner scanner = new Scanner(System.in);
//		String playListName = scanner.next();
//		playListName = new ArrayList<Song>();
//	}
	
	
	public boolean addAlbumToCollection(Album albumIn){
		boolean albumFound = findAlbum(albumIn);
		if(albumFound == false){
			albumCollection.add(albumIn);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean findSongInAlbum(Song songIn) {
		for(int i = 0;i<albumCollection.size();i++){
			boolean songInCollectionCheck = albumCollection.get(i).songlister.findSong(songIn);
			if(songInCollectionCheck == true){
//				System.out.println("Song was found in " +albumCollection.get(i).getAlbumName()+" album");
				return true;
			}
		}
		System.out.println("Song was not found in the your music collection");
		return false;
	}
	

	public boolean findAlbum(Album albumIn) {

		if (albumCollection.contains(albumIn)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void printPlayList(){
		for(int i = 0; i<playList.size(); i++){
			System.out.println(playList.get(i).getSongName());
		}
	}
	

	public ArrayList<Album> getAlbumCollection() {
		return albumCollection;
	}

	public void setAlbumCollection(ArrayList<Album> albumCollection) {
		this.albumCollection = albumCollection;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public ArrayList<Song> getPlayList() {
		return playList;
	}

	public void setPlayList(ArrayList<Song> playList) {
		this.playList = playList;
	}
	


}

import java.util.ArrayList;

public class Album {

	private String albumName;
//	ArrayList<Song> songList;
	SongLister songlister;

	public Album(String albumName) {
		this.albumName = albumName;
		this.songlister = new SongLister();
		
	}

	public boolean addSongToAlbum(Song songIn) {

		return this.songlister.addSongToAlbum(songIn);
	}

	
	
	public class SongLister{
		ArrayList<Song> listOfSongs ;
		public SongLister(){
			this.listOfSongs = new ArrayList<Song>();
		}
		
		public boolean addSongToAlbum(Song songIn) {

			boolean songFound = findSong(songIn);
			if (songFound == true) {
				return false;
			} else {
				if (songFound == false) {
					listOfSongs.add(songIn);
					return true;
				} else {
					return false;
				}
			}

		}
		
		public boolean findSong(Song songIn) {
			if (listOfSongs.contains(songIn)) {
				return true;
			} else {
				return false;
			}
		}
		
		public boolean findSong(String nameIn) {
			for (int i = 0; i < listOfSongs.size(); i++) {
				int compare = listOfSongs.get(i).getSongName().compareToIgnoreCase(nameIn);
				if (compare == 0) {
					System.out.println("Song is found");
					return true;
				}
			}
			System.out.println("Song doesnt exist in this album");
			return false;
		}
		
		
		
		
	}

	

	public void printSongsOnAlbum() {
		for (int i = 0; i < songlister.listOfSongs.size(); i++) {
			System.out.println(songlister.listOfSongs.get(i).toString());
		}
	}

	public String getAlbumName() {
		return albumName;
	}

//	public ArrayList<Song> getSongList() {
//		return songList;
//	}
//
//	public void setSongList(ArrayList<Song> songList) {
//		this.songlister = songList;
//	}

}



public class Song {
	private String songName;
	private double time;
	private String artist;
	
	
	public Song(String songNameIn, String artistNameIn, double timeIn) {
		this.songName = songNameIn;
		this.artist = artistNameIn;
		this.time = timeIn;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public double getTime() {
		return time;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "Name: " + songName + ", time: " + time + ", artist: " + artist;
	}
	
}
