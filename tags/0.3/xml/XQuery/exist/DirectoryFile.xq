xquery version "1.0";

declare function local:getResultsFromFile($filePath as xs:string)
{
	(: Loading the document :)
	let $doc := xcollection($filePath)
	
	(: Preparing the counter :)
	let $classCount := count($doc//directory/fileSet/file/classSet/class)
	let $methodCount := count($doc//directory/fileSet/file/classSet/class/methodSet/method)
	let $attributeCount := count($doc//directory/fileSet/file/classSet/class/attributeSet/attribute)
	let $interfaceCount := count($doc//directory/fileSet/file/classSet/class/implementedInterfaceSet/interface)
	
	(: Selecting all classes :)
	let $files := $doc//directory/fileSet/file
	let $classes := $doc//directory/fileSet/file/classSet/class
	
	
	return 
	
	<resultFile>
	
	    <globalCounters>
		    <classCount value="{$classCount}" />
			<methodCount value="{$methodCount}" />
			<attributeCount value="{$attributeCount}" />
			<interfaceCount value="{$interfaceCount}" />
		</globalCounters>
		
		<fileResults>
		{
		 	for $file in $files
				return
				<file name="{$file/@name}">
				{
					for $x in $file/classSet/class
						return
						<class name="{$x/@name}">
							<methodCount value="{count($x/methodSet/method)}" />
							<attributeCount value="{count($x/attributeSet/attribute)}" />
							<interfaceCount value="{count($x/implementedInterfaceSet/interface)}" />
						</class>

				}
				</file>
		}
		</fileResults>
		
	</resultFile>

};


local:getResultsFromFile("/db")









