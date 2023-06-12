package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt {
	/*
	 * restricts the media to a default value of 2
	 */

	private int planLimitedTo = 2;

	/*
	 * Customer ArrayList represents the customers present in the database
	 * Media ArrayList represents the media (movies and albums)
	 */

	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private ArrayList<Media> mediaList = new ArrayList<Media>();

	/*
	 * Adds the specified customer to the database.  
	 */

	@Override
	public void addCustomer(String name, String address, String plan) {
		Customer customer = new Customer(name, address, plan);
		customerList.add(customer);
	}

	/*
	 * Adds the specified movie to the database.  
	 */

	@Override
	public void addMovie(String title, int copiesAvailable, String rating) {
		Media movie = new Movie(title, copiesAvailable, rating);
		mediaList.add(movie);
	}

	/*
	 * Adds the specified Album to the database.  
	 */

	@Override
	public void addAlbum(String title, int copiesAvailable, String artist, String songs) {
		Media album = new Album(title, copiesAvailable, artist, songs);
		mediaList.add(album);
	}

	/*
	 *Sets the number of media associated with the LIMITED plan
	 */

	@Override
	public void setLimitedPlanLimit(int value) {
		planLimitedTo = value;

	}

	/* 
	 *  Returns information about the customers in the database.
	 */

	@Override
	public String getAllCustomersInfo() {
		Collections.sort(customerList);
		String info = "***** Customers' Information ***** \n";
		for (Customer customer : customerList) {
			info += customer.toString() + '\n';
			info += "Rented: " + customer.getRentList() + '\n';
			info += "Queue: " + customer.getInterestedList() + '\n';
		}
		return info;
	}

	/*
	 * Returns information about all the media (movies and albums) that are part
	 * of the database.
	 */

	@Override
	public String getAllMediaInfo() {
		Collections.sort(mediaList);
		String info = "***** Media Information ***** \n";
		for (Media media : mediaList) {
			if (media.getClass() == Album.class) {
				info += "Title: " + media.getTitle() + ", Copies Available: " +
			media.getAvailableCopies()
				+ media.toString() + '\n';
			} else if (media.getClass() == Movie.class) {
				info += "Title: " + media.getTitle() + ", Copies Available: " + 
			media.getAvailableCopies()
				+ media.toString() + '\n';
			}
		}
		return info;
	}

	/*
	 * Adds the specified media title to the queue associated with a customer. 
	 */

	@Override
	public boolean addToQueue(String customerName, String mediaTitle) {
		
		
		for (Customer customer : customerList) {
			if (customerName.equals(customer.getName())) {
				if (!(customer.getInterestedList().contains(mediaTitle))) {
					customer.getInterestedList().add(mediaTitle);
					return true;
				}
			}
		}
		//customer isn't found
		return false;
	}

	/*
	 * Removes the specified media title from the customer's queue.
	 */

	@Override
	public boolean removeFromQueue(String customerName, String mediaTitle) {
		for (Customer c : customerList)
		{
			if (customerName.equals(c.getName()))
			{
				if (c.getInterestedList().contains(mediaTitle))
				{
					c.getInterestedList().remove(mediaTitle);
					return true;
				}
			}
		}
		return false;



	}
	/*
	 * Processes the requests queue of each customer.
	 */

	@Override
	public String processRequests() {

		String result = "";
		Collections.sort(customerList);
		for (Customer customorOne : customerList) {
			ArrayList<String> requestedQueues = customorOne.getInterestedList();
			ArrayList<String> rentedQue = customorOne.getRentList();




			if (customorOne.getPlan().equals("LIMITED")) {
				for (String que : requestedQueues) {
					for (Media media : mediaList) {
						if (que.equals(media.getTitle()) && rentedQue.size() 
								< planLimitedTo && media.getAvailableCopies() > 0) {
							if (!(rentedQue.contains(media.getTitle()))) {
								result += "Sending " + media.getTitle() + " to " 
							+ customorOne.getName() + '\n';
								rentedQue.add(media.getTitle());
								media.currentCopies();
							}

						}
					}

				}
			}
			if (customorOne.getPlan().equals("UNLIMITED")) {
				for (String que : requestedQueues) {
					for (Media media : mediaList) {
						if (que.equals(media.getTitle()) && media.getAvailableCopies() > 0) {
							if (!(rentedQue.contains(media.getTitle()))) {
								result += "Sending " + media.getTitle() + " to " 
							+ customorOne.getName() + '\n';
								rentedQue.add(media.getTitle());
								media.currentCopies();
							}
						}
					}
				}
			}
			for (String b : rentedQue) {
				requestedQueues.remove(b);
			}
		}

		return result;
	}

	/*
	 * This is how a customer returns a rented media. 
	 */

	@Override
	public boolean returnMedia(String customerName, String mediaTitle) {


		for (Customer customer : customerList) {
			ArrayList<String> rented = customer.getRentList();
			{
				if (customer.getName().equals(customerName)) {
					for (Media media : mediaList) {
						if (media.getTitle().equals(mediaTitle)) {
							rented.remove(mediaTitle);
							media.incrimentCopies();
							return true;
						}
					}
				}
			}
		}

		return false;
	}


	/*
	 * Returns a SORTED ArrayList with media titles that satisfy the provided parameter values.
	 */

	@Override
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {

		ArrayList<String> mediaTitles = new ArrayList<String>();
		Collections.sort(mediaList);
		
		//IF NULL
		
		if (title == null) {
			for (Media ms : mediaList) {
				
				if (ms instanceof Movie) {
					if (artist == null && songs == null) {

						if (rating == null) {
							if (!mediaTitles.contains(title)) {
								mediaTitles.add(ms.getTitle());
							}
						} else if (rating != null) {
							Movie movie = (Movie) ms;
							if (movie.getRating().equals(rating)) {
								if (!mediaTitles.contains(title)) {
									mediaTitles.add(ms.getTitle());
								}
							}
						}
					}
				}
				
				if (ms instanceof Album) {
					if (rating == null) {
						if (artist == null) {
							if (songs == null) {
								if (!mediaTitles.contains(title)) {
									mediaTitles.add(ms.getTitle());
								}
							} else if (songs != null) {
								Album ablum = (Album) ms;
								if (ablum.getSongs().contains(songs)) {
									if (!mediaTitles.contains(title)) {
										mediaTitles.add(ms.getTitle());
									}
								}
							}
						} else if (artist != null) {
							Album ablum = (Album) ms;
							if (songs == null) {
								if (ablum.getArtist().equals(artist)) {
									if (!mediaTitles.contains(title)) {
										mediaTitles.add(ms.getTitle());
									}
								}
							} else if (songs != null) {
								if (ablum.getSongs().contains(songs) && 
										ablum.getArtist().equals(artist)) {
									if (!mediaTitles.contains(title)) {
										mediaTitles.add(ms.getTitle());
									}
								}
							}
						}
					}
				}

			}
			
		} 
	
	
		
	// IF NOT NULL	
		
	if (title != null) {
			for (Media msn : mediaList) {
				
				if (msn instanceof Movie) {
					if (songs == null && artist == null) {
						if (rating == null) {
							if (!mediaTitles.contains(title)) {
								if (title.equals(msn.getTitle())) {
									mediaTitles.add(msn.getTitle());
								}
							}
						} else if (rating != null) {
							Movie movie = (Movie) msn;
							if (movie.getRating().equals(rating)) {
								if (!mediaTitles.contains(title)) {
									if (title.equals(msn.getTitle())) {
										mediaTitles.add(msn.getTitle());
									}
								}
							}
						}
					}
				}

				if (msn instanceof Album) {
					if (rating == null) {
						if (artist == null) {
							if (songs == null) {
								if (!mediaTitles.contains(title)) {
									if (title.equals(msn.getTitle())) {
										mediaTitles.add(msn.getTitle());
									}
								}
							} else if (songs != null) {
								Album ablum = (Album) msn;
								if (ablum.getSongs().contains(songs)) {
									if (!mediaTitles.contains(title)) {
										if (title.equals(msn.getTitle())) {
											mediaTitles.add(msn.getTitle());
										}
									}
								}
							}
						} else if (artist != null) {
							Album ablum = (Album) msn;
							if (songs == null) {
								if (ablum.getArtist().equals(artist)) {
									if (!mediaTitles.contains(title)) {
										if (title.equals(msn.getTitle())) {
											mediaTitles.add(msn.getTitle());
										}
									}
								}
							} else if (songs != null) {
								if (ablum.getSongs().contains(songs) && ablum.
										getArtist().equals(artist)) {
									if (!mediaTitles.contains(title)) {
										if (title.equals(msn.getTitle())) {
											mediaTitles.add(msn.getTitle());
										}
									}
								}
							}
						}
					}
				}

			}

		}
		return mediaTitles;
	}
}
