<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="javax.portlet.PortletURL"%>
<jsp:useBean class="java.lang.String" id="addNameURL" scope="request" />

<portlet:defineObjects />

<form
	id="<portlet:namespace />form"
	action="<%= addNameURL %>"
	method="post">

	<table>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="username"></td>
		</tr>
	</table>
	<input type="submit" id="nameButton" title="Add Name" value="Add Name">
</form>


<%-- <%
    PortletURL addSecondNameActionURL = renderResponse.createActionURL();
	addSecondNameActionURL.setParameter("javax.portlet.action", "addSecondNameActionUrl");
%> --%>
<portlet:actionURL var="addSecondNameActionURL" name="addSecondNameActionURL"/>
<a href="<%=addSecondNameActionURL%>">Druga akcja</a>
Druga akcja kieruje tylko do innej metody