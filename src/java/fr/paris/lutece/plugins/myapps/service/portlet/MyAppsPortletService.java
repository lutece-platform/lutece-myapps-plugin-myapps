/*
 * Copyright (c) 2002-2011, Mairie de Paris
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
package fr.paris.lutece.plugins.myapps.service.portlet;

import fr.paris.lutece.plugins.myapps.business.portlet.MyAppsPortlet;
import fr.paris.lutece.plugins.myapps.business.portlet.MyAppsPortletHome;
import fr.paris.lutece.portal.business.portlet.Portlet;
import fr.paris.lutece.portal.business.portlet.PortletHome;


/**
 * MyAppsPortletService
 */
public class MyAppsPortletService
{
    private static MyAppsPortletService _singleton;

    /**
     * Creates a new MyAppsPortletService object.
     */
    public MyAppsPortletService(  )
    {
    }

    /**
     * Get the instance of the {@link MyAppsPortletService}
     *
     * @return the instance of {@link MyAppsPortletService}
     */
    public static synchronized MyAppsPortletService getInstance(  )
    {
        if ( _singleton == null )
        {
            _singleton = new MyAppsPortletService(  );
        }

        return _singleton;
    }

    /**
     * Find a {@link MyAppsPortlet} by its primary key
     *
     * @param nPortletId the portlet ID
     * @return a {@link MyAppsPortlet}
     */
    public MyAppsPortlet findByPrimaryKey( int nPortletId )
    {
        return (MyAppsPortlet) PortletHome.findByPrimaryKey( nPortletId );
    }

    /**
     * Create a new {@link MyAppsPortlet}
     *
     * @param portlet a {@link MyAppsPortlet}
     */
    public void create( Portlet portlet )
    {
        MyAppsPortletHome.getInstance(  ).create( portlet );
    }

    /**
     * Update a new {@link MyAppsPortlet}
     *
     * @param portlet a {@link MyAppsPortlet}
     */
    public void update( Portlet portlet )
    {
        MyAppsPortletHome.getInstance(  ).update( portlet );
    }

    /**
     * Create a new {@link MyAppsPortlet}
     *
     * @param portlet a {@link MyAppsPortlet}
     */
    public void remove( Portlet portlet )
    {
        MyAppsPortletHome.getInstance(  ).remove( portlet );
    }
}
