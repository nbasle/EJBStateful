/*
 * Created on 21 janv. 2006
 * BASLE Nadiege
 */
package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

import javax.ejb.RemoveException;

import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.server.cart.ShoppingCart;
import com.yaps.petstore.server.cart.ShoppingCartHome;

/**
 * ShoppingCartDelegate
 *
 */
public final class ShoppingCartDelegate {
	
	// ====================================
	// =        Attributes                =
	// ====================================
private static  ShoppingCartHome shoppingCartHome;
private static  ShoppingCart shoppingCartRemote;
	
    // ==================================
	// =  Business methods              =
	// ==================================
/**
 *  return shopping cart
 */
	public static Map getCart() throws RemoteException {		
		return getShoppingCart().getCart();
	}

	/**
	 * Add a new item in the list
	 * @param itemId
	 * @throws RemoteException
	 */
	public static void addItem(final String itemId) throws RemoteException {
		 getShoppingCart().addItem(itemId);		
	}

	/**
	 * @return all the items to buy in the caddy
	 * @throws RemoteException
	 */
	public static Collection getItems() throws RemoteException {		
		return getShoppingCart().getItems();
	}
	/**
	 * Remove item in the caddy
	 * @throws RemoveException
	 * @throws RemoteException
	 *
	 */
	public static void removeItem(final String itemId) throws RemoteException, RemoveException {
		getShoppingCart().removeItem(itemId);
	}
	/**
	 *  update quantity of item to add in the caddy
	 * @throws RemoteException
	 *
	 */
	public static void updateItemQuantity(final String item, final int quantity) throws RemoteException {
		getShoppingCart().updateItemQuantity(item, quantity);
	}
	/**
	 * calculate the total to pay
	 * @return sum
	 * @throws RemoteException
	 */
	public static Double getTotal() throws RemoteException {
		return getShoppingCart().getTotal();
	}

	/**
	 * Empty the caddy
	 * @throws RemoteException
	 */
	public static void empty() throws RemoteException {
		getShoppingCart().empty();
		
	}
	// ===================================
	// =      Private methods            =
	// ===================================
private static ShoppingCart getShoppingCart() throws RemoteException {
	
	try {
		// Looks up for the home interface
		if(shoppingCartHome == null) {
			shoppingCartHome = (ShoppingCartHome) ServiceLocator.getInstance().getHome(ShoppingCartHome.JNDI_NAME, ShoppingCartHome.class);
		}
		// Creates the remote interface
		if (shoppingCartRemote == null)
		{
			shoppingCartRemote = shoppingCartHome.create();
		}
		
	} catch (Exception e){
		throw new RemoteException("Lookup exception or create exception", e);
	}
	return shoppingCartRemote;
}
}
