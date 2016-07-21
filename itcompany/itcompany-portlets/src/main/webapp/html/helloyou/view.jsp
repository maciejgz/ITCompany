<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<jsp:useBean id="userName" class="java.lang.String" scope="request" />

<portlet:defineObjects />

<p>This is the Hello You portlet.</p>
<p>Hello <%= userName %>!</p>
