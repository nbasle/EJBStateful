/*
 * Created on 21 janv. 2006
 * ShoppingCartHome
 */
package com.yaps.petstore.server.cart;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;



/**
 * @author Veronique
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ShoppingCartHome extends EJBHome {
	static final String JNDI_NAME = "ejb/stateful/ShoppingCart";
 ShoppingCart create() throws RemoteException,CreateException;
}
