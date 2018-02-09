package com.depsolver;

import java.util.ArrayList;

/**
 * Construct a matrix of Pn x Pn size.
 * Pn (rows) number of bit arrays each of length Pn (columns)
 * Initialize the bitarray of each of the project with its dependencies
 * 
 *     p1  p2  p3  ....  pn
 * p1          1
 * p2
 * p3      1
 * ...
 * 
 * pn
 * 
 * Traverse the bit arrays with a bit array 'mask' evaluating against the
 * dependencies of each project. Start with all-zero mask and 'or' them with 
 * each of the project dependency. Iteratively evaluate the match and reorder
 * the projects based on the dependency graph.
 * 
 * @author chandranelumalai
 *
 */
public class DependencyAnalyzer
{
    ArrayList<BitArray> _rows;
    ArrayList<ProjectDefinition> _projects;

    public DependencyAnalyzer( ArrayList<ProjectDefinition> projects ) throws IllegalArgumentException
    {
        if (projects == null || projects.isEmpty())
            throw new IllegalArgumentException("Invalid list of projects supplied to classfier");

        _projects = projects;
        _rows = new ArrayList<BitArray>( _projects.size() );
        classify();	// initialize the matrix
    }

    /**
     * Initialize the project matrix 
     */
    private void classify()
    {
        // Initialize the data
        for (int i = 0; i < _projects.size(); i++)
        {
            _rows.add( new BitArray(_projects.size()) );
        }

        for (int i = 0; i < _projects.size(); i++)
        {
            String proj = ((ProjectDefinition)(_projects.get(i))).getProjName();
            for(int j=0; j<_projects.size(); j++ )
            {
                // go through the dependencies of the jth project
                // and fill the byte array for the ith element
                //if(i==j) continue;
            		
                if (_projects.get(j).getDependencies().contains(proj))
                {
                    _rows.get(j).set(i, true);
                }
            }
        }
    }

	/**
	 * The logic of re-ordering the project map is implemented here
	 * 
	 * @return
	 */
    public ArrayList<ProjectDefinition> resolve()
    {
        ArrayList<ProjectDefinition> orderedSet = new ArrayList<ProjectDefinition>(_rows.size());
        BitArray mask = new BitArray(_rows.size());

        BitArray fullHouse = new BitArray(_rows.size());
        for(int i=0;i <_rows.size(); i++)
        		fullHouse.set(i, true);	// set all bits to true
        
        int iterations = 0;
        while (BitArray.CompareBits(fullHouse, mask) != true)
        {
            // go through the bit arrays and pick out dependency free lists
            ArrayList<Integer> maskIndices = new ArrayList<Integer>();
            for (int i = 0; i < _rows.size(); i++)
            {
                BitArray bits = (BitArray)_rows.get(i);
                BitArray dependencies = new BitArray(_rows.size());
                dependencies = bits.or(mask);  // Or with mask

                if (BitArray.CompareBits(mask, dependencies))
                {
                    if (orderedSet.contains(_projects.get(i)) == false)
                    {
                        orderedSet.add(_projects.get(i));
                        maskIndices.add(i);
                    }
                }
            }

            // safety check
            if (maskIndices.size() == 0)
            {
                System.out.println("Possible cyclic dependency detected");
                break;
            }

            // set the new mask
            for(Integer i=0; i<maskIndices.size(); i++)
            {
                mask.set(maskIndices.get(i), true);
            }

            iterations++;
        }

        System.out.println("Resolved after " + iterations + " iterations");
        return orderedSet;
    }
}