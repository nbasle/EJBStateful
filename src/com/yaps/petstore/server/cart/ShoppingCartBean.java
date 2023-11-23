/*
 * Created on 21 janv. 2006
 * ShopingCartBean.java
 */
package com.yaps.petstore.server.cart;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ShoppingCartItemDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.service.AbstractRemoteService;
import com.yaps.petstore.server.service.catalog.CatalogService;
import com.yaps.petstore.server.service.catalog.CatalogServiceHome;

/**
 * ShoppingCartBean
 */
public class ShoppingCartBean extends AbstractRemoteService {
	private final transient String _cname = this.getClass().getName();
	private  final Map _shoppingCart = new HashMap();
	
	
	// ==================================
	// =   Business methods             =
	// ==================================
	/**
	 *  return the hashmap
	 */
	public Map getCart() {
		final String mname = "getCart";
		Trace.entering(getCname(), mname);
				
		return _shoppingCart;
	}
	/**
	 * Return all items selected by customer
	 * @return
	 */
	public Collection getItems() {
		final String mname ="getItems";
		Trace.entering(getCname(),mname);
		// fill the list of items
		final Collection itemsDTO = new ArrayList();
		final Set mapset =getCart().entrySet();
		final Iterator mapiterator = mapset.iterator();
		
		while(mapiterator.hasNext()) {
			try {
				final Map.Entry e =(Map.Entry )mapiterator.next();
				final String itemId =(String)e.getKey();				 
                final Integer itemquantity =(Integer)e.getValue();
		        final Item item = new Item();
           
		        // Finds the object
		        item.findByPrimaryKey(itemId);

		        // Transforms domain object into DTO
		        final ShoppingCartItemDTO shoppitemdto = new ShoppingCartItemDTO(itemId,item.getName(),item.getProduct().getDescription(),itemquantity.intValue(),item.getUnitCost());
			    itemsDTO.add(shoppitemdto);
			    
			} catch(Exception ex) {
				
			}
		}
		
		return itemsDTO;
	}
	/**
	 * Add an item in the list
	 * @param itemId
	 */
	public void addItem(final String itemId) {
		getCart().put(itemId, new Integer(1));
		
	}
	/**
	 * Remove an item of the list
	 * @param itemId
	 */
	public void removeItem(final String itemId) {
		getCart().put(itemId,null);
			getCart().remove(itemId);
			
		
	}
	/**
	 * Update the quantity for an item
	 * @param itemId
	 * @param newQty
	 */
	public void updateItemQuantity(final String itemId, final int newQty) {
		
		getCart().put(itemId, new Integer(newQty));
		
	}
	/**
	 * Return sum to pay
	 * @return
	 */
	public Double getTotal() {
		double total = 0 ;
		 Set mapset = getCart().entrySet();
		Iterator mapiterator = mapset.iterator();
		while(mapiterator.hasNext()){
			final Map.Entry e = (Map.Entry)mapiterator.next();
			try {
				final Item item = new Item();
				String itemId =(String)e.getKey();
				Integer itemquantity =(Integer)e.getValue();
		        // Finds the object
		        item.findByPrimaryKey(itemId);

			total = total + itemquantity.doubleValue() * item.getUnitCost();
			} catch(Exception exp)
			{
				
			}
			
			
		}
		return new Double(total);
	}
	/**
	 * Empty the Shopping cart map
	 *
	 */
	public void empty() {
		getCart().clear();
	}
	// =================================
	


   //	 =================================
	// =      Private methods          =
	// =================================
	
	 
}
