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
				<xsl:value-of select="./*[@type=68]/@text" />
			</xsl:attribute>
			<xsl:attribute name="type">
				<xsl:value-of select="./*[@type=13]/*/@text" />
			</xsl:attribute>
			
			<modifierSet>
				<xsl:for-each select="./*[@type=5]/*">
				
				  <xsl:if test="@type = '88' or @type='89' or @type='90'">
				      <visibility>
							<xsl:attribute name="value">
								<xsl:value-of select="@text" />
							</xsl:attribute>
						</visibility>
				   </xsl:if>
		  		   
		  		   <xsl:if test="@type = ''">
				    <visibility>
							<xsl:attribute name="value">
								<xsl:text>default</xsl:text>
							</xsl:attribute> 
					</visibility>	
					</xsl:if>
	
	
	
			
				<xsl:if test="@type = '65' or @type='91' or @type='92' or @type='93' or @type='94'  or @type='95'">	
					<modifier>
						<xsl:attribute name="value">
							<xsl:value-of select="./@text" />
						</xsl:attribute>
					</modifier>
				</xsl:if>
				
					
				</xsl:for-each>
			</modifierSet>

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
	
			<!-- Parameters --> 
			<paramSet>
				 <xsl:for-each select="./*[@type=20]/*[@type=21]">
						<param>
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
						</param>
				 </xsl:for-each>
			</paramSet>
			<!-- /Parameters -->
		
			
			
			<!-- Variables -->
			<varSet>	
				<xsl:call-template name="methodVars" />
			</varSet>	
			<!-- /Variables -->
	
		</method>
	</xsl:template>
	<!--  /Methods-->
	
	<!-- MethodVars -->
	<xsl:template name="methodVars" match="antlr.CommonAST[@type=7]/antlr.CommonAST[@type=10]">		
		<var>
		<!-- recursive -->
		<xsl:apply-templates /> 
			<xsl:if test="@type = '10'">
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
			</xsl:if>
			<!-- /var modifier -->	
		</var>
	</xsl:template>
	
</xsl:stylesheet>