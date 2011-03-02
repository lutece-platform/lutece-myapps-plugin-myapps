/*
 * Copyright (c) 2002-2010, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.myapps.web.portlet;

import fr.paris.lutece.plugins.myapps.business.portlet.MyAppsPortlet;
import fr.paris.lutece.plugins.myapps.service.MyAppsManager;
import fr.paris.lutece.plugins.myapps.service.MyAppsProvider;
import fr.paris.lutece.plugins.myapps.service.portlet.MyAppsPortletService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.web.constants.Parameters;
import fr.paris.lutece.portal.web.portlet.PortletJspBean;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * MyAppsPortletJspBean
 *
 */
public class MyAppsPortletJspBean extends PortletJspBean
{
    private static final String PARAMETER_PLUGIN_NAME = "plugin_name";
    private static final String PARAMETER_MYAPP_ID = "myapp_id";
    private static final String MESSAGE_NOT_NUMERIC = "myapps.message.notNumeric";
    private static final String MESSAGE_ERROR = "message.error";

    /**
     * Returns portlet user application's creation form
     *
     * @param request request
     * @return Html form
     */
    public String getCreate( HttpServletRequest request )
    {
        String strPageId = request.getParameter( Parameters.PAGE_ID );
        String strPortletTypeId = request.getParameter( Parameters.PORTLET_TYPE_ID );
        HtmlTemplate template = getCreateTemplate( strPageId, strPortletTypeId );

        return template.getHtml(  );
    }

    /**
     * Do create a {@link MyAppsPortlet}
     * 
     * @param request {@link HttpServletRequest}
     * @return The Jsp management URL of the process result
     */
    public String doCreate( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strPageId = request.getParameter( Parameters.PAGE_ID );

        if ( StringUtils.isNotBlank( strPageId ) && StringUtils.isNumeric( strPageId ) )
        {
            MyAppsPortlet portlet = new MyAppsPortlet(  );
            String strErrorUrl = setPortletCommonData( request, portlet );

            if ( StringUtils.isBlank( strErrorUrl ) )
            {
                int nIdPage = Integer.parseInt( strPageId );
                portlet.setPageId( nIdPage );
                MyAppsPortletService.getInstance(  ).create( portlet );

                //Displays the page with the new Portlet
                strUrl = getPageUrl( nIdPage );
            }
            else
            {
                strUrl = strErrorUrl;
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_NOT_NUMERIC, AdminMessage.TYPE_ERROR );
        }

        return strUrl;
    }

    /**
     * Returns portlet user application's modification form
     *
     * @param request request
     * @return Html form
     */
    public String getModify( HttpServletRequest request )
    {
        String strHtml = StringUtils.EMPTY;
        String strPortletId = request.getParameter( Parameters.PORTLET_ID );

        if ( StringUtils.isNotBlank( strPortletId ) && StringUtils.isNumeric( strPortletId ) )
        {
            int nPortletId = Integer.parseInt( strPortletId );
            MyAppsPortlet portlet = MyAppsPortletService.getInstance(  ).findByPrimaryKey( nPortletId );

            if ( portlet != null )
            {
                HtmlTemplate template = getModifyTemplate( portlet );
                strHtml = template.getHtml(  );
            }
            else
            {
                strHtml = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR, AdminMessage.TYPE_ERROR );
            }
        }
        else
        {
            strHtml = AdminMessageService.getMessageUrl( request, MESSAGE_NOT_NUMERIC, AdminMessage.TYPE_ERROR );
        }

        return strHtml;
    }

    /**
     * Process portlet's modification
     *
     * @param request request
     * @return The Jsp management URL of the process result
     */
    public String doModify( HttpServletRequest request )
    {
        String strUrl = StringUtils.EMPTY;
        String strPortletId = request.getParameter( Parameters.PAGE_ID );

        if ( StringUtils.isNotBlank( strPortletId ) && StringUtils.isNumeric( strPortletId ) )
        {
            int nPortletId = Integer.parseInt( strPortletId );
            MyAppsPortlet portlet = MyAppsPortletService.getInstance(  ).findByPrimaryKey( nPortletId );

            if ( portlet != null )
            {
                String strErrorUrl = setPortletCommonData( request, portlet );

                if ( StringUtils.isBlank( strErrorUrl ) )
                {
                    int nIdPage = Integer.parseInt( strPortletId );
                    portlet.setPageId( nIdPage );
                    MyAppsPortletService.getInstance(  ).update( portlet );

                    //Displays the page with the new Portlet
                    strUrl = getPageUrl( portlet.getPageId(  ) );
                }
                else
                {
                    strUrl = strErrorUrl;
                }
            }
            else
            {
                strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_ERROR, AdminMessage.TYPE_ERROR );
            }
        }
        else
        {
            strUrl = AdminMessageService.getMessageUrl( request, MESSAGE_NOT_NUMERIC, AdminMessage.TYPE_ERROR );
        }

        return strUrl;
    }

    /**
     * Returns application url with parameters
     *
     * @param request request
     * @return strLink application url
     * @throws UserNotSignedException access denied if the user is not connected
     */
    public String doOpenMyApp( HttpServletRequest request )
        throws UserNotSignedException
    {
        String strUrl = AppPathService.getBaseUrl( request );
        LuteceUser user = SecurityService.getInstance(  ).getRemoteUser( request );

        if ( user != null )
        {
            String strPluginName = request.getParameter( PARAMETER_PLUGIN_NAME );
            String strMyAppId = request.getParameter( PARAMETER_MYAPP_ID );

            if ( StringUtils.isNotBlank( strPluginName ) && StringUtils.isNotBlank( strMyAppId ) &&
                    StringUtils.isNumeric( strMyAppId ) )
            {
                int nMyAppId = Integer.parseInt( strMyAppId );
                MyAppsProvider provider = MyAppsManager.getInstance(  ).getProvider( strPluginName );

                if ( provider != null )
                {
                    strUrl = provider.getUrlOpenMyApps( nMyAppId, user );
                }
            }
        }
        else
        {
            throw new UserNotSignedException(  );
        }

        return strUrl;
    }
}
