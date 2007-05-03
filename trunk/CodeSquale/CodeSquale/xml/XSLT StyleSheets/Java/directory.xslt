<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>
<!-- 
CodeSquale 
2007, April

Damien Cuvillier

XSLT StyleSheet

Antlr to CodeSquale XML transforming
-->
<!-- Transforming directory scoped description -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"
	version="1.0"
	xmlns:redirect="http://xml.apache.org/xalan/redirect"
    extension-element-prefixes="redirect">
	
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	
	<xsl:include href="file.xslt" />
	
	
	
	<xsl:template match="//directory">
		<xsl:message>Analyse directory <xsl:value-of select="@value" /></xsl:message>
		<redirect:write select="concat(@value,'.xml')">
			<directory>
				<xsl:attribute name="xsi:noNamespaceSchemaLocation">
					<xsl:text>DirectoryFile.xsd</xsl:text>
				</xsl:attribute>
				<xsl:attribute name="absolutePath">
					<xsl:text>@value</xsl:text><!--  TODO Write absolute Path -->
				</xsl:attribute>
				
				<xsl:attribute name="name">
					 <xsl:value-of select="@value" />
				</xsl:attribute>
				
				<directorySet>
					 <xsl:for-each select="directory">
					 	<dir>
					 		<xsl:attribute name="name">
					 			<xsl:value-of select="@value" />
					 		</xsl:attribute>
					 		<xsl:attribute name="descriptionFile">
					 			<xsl:value-of select="concat(@value,'.xml')" />
					 		</xsl:attribute>
					 	</dir>
					 </xsl:for-each>
				</directorySet>
				<fileSet>
					<xsl:for-each select="file">
				<file>
							<xsl:attribute name="name">
								<xsl:value-of select="@value" />
							</xsl:attribute>
							<xsl:variable name="documentName" select="@href" />
							<xsl:apply-templates select="document($documentName)/*" />
						</file>
					</xsl:for-each>
				</fileSet>
				
			</directory>
			<xsl:apply-templates select="directory" />
		</redirect:write>
	</xsl:template>
	

</xsl:stylesheet>