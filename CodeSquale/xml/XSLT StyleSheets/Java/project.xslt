<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>
<!-- Transforming project scoped description -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"
	version="2.0">
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	
	
	<xsl:template match="root">
		<project name="test" language="java" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 	xsi:noNamespaceSchemaLocation="../projectFileSchema.xsd">
			<xsl:attribute name="xsi:noNamespaceSchemaLocation">
				<xsl:text>ProjectFile.xsd</xsl:text>
			</xsl:attribute>
		<!--  TODO AbsolutePath -->
			<dir>
				<xsl:attribute name="name">
					<xsl:value-of select="@path" />
				</xsl:attribute>
				<xsl:attribute name="descriptionFile">
					<xsl:value-of select="concat(@path,'.xml')" />
					
				</xsl:attribute>
			</dir>
		</project>

	</xsl:template>
	
</xsl:stylesheet>