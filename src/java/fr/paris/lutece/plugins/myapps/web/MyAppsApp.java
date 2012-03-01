/*
 * Copyright (c) 2002-2012, Mairie de Paris
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
package fr.paris.lutece.plugins.myapps.web;

import fr.paris.lutece.plugins.myapps.business.MyApps;
import fr.paris.lutece.plugins.myapps.service.MyAppsManager;
import fr.paris.lutece.plugins.myapps.service.MyAppsProvider;
import fr.paris.lutece.plugins.myapps.service.parameter.MyAppsParameterService;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.web.xpages.XPageApplication;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.sort.AttributeComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * MyAppsApp
 *
 */
public class MyAppsApp implements XPageApplication
{
    // TEMPLATES
    private static final String TEMPLATE_MYAPPS_IN_DIVIDED_LISTS = "skin/plugins/myapps/page_myapps_in_divided_lists.html";
    private static final String TEMPLATE_MYAPPS_IN_ONE_LIST = "skin/plugins/myapps/page_myapps_in_one_list.html";

    // MARKS
    private static final String MARK_MYAPPS_PROVIDERS_LIST = "myapps_providers_list";
    private static final String MARK_USER_NAME = "user_name";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_MYAPPS_LIST = "myapps_list";
    private static final String MARK_IS_ASC_SORT = "is_asc_sort";

    // PARAMETERS
    private static final String PARAMETER_IS_SHOWN_IN_ONE_LIST = "is_shown_in_one_list";
    private static final String PARAMETER_IS_ASC_SORT = "is_asc_sort";
    private static final String PARAMETER_NAME = "name";

    // PROPERTIES
    private static final String PROPERTY_PAGE_TITLE = "myapps.page_myapps.pageTitle";
    private static final String PROPERTY_PAGE_PATH = "myapps.page_myapps.pagePathLabel";

    /**
     * Front Office application to manage myapps application
     *
     * @param request The request
     * @param nMode The mode
     * @param plugin The plugin
     * @return The Xpage
     * @throws SiteMessageException exception if some parameters are not correctly filled
     * @throws UserNotSignedException exception if the current user is not connected
     */
    public XPage getPage( HttpServletRequest request, int nMode, Plugin plugin )
        throws SiteMessageException, UserNotSignedException
    {
        XPage page = new XPage(  );
        page.setTitle( I18nService.getLocalizedString( PROPERTY_PAGE_TITLE, request.getLocale(  ) ) );
        page.setPathLabel( I18nService.getLocalizedString( PROPERTY_PAGE_PATH, request.getLocale(  ) ) );

        page = getManageMyAppsPage( page, request, plugin );

        return page;
    }

    /**
     * Get the manage myApps interface
     *
     * @param page the {@link XPage}
     * @param request {@link HttpServletRequest}
     * @param plugin {@link Plugin}
     * @return a {@link XPage}
     * @throws UserNotSignedException exception if the current user is not connected
     */
    private XPage getManageMyAppsPage( XPage page, HttpServletRequest request, Plugin plugin )
        throws UserNotSignedException
    {
        LuteceUser user = SecurityService.getInstance(  ).getRemoteUser( request );

        if ( user != null )
        {
            ReferenceItem isShownInOneList = MyAppsParameterService.getInstance(  )
                                                                   .getParamDefaultValue( PARAMETER_IS_SHOWN_IN_ONE_LIST,
                    plugin );
            ReferenceItem isAscSort = MyAppsParameterService.getInstance(  )
                                                            .getParamDefaultValue( PARAMETER_IS_ASC_SORT, plugin );
            List<MyAppsProvider> listProviders = MyAppsManager.getInstance(  ).getProvidersList(  );

            HtmlTemplate template;
            Map<String, Object> model = new HashMap<String, Object>(  );
            model.put( MARK_USER_NAME, user.getName(  ) );
            model.put( MARK_LOCALE, request.getLocale(  ) );
            model.put( MARK_MYAPPS_PROVIDERS_LIST, listProviders );

            // Checking if we display in one list or if we display by modules
            if ( isShownInOneList.isChecked(  ) )
            {
                List<MyApps> listMyApps = new ArrayList<MyApps>(  );

                for ( MyAppsProvider provider : listProviders )
                {
                    listMyApps.addAll( provider.getMyAppsListByUserName( user.getName(  ), isAscSort.isChecked(  ) ) );
                }

                Collections.sort( listMyApps, new AttributeComparator( PARAMETER_NAME, isAscSort.isChecked(  ) ) );
                model.put( MARK_MYAPPS_LIST, listMyApps );
                template = AppTemplateService.getTemplate( TEMPLATE_MYAPPS_IN_ONE_LIST, request.getLocale(  ), model );
            }
            else
            {
                model.put( MARK_IS_ASC_SORT, isAscSort.isChecked(  ) );
                template = AppTemplateService.getTemplate( TEMPLATE_MYAPPS_IN_DIVIDED_LISTS, request.getLocale(  ),
                        model );
            }

            page.setContent( template.getHtml(  ) );
        }
        else
        {
            throw new UserNotSignedException(  );
        }

        return page;
    }
}
