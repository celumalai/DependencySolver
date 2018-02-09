# ProjectOrganizer

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