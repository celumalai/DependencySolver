package com.depsolver;

import java.util.ArrayList;

/**
 * 
 * @author chandranelumalai Fri Dec 8, 2017
 *
 * This is a simple project dependency resolver.
 * If there are multiple projects that depend on each other to build,
 * this algorithm will efficiently resolve the order to build the projects
 * sequentially.
 * 
 * In this example, for simplicity I'm hard coding the following project structure
 * 
 * There are five projects: p1, p2, p3, p4 and p5.
 * 
 * p1 depends on p3;
 * p2 can be built independently
 * p3 depends on p2;
 * p4 depends on p3, p5 and
 * p5 depends on p1
 * 
 * The correct order to build this set of projects is then p2, p3, p1, p5 and p4.
 * 
 * This example shows a generic algorithm that can expand to large number of projects and
 * resolve dependencies quickly to evaluate the order of build
 *  
 */
public class ProjectOrganizer {
	
	public static void main(String[] args)
    {
		ProjectOrganizer rr = getProjectOrganizer();
		
		DependencyAnalyzer da = new DependencyAnalyzer(rr.get_projDefs());
		ArrayList<ProjectDefinition> orderedList = da.resolve();
		
		System.out.println("Project resolution order");
		for(int i=0;i<orderedList.size();i++)
		{
			System.out.println(orderedList.get(i).toString());
		}
    }
	
/**
 * Singleton ProjectOrganizer getter
 * @return
 */
	public static ProjectOrganizer getProjectOrganizer()
	{
		if(_organizer == null)
		{
			_organizer = new ProjectOrganizer();
		}
		
		return _organizer;	
	}
	
	private static ProjectOrganizer _organizer = null;
	ArrayList<ProjectDefinition> _projDefs = null;	
	
	/*
	 * Constructor initializes the input data set.
	 * A JSON can be introduced and parsed with this input.
	 * 
	 */
	private ProjectOrganizer()
	{	
		// Initialize project definitions
		_projDefs = new ArrayList<ProjectDefinition>();
		_projDefs.add(new ProjectDefinition("p1", new String[]{"p3"}));
		_projDefs.add(new ProjectDefinition("p2"));
		_projDefs.add(new ProjectDefinition("p3", new String[]{"p2"}));		
		_projDefs.add(new ProjectDefinition("p4", new String[]{"p3", "p5"}));
		_projDefs.add(new ProjectDefinition("p5", new String[]{"p1"}));
	}

	private ArrayList<ProjectDefinition> get_projDefs() 
	{
		return _projDefs;
	}
} 
