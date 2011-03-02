<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:useBean id="myapps" scope="session" class="fr.paris.lutece.plugins.myapps.web.MyAppsJspBean" />
<% 
	myapps.init( request, myapps.RIGHT_MANAGE_ADMINDASHBOARD );
    response.sendRedirect( myapps.doModifyMyAppsParameterDefaultValues( request ));
%>
