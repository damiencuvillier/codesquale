<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>
<!-- 
CodeSquale 
2007, April

Damien Cuvillier

XSLT StyleSheet

Antlr to CodeSquale XML transforming
-->
<!-- Transforming project scoped description -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"
	version="2.0">
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	
	
	

	<!--  Project Description -->
	<xsl:template match="/root">
		<project language="java" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 	
			xsi:noNamespaceSchemaLocation="../projectFileSchema.xsd">
			<!-- TODO put language in parameter -->
			<xsl:attribute name="xsi:noNamespaceSchemaLocation">
				<xsl:text>ProjectFile.xsd</xsl:text>
			</xsl:attribute>
			<!-- TODO AbsolutePath -->
		
		
		<!-- Dir Node : Defines the source dir for the project -->
			<dir>
			<!-- Dir node has two attributes :
				- absolutePath : absolute path of the sourcedir
				- name = relative path = name of the path
				- descriptionFile
			 -->
			 	<xsl:attribute name="name">
					<xsl:value-of select="directory/@value" />
				</xsl:attribute>
				<xsl:attribute name="absolutePath">
					<xsl:value-of select="directory/@href" />
				</xsl:attribute>
				<xsl:attribute name="descriptionFile">
					<xsl:value-of select="concat(directory/@value,'.xml')" />
				</xsl:attribute>
			</dir>
		</project>

	</xsl:template>
	
</xsl:stylesheet>