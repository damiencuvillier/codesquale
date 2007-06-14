<fileSet> {
for $file in //directory/fileSet/file
	return
		<file name="{$file/@name}">
			<globalMetrics>
				<lineOfCode>
					<toli>{data($file/@toli)}</toli>
					<ploc>{data($file/@ploc)}</ploc>
					<blli>{data($file/@blli)}</blli>
				 </lineOfCode>
			</globalMetrics>
			<fileProperties>
				<size unit="kB">{data($file/@filesize)}</size>
				<lastmodified>{data($file/@lastmodified)}</lastmodified>
			</fileProperties>
			<classSet>
			{
				for $class in //directory/fileSet/file[@name=data($file/@name)]/classSet/class
				return
						<class name="{$class/@name}">
							<methodCount>
								<all>{count($class/methodSet/method)}</all>
								<public>{count($class/methodSet/method[@modifier="public"])}</public>
								<others>{count($class/methodSet/method[@modifier!="public"])}</others>
							</methodCount>
							<attributeCount>
								<all>{count($class/attributeSet/attribute)}</all>
								<public>{count($class/attributeSet/attribute/modifierSet/visibility[@value="public"])}</public>
								<others>{count($class/attributeSet/attribute/modifierSet/visibility[@value!="public"])}</others>
							</attributeCount>
							<implementedInterfaceCount>{count($class/implementedInterfaceSet/interface)}</implementedInterfaceCount>
						</class>
			}
			</classSet>
		</file>
}</fileSet>