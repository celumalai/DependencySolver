package com.depsolver;

/**
 * Simple java equivalent of C# BitArray class
 * 
 * @author chandranelumalai
 *
 */
public class BitArray 
{
    private int[] elements;
    private int length;

    public BitArray(int maxSize) 
    {
        length = maxSize;
        elements = new int[(int)Math.ceil(maxSize / 32f)];
    }
    
    /**
     * Compare two bit arrays and return true if they match
     * 
     * @param arr1
     * @param arr2
     * @return
     */
    public static Boolean CompareBits(BitArray arr1, BitArray arr2)
    {
        if (arr1.getLength() != arr2.getLength())
        {
            return false;
        }

        Boolean bMatch = true;
        for (int i = 0; i < arr1.getLength(); i++)
        {
            if (arr1.get(i) != arr2.get(i))
            {
                bMatch = false;
                break;
            }
        }
        return bMatch;
    }    
    
    public boolean get(int index) 
    {
        return (elements[(int)Math.floor(index / 32f)] >> (index % 32) & 0x01) == 1;
    }

    public void set(int index, boolean value) 
    {
        int actualIndex = (int)Math.floor(index / 32f);
        int elementValue = elements[actualIndex] & ~(0x01 << (index % 32));

        if (value) 
        {
            elementValue = elementValue | (0x01 << (index % 32));
        }

        elements[actualIndex] = elementValue;
    }

    public int getLength() 
    {
        return length;
    }
    
    /**
     * or two bit arrays and return the result
     * 
     * @param toArray
     * @return
     */
    public BitArray or(BitArray toArray)
    {
    		if(toArray.getLength() != this.length) 
    			throw new IllegalArgumentException("Unequal array sent to or function");
    		
    		BitArray orArray = new BitArray(toArray.getLength());
		
    		for(int i=0;i<this.length;i++)
    		{
    			if(this.get(i) == true || toArray.get(i) == true)
    				orArray.set(i, true);
    			else
    				orArray.set(i, false);
    		}
    		
    		return orArray;
    }

	@Override
	public String toString() 
	{
		String s = "BitArray elements=";
		for(int i=0; i<elements.length; i++)
		{
			s += elements[i];
		}
		return s;
	}
}
