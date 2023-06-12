import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * <P>
 * The HeavyBag class implements a Set-like collection that allows duplicates (a
 * lot of them).
 * </P>
 * <P>
 * The HeavyBag class provides Bag semantics: it represents a collection with
 * duplicates. The "Heavy" part of the class name comes from the fact that the
 * class needs to efficiently handle the case where the bag contains 100,000,000
 * copies of a particular item (e.g., don't store 100,000,000 references to the
 * item).
 * </P>
 * <P>
 * In a Bag, removing an item removes a single instance of the item. For
 * example, a Bag b could contain additional instances of the String "a" even
 * after calling b.remove("a").
 * </P>
 * <P>
 * The iterator for a heavy bag must iterate over all instances, including
 * duplicates. In other words, if a bag contains 5 instances of the String "a",
 * an iterator will generate the String "a" 5 times.
 * </P>
 * <P>
 * In addition to the methods defined in the Collection interface, the HeavyBag
 * class supports several additional methods: uniqueElements, getCount, and
 * choose.
 * </P>
 * <P>
 * The class extends AbstractCollection in order to get implementations of
 * addAll, removeAll, retainAll and containsAll.  (We will not be over-riding those).
 * All other methods defined in
 * the Collection interface will be implemented here.
 * </P>
 */
public class HeavyBag<T> extends AbstractCollection<T> implements Serializable {

    /* Leave this here!  (We need it for our testing) */
	private static final long serialVersionUID = 1L;

	
	/* Create whatever instance variables you think are good choices */
	
	int size = 0;
	Map<T, Integer> bag; //key and value	
	
	
	
	/**
     * Initialize a new, empty HeavyBag
     */
    public HeavyBag() {
    	bag = new HashMap<>();
    }

    /**
     * Adds an instance of o to the Bag
     * 
     * @return always returns true, since added an element to a bag always
     *         changes it
     * 
     */
    @Override
    public boolean add(T o) {
    	//if there is an instance of o, increment and update
    	if(bag.containsKey(o)) {
    		int value = bag.get(o) + 1;
    		bag.put(o, value);
    	}else {
    		bag.put(o, 1);
    		
    	}
    	size++;
    	return true;
    	
    	
    }

    /**
     * Adds multiple instances of o to the Bag.  If count is 
     * less than 0 or count is greater than 1 billion, throws
     * an IllegalArgumentException.
     * 
     * @param o the element to add
     * @param count the number of instances of o to add
     * @return true, since addMany always modifies
     * the HeavyBag.
     */
    public boolean addMany(T o, int count) {
    	if(count < 0 || count > 1000000000) {
    		throw new IllegalArgumentException();
    	}else {
    		if(bag.containsKey(o)) {
    			int value = bag.get(o);
    			bag.put(o, value + count);
    		}else {
    			bag.put(o, count);
    		}
    	}
    	size+=count;
    	return true;
    	
    }
    
    /**
     * Generate a String representation of the HeavyBag. This will be useful for
     * your own debugging purposes, but will not be tested other than to ensure that
     * it does return a String and that two different HeavyBags return two
     * different Strings.
     */
    @Override
    public String toString() {
    	return "Heavy Bag contains: " + bag.keySet() + " ";
    }

    /**
     * Tests if two HeavyBags are equal. Two HeavyBags are considered equal if they
     * contain the same number of copies of the same elements.
     * Comparing a HeavyBag to an instance of
     * any other class should return false;
     */
    @Override
    public boolean equals(Object o) {
    	boolean valid = true;
    	if(!(o instanceof HeavyBag)) {
    		return false;
    	}
    	HeavyBag<?> newBag = (HeavyBag<?>) o;
    	if(!(newBag.bag.keySet().equals(bag.keySet()))){
        	valid = false;
        	return valid;

    	}
    	for(T object: bag.keySet()) {
    		if(!(newBag.bag.get(object) == bag.get(object))) {
    			valid = false;
    		}
    	}
    	return valid;
    }
    

    /**
     * Return a hashCode that fulfills the requirements for hashCode (such as
     * any two equal HeavyBags must have the same hashCode) as well as desired
     * properties (two unequal HeavyBags will generally, but not always, have
     * unequal hashCodes).
     */
    @Override
    public int hashCode() {
    	return bag.hashCode();
    }

    /**
     * <P>
     * Returns an iterator over the elements in a heavy bag. Note that if a
     * Heavy bag contains 3 a's, then the iterator must iterate over 3 a's
     * individually.
     * </P>
     */
    @Override
    public Iterator<T> iterator() {
    	return new Iterator<T>() {
    	int curr = 1;//the index in heavy bag
    	int remaining = 0; //number of the element remaining 
    	T next = null;
    	int obj = 0;
    	Iterator<T> iterator = bag.keySet().iterator();//iterates through the key set
    	
    	@Override
    	public boolean hasNext() {
    		return curr < size();
    	}
    	@Override
    	public T next() {
    		if(remaining > 0) {
    			remaining--;
    		}else {
    			next = iterator.next();
    			remaining = bag.get(next);
    		
    	}
    	curr++;
    	return next;
    	}
    	@Override
    	public void remove() {
    		//if there is only one instance of the object remove it 
    		if(bag.get(next) == 1 || remaining ==1) {
    			bag.remove(next);
    			size--;
    		}else {
    			remaining--;//decremant remaining objects
    			obj = bag.get(next) -1;
    			bag.put(next, obj);
    		}
    	}
    	};

    }

    /**
     * return a Set of the elements in the Bag (since the returned value is a
     * set, it can contain no duplicates. It will contain one value for each 
     * UNIQUE value in the Bag).
     * 
     * @return A set of elements in the Bag
     */
    public Set<T> uniqueElements() {
    	return bag.keySet();
    	
    }

    /**
     * Return the number of instances of a particular object in the bag. Return
     * 0 if it doesn't exist at all.
     * 
     * @param o
     *            object of interest
     * @return number of times that object occurs in the Bag
     */
    public int getCount(Object o) {
    	if(!bag.containsKey(o)) {
    		return 0;
    	}
    	return bag.get(o);
    }

    /**
     * Given a random number generator, randomly choose an element from the Bag
     * according to the distribution of objects in the Bag (e.g., if a Bag
     * contains 7 a's and 3 b's, then 70% of the time choose should return an a,
     * and 30% of the time it should return a b.
     * 
     * This operation can take time proportional to the number of unique objects
     * in the Bag, but no more.
     * 
     * This operation should not affect the Bag.
     * 
     * @param r
     *            Random number generator
     * @return randomly chosen element
     */
    public T choose(Random r) {
    	int randIndex = r.nextInt(size());
    	T val = null;//represents the chose object
    	int count = 0;
    	
    	for(T object : bag.keySet()) {
    		count += bag.get(object);
			// if the values inside the bag are greater than the index, set the value to be the chosen element
    		if(count > randIndex) {
    			val = object;
    			break;
    		}
    	}
    	return val;
    	
    	
    }

    /**
     * Returns true if the Bag contains one or more instances of o
     */
    @Override
    public boolean contains(Object o) {
    	return bag.keySet().contains(o);
    }


    /**
     * Decrements the number of instances of o in the Bag.
     * 
     * @return return true if and only if at least one instance of o exists in
     *         the Bag and was removed.
     */
    @SuppressWarnings("unchecked")
	@Override
    public boolean remove(Object o) {
    	if(bag.keySet().contains(o)) {
    		int val = bag.get(o);
    		if(val == 1) {
    			bag.remove(o);
    		}else {
    			val--;
    			bag.put((T) o, val);
    		}
    			
    	}else {
    		return false;
    	}
    	size--;
    	return true;
    }

    /**
     * Total number of instances of any object in the Bag (counting duplicates)
     */
    @Override
    public int size() {
    	return size;
    }
}