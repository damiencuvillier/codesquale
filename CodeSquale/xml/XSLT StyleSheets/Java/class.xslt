<?xml version="1.0" encoding="ISO-8859-1" standalone="no" ?>

<!-- 
CodeSquale 
2007, April

Damien Cuvillier

XSLT StyleSheet

Antlr to CodeSquale XML transforming
-->
<!-- Transforming class scoped description -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="xml" indent="yes" encoding="ISO-8859-1" />
	<xsl:strip-space elements="*" />
	
	<!-- class root -->
	<xsl:template match="*[@type=6]">
		<attributeSet>
			<xsl:apply-templates select="antlr.CommonAST[@type=10]" />
		</attributeSet>
		<methodSet>
			<xsl:apply-templates select="antlr.CommonAST[@type=9]" />
		</methodSet>
	</xsl:template>
	
	<!--  Attributes -->
	<xsl:template match="antlr.CommonAST[@type=6]/antlr.CommonAST[@type=10]">
		<attribute>
			<xsl:attribute name="name">
				<xsl:value-of select="*/*[@type=68]/@text" />
			</xsl:attribute>
			<xsl:attribute name="type">
				<xsl:value-of select="./*[@type=13]/*/@text" />
			</xsl:attribute>
			<xsl:attribute name="modifier">
				<xsl:value-of select="./*[@type=5]/*/@text" />
			</xsl:attribute>

		</attribute>
	</xsl:template>
	<!-- Attributes -->
	
	<!--  Methods -->
	<xsl:template match="antlr.CommonAST[@type=6]/antlr.CommonAST[@type=9]">
		<method>
			<!-- method name -->
			<xsl:attribute name="name">
				<xsl:value-of select="*[@type=68]/@text" />
			</xsl:attribute>
			<!-- /method name -->
			
			<!-- method return -->
			<xsl:attribute name="return"> 
				<xsl:value-of select="./*[@type=13]/*/@text" />
			</xsl:attribute>
			<!-- /method return -->
			
			<!-- method modifier -->			
			<xsl:attribute name="modifier">
				<xsl:value-of select="./*[@type=5]/*/@text" />
			</xsl:attribute>
			<!-- /method modifier -->
	
			<!-- Variables -->
			<varSet>	
				<xsl:call-template name="variables" />
			</varSet>	
			<!-- /Variables -->
	
		</method>
	</xsl:template>
	<!--  /Methods-->
	
	<xsl:template name="variables" match="antlr.CommonAST[@type=10]">
	
		
		<var>

		
			<!--  var name -->
			<xsl:attribute name="name">
				<xsl:value-of select="./*[@type=68]/@text" />
			</xsl:attribute>
			<!-- /var name -->
			
			<!-- var type -->
			<xsl:attribute name="type"> 
				<xsl:value-of select="./*[@type=13]/*/@text" />
			</xsl:attribute>
			<!-- /var type -->
			
			<!-- var modifier -->			
			<xsl:attribute name="modifier">
				<xsl:value-of select="./*[@type=5]/*/@text" />
			</xsl:attribute>
			<!-- /var modifier -->
		</var>
		<xsl:apply-templates  /> 	
	</xsl:template>
	

	
</xsl:stylesheet>