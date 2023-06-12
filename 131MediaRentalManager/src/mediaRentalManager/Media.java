package mediaRentalManager;

public class Media implements Comparable<Media> {
	private String title;
	private int availableCopies;

	/* 
	 * getters and setters for the Media class as well as the string representation
	 */
	
	public Media(String title, int copiesAvailable) {
		this.title = title;
		this.availableCopies = copiesAvailable;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;

	}


	public int getAvailableCopies() {
		return availableCopies;
	}
	
	public void setAvailableCopies(int copiesAvailable) {
		this.availableCopies = copiesAvailable;
	}

	public void currentCopies() {
		availableCopies--;
	}

	public void incrimentCopies() {
		availableCopies++;
	}

	

	@Override
	public int compareTo(Media o) {
		return title.compareTo(o.title);
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
		Media media = (Media) obj;
		return this.title.equals(media.title) && this.availableCopies == media.availableCopies;

	}

}
