<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:useBean id="myapps" scope="session" class="fr.paris.lutece.plugins.myapps.web.MyAppsJspBean" />
<% 
	myapps.init( request, fr.paris.lutece.portal.web.dashboard.AdminDashboardJspBean.RIGHT_MANAGE_ADMINDASHBOARD );
    response.sendRedirect( myapps.doModifyMyAppsParameterDefaultValues( request ));
%>
