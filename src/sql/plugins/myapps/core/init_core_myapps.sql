--
-- Dumping data for table core_admin_right
--

INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url) VALUES ('MYAPPS_MANAGEMENT','myapps.adminFeature.myapps_management.name',1,'jsp/admin/plugins/myapps/ManageMyApps.jsp','myapps.adminFeature.myapps_management.description',0,'myapps','APPLICATIONS',NULL, NULL);

--
-- Dumping data for table core_user_right
--

INSERT INTO core_user_right (id_right,id_user) VALUES ('MYAPPS_MANAGEMENT',1);
INSERT INTO core_user_right (id_right,id_user) VALUES ('MYAPPS_MANAGEMENT',2);
INSERT INTO core_user_right (id_right,id_user) VALUES ('MYAPPS_MANAGEMENT',5);


--
-- Dumping data for table core_portlet_type
--

INSERT INTO core_portlet_type (id_portlet_type,name,url_creation,url_update,home_class,plugin_name,url_docreate,create_script,create_specific,create_specific_form,url_domodify,modify_script,modify_specific,modify_specific_form) VALUES ('MYAPPS_PORTLET','myapps.portlet.name','plugins/myapps/CreatePortletMyApps.jsp','plugins/myapps/ModifyPortletMyApps.jsp','fr.paris.lutece.plugins.myapps.business.portlet.MyAppsPortletHome','myapps','plugins/myapps/DoCreatePortletMyApps.jsp','/admin/portlet/script_create_portlet.html','','','plugins/myapps/DoModifyPortletMyApps.jsp','/admin/portlet/script_modify_portlet.html','','');


--
-- Dumping data for table core_style
--

INSERT INTO core_style (id_style,description_style,id_portlet_type,id_portal_component) VALUES (600,'Default','MYAPPS_PORTLET',0);


--
-- Dumping data for table core_style_mode_stylesheet
--


INSERT INTO core_style_mode_stylesheet (id_style,id_mode,id_stylesheet) VALUES (600,0,314);


--
-- Dumping data for table core_stylesheet
--

INSERT INTO core_stylesheet (id_stylesheet, description, file_name, source) VALUES (314,'Rubrique MyApps - Default','portlet_myapps.xsl',0x3C3F786D6C2076657273696F6E3D22312E30223F3E0D0A3C78736C3A7374796C6573686565742076657273696F6E3D22312E302220786D6C6E733A78736C3D22687474703A2F2F7777772E77332E6F72672F313939392F58534C2F5472616E73666F726D223E0D0A202020200D0A202020200D0A202020203C78736C3A74656D706C617465206D617463683D22706F72746C6574223E0D0A20202020202020203C64697620636C6173733D22706F72746C6574202D6C75746563652D626F726465722D72616469757320617070656E642D626F74746F6D223E0D0A2020202020202020202020203C78736C3A696620746573743D226E6F7428737472696E6728646973706C61792D706F72746C65742D7469746C65293D27312729223E0D0A202020202020202020202020202020203C683320636C6173733D22706F72746C65742D686561646572223E0D0A20202020202020202020202020202020202020203C78736C3A76616C75652D6F662064697361626C652D6F75747075742D6573636170696E673D22796573222073656C6563743D22706F72746C65742D6E616D6522202F3E0D0A202020202020202020202020202020203C2F68333E0D0A2020202020202020202020203C2F78736C3A69663E0D0A2020202020202020202020203C64697620636C6173733D22706F72746C65742D636F6E74656E74223E0D0A202020202020202020202020202020203C7374726F6E673E3C2F7374726F6E673E093C78736C3A6170706C792D74656D706C617465732073656C6563743D226D79617070732D6C69737422202F3E0D0A2020202020202020202020203C2F6469763E0D0A20202020202020203C2F6469763E0D0A202020203C2F78736C3A74656D706C6174653E0D0A202020200D0A202020200D0A202020203C78736C3A74656D706C617465206D617463683D226D79617070732D6C697374223E0D0A20202020202020203C7461626C652063656C6C70616464696E673D2230222063656C6C73706163696E673D223022202077696474683D2231303025223E0D0A2020202020202020202020203C74723E0D0A202020202020202020202020202020203C74643E0D0A20202020202020202020202020202020202020203C7461626C653E0D0A2020202020202020202020202020202020202020202020203C78736C3A6170706C792D74656D706C617465732073656C6563743D226D7961707022202F3E0D0A2020202020202020202020202020202020202020202020203C78736C3A76616C75652D6F662073656C6563743D226D65737361676522202F3E0D0A20202020202020202020202020202020202020203C2F7461626C653E0D0A202020202020202020202020202020203C2F74643E0D0A2020202020202020202020203C2F74723E0D0A2020202020202020202020203C74723E0D0A202020202020202020202020202020203C746420636C6173733D226D79617070732D706F72746C6574223E0D0A20202020202020202020202020202020202020203C666F726D20616374696F6E3D2222206E616D653D22585061676522207461726765743D225F746F70223E0D0A2020202020202020202020202020202020202020202020203C6469763E0D0A202020202020202020202020202020202020202020202020202020203C696E70757420747970653D2268696464656E22206E616D653D2270616765222076616C75653D226D796170707322202F3E0D0A202020202020202020202020202020202020202020202020202020203C78736C3A746578742064697361626C652D6F75747075742D6573636170696E673D22796573223E3C215B43444154415B3C696E70757420747970653D227375626D6974222076616C75653D225D5D3E3C2F78736C3A746578743E3C78736C3A76616C75652D6F662064697361626C652D6F75747075742D6573636170696E673D22796573222073656C6563743D226D796170702D627574746F6E22202F3E0D0A202020202020202020202020202020202020202020202020202020203C78736C3A746578742064697361626C652D6F75747075742D6573636170696E673D22796573223E3C215B43444154415B222F3E5D5D3E3C2F78736C3A746578743E0D0A2020202020202020202020202020202020202020202020203C2F6469763E0D0A20202020202020202020202020202020202020203C2F666F726D3E0D0A202020202020202020202020202020203C2F74643E0D0A2020202020202020202020203C2F74723E0D0A20202020202020203C2F7461626C653E0D0A202020203C2F78736C3A74656D706C6174653E0D0A202020200D0A202020203C78736C3A74656D706C617465206D617463683D226D7961707022203E0D0A20202020202020203C74723E0D0A2020202020202020202020203C74643E0D0A202020202020202020202020202020203C696D67207372633D227B6D796170702D69636F6E7D222077696474683D22333222206865696768743D2233322220616C743D227B6D796170702D6465736372697074696F6E7D222F3E0D0A2020202020202020202020203C2F74643E0D0A2020202020202020202020203C74643E0D0A202020202020202020202020202020203C7374726F6E673E0D0A20202020202020202020202020202020202020203C6120687265663D227B6D796170702D6C696E6B7D26616D703B706C7567696E5F6E616D653D6D796170707322207461726765743D225F626C616E6B223E0D0A2020202020202020202020202020202020202020202020203C78736C3A76616C75652D6F662073656C6563743D226D796170702D6E616D6522202F3E0D0A20202020202020202020202020202020202020203C2F613E0D0A202020202020202020202020202020203C2F7374726F6E673E0D0A202020202020202020202020202020203C6272202F3E0D0A202020202020202020202020202020203C736D616C6C3E0D0A20202020202020202020202020202020202020203C78736C3A76616C75652D6F662073656C6563743D226D796170702D6465736372697074696F6E22202F3E0D0A202020202020202020202020202020203C2F736D616C6C3E0D0A2020202020202020202020203C2F74643E0D0A20202020202020203C2F74723E0D0A202020203C2F78736C3A74656D706C6174653E0D0A202020200D0A3C2F78736C3A7374796C6573686565743E);
