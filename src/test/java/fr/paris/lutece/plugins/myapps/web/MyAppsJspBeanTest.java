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
package fr.paris.lutece.plugins.myapps.web;

import fr.paris.lutece.plugins.myapps.business.parameter.MyAppsParameterHome;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.business.user.AdminUserHome;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.web.dashboard.AdminDashboardJspBean;
import fr.paris.lutece.test.LuteceTestCase;
import fr.paris.lutece.test.MokeHttpServletRequest;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.ReferenceList;


/**
 *
 * MyAppsJspBeanTest
 *
 */
public class MyAppsJspBeanTest extends LuteceTestCase
{
    private final Plugin _plugin = PluginService.getPlugin( "myapps" );
    private final String PARAMETERVALUE = "ParameterValue";

    /**
     * Test of doModifyMyAppsParameterDefaultValues method of class fr.paris.lutece.plugins.myapps.web.MyAppsJspBean
     * @throws AccessDeniedException if the user has not the right
     */
    public void testDoModifyMyAppsParameterDefaultValues(  )
        throws AccessDeniedException
    {
        System.out.println( "doModifyMyAppsParameterDefaultValues" );

        ReferenceList listParameters = MyAppsParameterHome.findAll( _plugin );

        if ( !listParameters.isEmpty(  ) )
        {
            MokeHttpServletRequest request = new MokeHttpServletRequest(  );

            for ( int i = 0; i < listParameters.size(  ); i++ )
            {
                ReferenceItem parameter = listParameters.get( i );
                request.addMokeParameters( parameter.getCode(  ), PARAMETERVALUE + ( i + 1 ) );
            }

            AdminUser user = AdminUserHome.findUserByLogin( "admin" );
            user.setRoles( AdminUserHome.getRolesListForUser( user.getUserId(  ) ) );
            request.registerAdminUserWithRigth( user, AdminDashboardJspBean.RIGHT_MANAGE_ADMINDASHBOARD );

            MyAppsJspBean instance = new MyAppsJspBean(  );
            instance.init( request, AdminDashboardJspBean.RIGHT_MANAGE_ADMINDASHBOARD );

            String result = instance.doModifyMyAppsParameterDefaultValues( request );

            assertNotNull( result );
        }
    }
}
