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
package fr.paris.lutece.plugins.myapps.business;

import fr.paris.lutece.plugins.myapps.service.MyAppsProvider;
import fr.paris.lutece.portal.service.rbac.RBACResource;


/**
 *
 * MyApps
 *
 */
public abstract class MyApps implements RBACResource
{
    public static final String RESOURCE_TYPE = "MYAPPS";

    // Variables declarations
    private int _nIdApplication;
    private String _strName;
    private String _strDescription;
    private String _strUrl;

    /**
     * Check if the myApp has an icon or not
     *
     * @return true if it has an icon, false otherwise
     */
    public abstract boolean hasIcon(  );

    /**
     * Get the provider that provides this MyApp
     *
     * @return a {@link MyAppsProvider}
     */
    public abstract MyAppsProvider getProvider(  );

    /**
     * Returns the IdApplication
     *
     * @return The IdApplication
     */
    public int getIdApplication(  )
    {
        return _nIdApplication;
    }

    /**
     * Sets the IdApplication
     *
     * @param nIdApplication The IdApplication
     */
    public void setIdApplication( int nIdApplication )
    {
        _nIdApplication = nIdApplication;
    }

    /**
     * Returns the Name
     *
     * @return The Name
     */
    public String getName(  )
    {
        return _strName;
    }

    /**
     * Sets the Name
     *
     * @param strName The Name
     */
    public void setName( String strName )
    {
        _strName = strName;
    }

    /**
     * Returns the Description
     *
     * @return The Description
     */
    public String getDescription(  )
    {
        return _strDescription;
    }

    /**
     * Sets the Description
     *
     * @param strDescription The Description
     */
    public void setDescription( String strDescription )
    {
        _strDescription = strDescription;
    }

    /**
     * Returns the Url
     *
     * @return The Url
     */
    public String getUrl(  )
    {
        return _strUrl;
    }

    /**
     * Sets the Url
     *
     * @param strUrl The Url
     */
    public void setUrl( String strUrl )
    {
        _strUrl = strUrl;
    }

    /**
     * Get the resource ID
     *
     * @return the resource ID
     */
    public String getResourceId(  )
    {
        return String.valueOf( _nIdApplication );
    }

    /**
     * Get the resource type code
     *
     * @return the resource type code
     */
    public String getResourceTypeCode(  )
    {
        return RESOURCE_TYPE;
    }

    /**
     * Override the method equals
     * @param o Object
     * @return true if it is equals to the object o
     */
    @Override
    public boolean equals( Object o )
    {
        boolean bIsEqual = false;

        if ( o instanceof fr.paris.lutece.plugins.myapps.business.MyApps )
        {
            MyApps myApp = (MyApps) o;

            if ( myApp != null )
            {
                bIsEqual = _nIdApplication == myApp.getIdApplication(  );
            }
        }

        return bIsEqual;
    }

    /**
     * Override the method hashCode
     * @return the hashCode
     */
    @Override
    public int hashCode(  )
    {
        return _nIdApplication;
    }
}
