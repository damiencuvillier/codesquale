xquery version "1.0";

let $classCount := count(//directory/fileSet/file/classSet/class)
let $methodCount := count(//directory/fileSet/file/classSet/class/methodSet/method)
let $attributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute)
let $interfaceCount := count(//directory/fileSet/file/classSet/class/implementedInterfaceSet/interface)
let $classes := //directory/fileSet/file/classSet/class

return
	<resultFile>
		<globalCounters>
		    <classCount value="{$classCount}" />
			<methodCount value="{$methodCount}" />
			<attributeCount value="{$attributeCount}" />
			<interfaceCount value="{$interfaceCount}" />
		</globalCounters>

		<classResults>
		{
			for $x in $classes
				return
					<class name="{ $x/@name }">
						<methodCount value="{count($x/methodSet/method)}" />
						<attributeCount value="{count($x/attributeSet/attribute)}" />
						<interfaceCount value="{count($x/implementedInterfaceSet/interface)}" />
					</class>
		}
		</classResults>
	</resultFile>