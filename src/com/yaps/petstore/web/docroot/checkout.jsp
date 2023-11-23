<%@ page errorPage="error.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>YAPS PetStore - Checkout.jsp</title>
</head>
<body>

<table cellspacing="0" cellpadding="5" width="100%">
    <%--HEADER--%>
	<tr>
		<td colspan="3">
			<jsp:include page="common/header.jsp"/>
		</td>
	</tr>

	<tr>
        <%--NAVIGATION--%>
        <td valign="top" width="20%">
    		<jsp:include page="common/navigation.jsp"/>
    	</td>

        <td align="left" valign="top" width="60%">
        <%--CENTRAL BODY--%>



           

            <P><strong>Your Order is Complete</strong></P>
            <TABLE cellSpacing=0 cellPadding=1 width="100%" border=0>
                <TR>
                    <TD>
                        <TABLE cellSpacing=0 cellPadding=2 width="100%" border=0>
                            <TR>                                
                                <TD valign="top">
                                    <strong>Your order id is: </strong>${requestScope.orderId}
                                        <BR><BR>
                                        <strong>Thank you for shopping with the Yaps Pet Store</strong>
                                </TD>
                            </TR>
                        </TABLE>
                    </TD>
                </TR>
            </TABLE>

           



    <%--FOOTER--%>
    	</td>
        <td></td>
    </tr>
    <tr>
    	<td colspan="3">
    		<jsp:include page="common/footer.jsp"/>
    	</td>
    </tr>
</table>
</body>
</html>