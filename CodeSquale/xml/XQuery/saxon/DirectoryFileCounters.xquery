xquery version "1.0";

(: counting classes :)
let $classCount := count(//directory/fileSet/file/classSet/class)
let $privateClassCount := count(//directory/fileSet/file/classSet/class[@modifier="private"])
let $publicClassCount := count(//directory/fileSet/file/classSet/class[@modifier="public"])

(: counting method :)
let $methodCount := count(//directory/fileSet/file/classSet/class/methodSet/method)
let $privateMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method[@modifier="private"])
let $publicMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method[@modifier="public"])


let $attributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute)
let $privateAttributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute[@modifier="private"])
let $publicAttributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute[@modifier="public"])

(: others counters :)
let $interfaceCount := count(//directory/fileSet/file/classSet/class/implementedInterfaceSet/interface)
let $classes := //directory/fileSet/file/classSet/class

return
<directoryResults packageName="{//directory/@completeName}" absolutePath="{//directory/@absolutePath}">
			<packageCounters>
				<classes>
				    <all>{$classCount}</all>
				    <private>{$privateClassCount}</private>
				    <public>{$publicClassCount}</public>
				</classes>
				<methods>
					<all>{$methodCount}</all>
					<private>{$privateMethodCount}</private>
				    <public>{$publicMethodCount}</public>
				</methods>
				<attributes>
					<all>{$attributeCount}</all>
					<private>{$privateAttributeCount}</private>
					<public>{$publicAttributeCount}</public>
				</attributes>
				<interfaces>{$interfaceCount}</interfaces>
			</packageCounters>
	{
	if($classCount != 0) 
		then 		
			<packageRatios>
				<averageMethodNumberPerClass>
					<all>{$methodCount div $classCount}</all>
					<private>{$privateMethodCount div $classCount}</private>
					<public>{$publicMethodCount div $classCount}</public>
				</averageMethodNumberPerClass>
				<averageAttributeNumberPerClass>
					<all>{$attributeCount div $classCount}</all>
					<private>{$privateAttributeCount div $classCount}</private>
					<public>{$publicAttributeCount div $classCount}</public>
				</averageAttributeNumberPerClass>
			</packageRatios>
		else ()
	}

	<classesResultCounters>
	{
		for $x in $classes
			return
				<classResultCounters className="{$x/@name}">
					<methodCount>
						<all>{count($x/methodSet/method)}</all>
						<public>{count($x/methodSet/method[@modifier="private"])}</public>
						<private>{count($x/methodSet/method[@modifier="public"])}</private>
					</methodCount>
					<attributeCount>
						<all>{count($x/attributeSet/attribute)}</all>
						<private>{count($x/attributeSet/attribute[@modifier="private"])}</private>
						<public>{count($x/attributeSet/attribute[@modifier="public"])}</public>
					</attributeCount>
					<interfaceCount>{count($x/implementedInterfaceSet/interface)}</interfaceCount>
				</classResultCounters>
	}
	</classesResultCounters>
	
</directoryResults>