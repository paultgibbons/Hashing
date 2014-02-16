/**
 * @file Hashing.java
 * A class providing a hash function
 *
 * @author Paul Gibbons
 * @date Created: Spring 2014
 */
public class Hashing
{
	/**
	 * Constructor
	 * Returns new hash table
	 *
	 * @param numBuckets the number of buckets in the table
	 */
	public Hashing(int numBuckets)
	{
		buckets = new List[numBuckets];
	}

	/**
	 * Returns the location of the bucket containing the value
	 *
	 * @param name The name of the item
	 * @return the bucket containing the names value
	 */
	private int getBucket(String name)
	{
		int bucket = 0;
		for(int i=0; i<name.length(); i++)
		{
			bucket+= name.charAt(i);
		}
		return bucket%buckets.length; 
	}

	/**
	 * Adds a name and value to the hash table
	 * if a name is already in storage, changes the value associated with that name
	 *
	 * @param name The name of the item
	 * @param value The value to be stored with that name
	 */
	public void set(String name, int value)
	{
		if (name==null)
		{
			return;
		}
		int bucket = getBucket(name);
		List current = new List(value, name);
		if (this.buckets[bucket]==null) this.buckets[bucket] = current;
		else this.buckets[bucket].append(current);
	}

	/**
	 * Returns the value stored with a given index
	 *
	 * @param name The search vale
	 * @param buckets Array of lists containing data
	 * @return value The value associated with the name, -1 if value was not found
	 */
	public int getValue(String name)
	{
		if (name==null)
		{
			return -1;
		}
		int bucket = getBucket(name);
		List temp = this.buckets[bucket];
		int rvalue = temp.find(name);
		return rvalue;
	}
	/**
	 * Deletes the value associated with name
	 * 
	 * @param name The name associated with the value to be deleted
	 * @param buckets Array of lists containing data
	 */
	public void delete(String name)
	{
		if (name==null)
		{
			return;
		}
		int bucket = getBucket(name);
		if (buckets[bucket]==null) return;
		buckets[bucket].remove(name);
	}

	/**
	 * Class to make linked List of values in buckets
	 * This will deal with collision
	 */
	private class List
	{
		int value;
		String name;
		List next;
		public List(int value, String name)
		{
			this.value = value;
			this.name = name;
			next = null;
		}
		public void append(List newList)
		{
			if(newList.name.equals(name))
			{
				value = newList.value;
			}
			if (next==null)
			{
				next = newList;
			}
			else
			{
				next.append(newList);
			}
		}
		public int find(String name)
		{
			if (name.equals(this.name)) return value;
			if (next==null) return -1;
			return next.find(name);
		}
		public List remove(String name)
		{			
			if (this.name.equals(name))
			{
				return next;
			}
			if (next==null) return this;
			next = next.remove(name);
			return this;
		}
	}
	/**
	 * Instance variables
	 */
	private List[] buckets;
}
