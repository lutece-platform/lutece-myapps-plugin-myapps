<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="portlet">
        <div class="portlet -lutece-border-radius append-bottom">
            <xsl:if test="not(string(display-portlet-title)='1')">
                <h3 class="portlet-header">
                    <xsl:value-of disable-output-escaping="yes" select="portlet-name" />
                </h3>
            </xsl:if>
            <div class="portlet-content">
                <xsl:apply-templates select="myapps-providers-list" />
            </div>
        </div>
    </xsl:template>

    <xsl:template match="myapps-providers-list">
    	<xsl:apply-templates select="myapps-provider" />
    	<div class="clear"></div>
    </xsl:template>

    <xsl:template match="myapps-provider">
    	<div class="myapps-content left">
    		<h3><xsl:value-of disable-output-escaping="yes" select="myapps-provider-name" /></h3>
    		<xsl:apply-templates select="myapps-list" />
    	</div>
    </xsl:template>

    <xsl:template match="myapps-list">
    	<ul>
    		<xsl:apply-templates select="myapp" />
    	</ul>
    </xsl:template>

    <xsl:template match="myapp" >
    	<li>
    		<img src="{myapp-icon}" width="32" height="32" alt="{myapp-description}"/>
    		<a href="{myapp-link}&amp;plugin_name=myapps" target="_blank">
				<b><xsl:value-of select="myapp-name" /></b>
			</a>
			<small>
				<xsl:value-of select="myapp-description" />
			</small>
    	</li>
    </xsl:template>

</xsl:stylesheet>