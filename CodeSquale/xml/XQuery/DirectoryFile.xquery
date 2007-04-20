declare function local:build-global-counter($classCount, $methodCount, $attributeCount, $interfaceCount) 
{
	<globalCounters>
	    <classCount>{$classCount}</classCount>
		<methodCount>{$methodCount}</methodCount>
		<attributeCount>{$attributeCount}</attributeCount>
		<interfaceCount>{$interfaceCount}</interfaceCount>
	</globalCounters>
};

declare function local:build-class-counter($classes) 
{
for $x in $classes
return
	<class name="{$x/@name}">
		<methodCount value="{count($x/methodSet/method)}" />
		<attributeCount value="{count($x/attributeSet/attribute)}" />
		<interfaceCount value="{count($x/implementedInterfaceSet/interface)}" />
	</class>
};


declare function local:build-result-page($classes, $globalClassCount, $globalMethodCount, $globalAttributeCount, $globalInterfaceCount) 
{
	<resultFile>
	    {local:build-global-counter($globalClassCount, $globalMethodCount, $globalAttributeCount, $globalInterfaceCount)}
		<classResults> 
			{local:build-class-counter($classes)}
		</classResults>
		
	</resultFile>
};


declare function local:getResultsFromFile($filePath)
{
	(: Loading the document :)
	let $doc := doc($filePath)
	
	(: Preparing the counter :)
	let $classCount := count($doc//directory/fileSet/file/classSet/class)
	let $methodCount := count($doc//directory/fileSet/file/classSet/class/methodSet/method)
	let $attributeCount := count($doc//directory/fileSet/file/classSet/class/attributeSet/attribute)
	let $interfaceCount := count($doc//directory/fileSet/file/classSet/class/implementedInterfaceSet/interface)
	
	(: Selecting all classes :)
	let $classes := $doc//directory/fileSet/file/classSet/class
	
	
	return 
		local:build-result-page($classes, $classCount, $methodCount, $attributeCount, $interfaceCount)

};




 








