package com.depsolver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Data structure for holding project name and dependencies list
 * 
 * @author chandranelumalai
 *
 */
public class ProjectDefinition
{
	private String projName;
	private ArrayList<String> dependencies;
	
	public ProjectDefinition(String name, String[] deps )
	{
		if(name.isEmpty())
			throw new IllegalArgumentException("Null project not allowed");
		
		this.projName = name;
		
		if(deps != null)
		{
			this.dependencies = new ArrayList<String>(Arrays.asList(deps));
		}
	}
	
	public ProjectDefinition(String name)
	{
		this(name,  new String[] {});
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public ArrayList<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(ArrayList<String> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public String toString() 
	{
		return "ProjectDefinition [projName=" + projName + ", dependencies=" + dependencies + "]";
	}
	
}