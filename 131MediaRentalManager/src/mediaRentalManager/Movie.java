package mediaRentalManager;

public class Movie extends Media{
	private String rating;
	
	/* 
	 * getters and setters for the Movie class as well as the string representation
	 */
	
	public Movie(String title, int copiesAvailable, String rating) {
		super(title, copiesAvailable);
		this.setRating(rating);
		}


	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	public String toString()
	{
		return", Rating: " + rating;
		
	}
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		
		if (getClass()!=obj.getClass()) {
			return false;
		}
		Movie movie = (Movie) obj;
		return this.rating.equals(movie.rating);
	}

}
