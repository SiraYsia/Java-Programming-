package mediaRentalManager;

public class Album extends Media {
	private String artist;
	private String songs;
	
	/* 
	 * getters and setters for the Album class as well as the string representation
	 */
	
	public Album(String title, int copiesAvailable, String artist, String songs) {
		super(title, copiesAvailable);
		this.artist = artist;
		this.songs = songs;
	}

	public String getArtist() {
		return artist;

	}
	
	public void setArtist(String artist) {
		this.artist = artist;
		
	}
	
	public String getSongs() {
		return songs;
	}

	public void setSongs(String songs) {
		this.songs = songs;
	}

	public String toString() {
		return ", Artist: " + artist + ", Songs: " + songs;

	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		
		if (getClass()!=obj.getClass()) {
			return false;
		}
		Album album = (Album) obj;
		return this.artist.equals(album.artist) && this.songs.equals(album.songs);
	}

}
