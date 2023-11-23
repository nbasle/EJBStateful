/*
 * Created on 21 janv. 2006
 *  Remove a item from the caddy
 */
package com.yaps.petstore.web.servlet.cart;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.ejb.RemoveException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.web.servlet.AbstractServlet;
import com.yaps.petstore.common.delegate.ShoppingCartDelegate;

/**
 * @author BASLE Nadiege Veronique
 */
public class RemoveItemFromCartServlet extends AbstractServlet {
	// ==============================
	// =      Service method        =
	// ==============================
	protected void service (final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String mname = "service";
		Trace.entering(getCname(), mname);
		try {
			// Remove the Item with the itemId
			ShoppingCartDelegate.removeItem(request.getParameter("itemId"));
			
			getServletContext().getRequestDispatcher("/viewcart").forward(request, response);
		} catch(RemoveException re) {
			Trace.throwing(getCname(),mname, re);
			getServletContext().getRequestDispatcher("/error.jsp?exception=Cannot remove this item from the shopping cart").forward(request, response);
		}
		catch(RemoteException e) {
			Trace.throwing(getCname(), mname,e);
			getServletContext().getRequestDispatcher("/error.jsp?exception=Cannot remove this item from the shopping cart").forward(request, response);
		}
	}
}
