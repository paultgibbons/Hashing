/**
 * Examlpe implementtion of Hashing.java
 */
import java.util.Random;
public class HashExample
{
	public static void main(String[]args)
	{
		Random rng = new Random();
		Hashing example = new Hashing(61);
		String[] toCheck = new String[3];
		
		for (int i=5; i<=7; i++) //words added will be of length 5 to 7
		{
			for (int j=0; j<200; j++) //200 words of each length
			{
				String name = "";
				int value = (rng.nextInt(10000)+1) ;// values will range from 1 to 10000
				for (int k=0; k<i; k++)
				{
					char c = (char) (rng.nextInt(26)+97); // all lowercase letters from a to z
					name += c;
				}
				example.set(name, value);
				if (j==150)
				{
					toCheck[i-5] = name;
				}
			}
		}
		for (int i=0; i<toCheck.length; i++)
		{
			System.out.println("Name: " + toCheck[i] + ", Value: "+ example.getValue(toCheck[i]));
		}
	}
}
