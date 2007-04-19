<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>
<!-- Transforming directory scoped description -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"
	version="1.0"
	xmlns:redirect="http://xml.apache.org/xalan/redirect"
	xmlns:pipe="http://xml.apache.org/xalan/PipeDocument"
    extension-element-prefixes="redirect pipe">
	
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	
	<xsl:include href="file.xslt" />
	
	
	
	<xsl:template match="/*/directory">
		<xsl:variable name="directoryName" select="concat(.,'.xml')" />
		<redirect:write select="concat(@value,'.xml')">
			<directory>
				<xsl:attribute name="xsi:noNamespaceSchemaLocation">
					<xsl:text>DirectoryFile.xsd</xsl:text>
				</xsl:attribute>
				<xsl:attribute name="absolutePath">
					<xsl:text>TEST</xsl:text><!--  TODO Write absolute Path -->
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
							
								
						</file>
						
					</xsl:for-each>

				</fileSet>
				
			</directory>
		</redirect:write>
	</xsl:template>
	

</xsl:stylesheet>