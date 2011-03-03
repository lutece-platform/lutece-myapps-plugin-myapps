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
package fr.paris.lutece.plugins.myapps.business.portlet;

import fr.paris.lutece.plugins.myapps.business.MyApps;
import fr.paris.lutece.plugins.myapps.service.MyAppsManager;
import fr.paris.lutece.plugins.myapps.service.MyAppsPlugin;
import fr.paris.lutece.plugins.myapps.service.MyAppsProvider;
import fr.paris.lutece.plugins.myapps.service.parameter.MyAppsParameterService;
import fr.paris.lutece.plugins.myapps.service.portlet.MyAppsPortletService;
import fr.paris.lutece.portal.business.portlet.Portlet;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.url.UrlItem;
import fr.paris.lutece.util.xml.XmlUtil;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * MyAppsPortlet
 *
 */
public class MyAppsPortlet extends Portlet
{
    // TAGS
    private static final String TAG_MYAPPS_PROVIDERS_LIST = "myapps-providers-list";
    private static final String TAG_MYAPPS_PROVIDER = "myapps-provider";
    private static final String TAG_MYAPPS_PROVIDERS_NAME = "myapps-provider-name";
    private static final String TAG_MYAPPS_LIST = "myapps-list";
    private static final String TAG_MYAPP = "myapp";
    private static final String TAG_MYAPP_NAME = "myapp-name";
    private static final String TAG_MYAPP_ICON = "myapp-icon";
    private static final String TAG_MYAPP_DESCRIPTION = "myapp-description";
    private static final String TAG_MYAPP_LINK = "myapp-link";
    private static final String TAG_MYAPP_BUTTON = "myapp-button";

    // PARAMETERS
    private static final String PARAMETER_MYAPP_ID = "myapp_id";
    private static final String PARAMETER_PLUGIN_NAME = "plugin_name";
    private static final String PARAMETER_IS_ASC_SORT = "is_asc_sort";

    // PROPERTIES
    private static final String PROPERTY_MANAGE_MYAPPS_BUTTON_LABEL = "myapps.portlet.buttonManageMyApps";

    // JSP
    private static final String JSP_OPEN_MYAPP = "jsp/site/plugins/myapps/DoOpenMyApp.jsp";

    /**
     * Constructor
     */
    public MyAppsPortlet(  )
    {
    }

    /**
     * Returns the Xml code of the MyLutece portlet without XML heading
     *
     * @param request The HTTP Servlet request
     * @return the Xml code of the MyLutece portlet content
     */
    public String getXml( HttpServletRequest request )
    {
        String strUserName;

        try
        {
            LuteceUser user = SecurityService.getInstance(  ).getRemoteUser( request );

            if ( user != null )
            {
                strUserName = user.getName(  );
            }
            else
            {
                strUserName = StringUtils.EMPTY;
            }
        }
        catch ( UserNotSignedException e )
        {
            strUserName = StringUtils.EMPTY;
        }

        StringBuffer sbXml = new StringBuffer(  );

        if ( StringUtils.isNotBlank( strUserName ) )
        {
            List<MyAppsProvider> listProviders = MyAppsManager.getInstance(  ).getProvidersList(  );

            XmlUtil.beginElement( sbXml, TAG_MYAPPS_PROVIDERS_LIST );

            String strButtonLabel = I18nService.getLocalizedString( PROPERTY_MANAGE_MYAPPS_BUTTON_LABEL,
                    request.getLocale(  ) );
            Plugin plugin = PluginService.getPlugin( MyAppsPlugin.PLUGIN_NAME );
            ReferenceItem isAscSort = MyAppsParameterService.getInstance(  )
                                                            .getParamDefaultValue( PARAMETER_IS_ASC_SORT, plugin );

            for ( MyAppsProvider provider : listProviders )
            {
                XmlUtil.beginElement( sbXml, TAG_MYAPPS_PROVIDER );
                XmlUtil.addElement( sbXml, TAG_MYAPPS_PROVIDERS_NAME, provider.getProviderName( request.getLocale(  ) ) );
                XmlUtil.beginElement( sbXml, TAG_MYAPPS_LIST );

                for ( MyApps myapp : provider.getMyAppsListByUserName( strUserName, isAscSort.isChecked(  ) ) )
                {
                    XmlUtil.beginElement( sbXml, TAG_MYAPP );

                    UrlItem urlLink = new UrlItem( JSP_OPEN_MYAPP );
                    urlLink.addParameter( PARAMETER_MYAPP_ID, myapp.getIdApplication(  ) );
                    urlLink.addParameter( PARAMETER_PLUGIN_NAME, provider.getPluginName(  ) );
                    XmlUtil.addElement( sbXml, TAG_MYAPP_ICON,
                        provider.getResourceImage( String.valueOf( myapp.getIdApplication(  ) ) ) );
                    XmlUtil.addElement( sbXml, TAG_MYAPP_NAME, myapp.getName(  ) );
                    XmlUtil.addElement( sbXml, TAG_MYAPP_DESCRIPTION, myapp.getDescription(  ) );
                    XmlUtil.addElement( sbXml, TAG_MYAPP_LINK, urlLink.getUrlWithEntity(  ) );
                    XmlUtil.endElement( sbXml, TAG_MYAPP );
                }

                XmlUtil.endElement( sbXml, TAG_MYAPPS_LIST );

                XmlUtil.endElement( sbXml, TAG_MYAPPS_PROVIDER );
            }

            XmlUtil.addElement( sbXml, TAG_MYAPP_BUTTON, strButtonLabel );
            XmlUtil.endElement( sbXml, TAG_MYAPPS_PROVIDERS_LIST );
        }

        return addPortletTags( sbXml );
    }

    /**
     * Returns the Xml code of the MyApps portlet with XML heading
     *
     * @param request The HTTP servlet request
     * @return the Xml code of the MyLutece portlet
     */
    public String getXmlDocument( HttpServletRequest request )
    {
        return XmlUtil.getXmlHeader(  ) + getXml( request );
    }

    /**
     * Update portlet's data
     */
    public void update(  )
    {
        MyAppsPortletService.getInstance(  ).update( this );
    }

    /**
     * Remove of this portlet
     */
    public void remove(  )
    {
        MyAppsPortletService.getInstance(  ).remove( this );
    }
}
