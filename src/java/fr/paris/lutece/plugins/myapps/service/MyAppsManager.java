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
package fr.paris.lutece.plugins.myapps.service;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.util.AppLogService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * MyAppsManager
 *
 */
public final class MyAppsManager
{
    private static final String MESSAGE_PLUGIN_NOT_FOUND = "Plugin not found or not installed for plugin name ";
    private static MyAppsManager _singleton;
    private Map<String, MyAppsProvider> _listRegisteredProviders = new HashMap<String, MyAppsProvider>(  );

    /**
     * Private constructor
     */
    private MyAppsManager(  )
    {
    }

    /**
     * Get the instance of {@link MyAppsManager}
     *
     * @return the instance of {@link MyAppsManager}
     */
    public static synchronized MyAppsManager getInstance(  )
    {
        if ( _singleton == null )
        {
            _singleton = new MyAppsManager(  );
        }

        return _singleton;
    }

    /**
    * Register a new {@link MyAppsProvider}
    *
    * @param myAppsProvider The provider to register
    */
    public void registerProvider( MyAppsProvider myAppsProvider )
    {
        _listRegisteredProviders.put( myAppsProvider.getPluginName(  ), myAppsProvider );
    }

    /**
     * Get the list of providers
     *
     * @return a list of {@link MyAppsProvider}
     */
    public List<MyAppsProvider> getProvidersList(  )
    {
        List<MyAppsProvider> listMyAppsProviders = new ArrayList<MyAppsProvider>(  );

        for ( MyAppsProvider myAppsProvider : _listRegisteredProviders.values(  ) )
        {
            Plugin plugin = PluginService.getPlugin( myAppsProvider.getPluginName(  ) );

            if ( ( plugin != null ) && plugin.isInstalled(  ) )
            {
                listMyAppsProviders.add( myAppsProvider );
            }
            else if ( AppLogService.isDebugEnabled(  ) )
            {
                AppLogService.debug( MESSAGE_PLUGIN_NOT_FOUND + myAppsProvider.getPluginName(  ) );
            }
        }

        return listMyAppsProviders;
    }

    /**
     * Get the provider given its plugin name
     *
     * @param strPluginName the plugin name
     * @return a {@link MyAppsProvider}
     */
    public MyAppsProvider getProvider( String strPluginName )
    {
        return _listRegisteredProviders.get( strPluginName );
    }
}
