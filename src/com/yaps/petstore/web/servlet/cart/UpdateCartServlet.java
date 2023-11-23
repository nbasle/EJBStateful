/*
 * Created on 21 janv. 2006
 *
 */
package com.yaps.petstore.web.servlet.cart;

import java.io.IOException;
import java.rmi.RemoteException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.web.servlet.AbstractServlet;
import com.yaps.petstore.common.delegate.ShoppingCartDelegate;

/**
 * @author BASLE Nadiege Veronique
 *
 */
public class UpdateCartServlet extends AbstractServlet {

	// =====================
	// = Service method    =
	// =====================
	protected void service (final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
	{
		final String mname = "service";
		Trace.entering(getCname(), mname);
		try {
			// Updates the item into the Shopping Cart			
			
			ShoppingCartDelegate.updateItemQuantity(request.getParameter("itemId"), new Integer(request.getParameter("quantity")).intValue());
			getServletContext().getRequestDispatcher("/viewcart").forward(request, response);
		} catch (RemoteException e) {
			Trace.throwing(getCname(), mname, e);
            getServletContext().getRequestDispatcher("/error.jsp?exception=Cannot update the item to the shopping cart").forward(request, response);
		}
	}
}
