/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.aber.dcs.bpt.cs21120.assignment1;

/**
 * Get the indicated competition manager
 * @author bpt
 */
public class IManagerFactory {
    
    /**
     * Method to get the competition manager
     * @param className indicates the name of the class you would like returned, including full package name
     * @return returns an IManager
     */
    public static IManager getManager(String className) {
        
        Object obj;
        IManager manager;
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		if (obj instanceof IManager) {
			manager = (IManager)obj;
			System.out.println("IManager selected successfully. ");
		} else {
			System.out.println(className + " is not a IManager. ");
			return null;
		}
		return manager;
    }
}
