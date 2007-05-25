xquery version "1.0";

(: counting classes :)
let $classCount := count(//directory/fileSet/file/classSet/class)
let $otherClassCount := count(//directory/fileSet/file/classSet/class[@modifier!="public"])
let $publicClassCount := count(//directory/fileSet/file/classSet/class[@modifier="public"])

(: counting method :)
let $methodCount := count(//directory/fileSet/file/classSet/class/methodSet/method)
let $otherMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method[@modifier!="public"])
let $publicMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method[@modifier="public"])


let $attributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute)
let $otherAttributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute[@modifier!="public"])
let $publicAttributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute[@modifier="public"])

(: others counters :)
let $interfaceCount := count(//directory/fileSet/file/classSet/class/implementedInterfaceSet/interface)
(: iterators :)
let $files := //directory/fileSet/file
let $classes := //directory/fileSet/file/classSet/class

return
<directoryResults packageName="{//directory/@completeName}" absolutePath="{//directory/@absolutePath}">

<packageAnalysis>
	<packageGlobalMetrics>
		<classes>
			<all>{$classCount}</all>
			<public>{$publicClassCount}</public>
			<others>{$otherClassCount}</others>
		</classes>
		<methods>
			<all>{$methodCount}</all>
			<public>{$publicMethodCount}</public>
			<others>{$otherMethodCount}</others>
		</methods>
		<attributes>
			<all>{$attributeCount}</all>
			<public>{$publicAttributeCount}</public>
			<others>{$otherAttributeCount}</others>
		</attributes>
		<interfaces>{$interfaceCount}</interfaces>
	</packageGlobalMetrics>
	
	{
	if($classCount != 0) 
		then 		
			<packageRatios>
				<averageMethodNumberPerClass>
					<all>{$methodCount div $classCount}</all>
					<public>{$publicMethodCount div $classCount}</public>
					<others>{$otherMethodCount div $classCount}</others>
				</averageMethodNumberPerClass>
				<averageAttributeNumberPerClass>
					<all>{$attributeCount div $classCount}</all>
					<public>{$publicAttributeCount div $classCount}</public>
					<others>{$otherAttributeCount div $classCount}</others>
				</averageAttributeNumberPerClass>
			</packageRatios>
		else ()
	}

</packageAnalysis>

	<fileSet>
	{
		for $file in $files
			return
				<file name="{$file/@name}">
					<globalMetrics>
						<lineOfCode>
							<toli>{data($file/@toli)}</toli>
							<ploc>{data($file/@ploc)}</ploc>
							<blli>{data($file/@blli)}</blli>
						</lineOfCode>
					</globalMetrics>
					<attributes>
						<size>{data($file/@filesize)} kB</size>
						<lastmodified>{data($file/@lastmodified)}</lastmodified>
					</attributes>
					<classSet>
					{
						for $x in //directory/fileSet/file[@name=data($file/@name)]/classSet/class
							return
								<class name="{$x/@name}">
									<methodCount>
										<all>{count($x/methodSet/method)}</all>
										<public>{count($x/methodSet/method[@modifier="public"])}</public>
										<others>{count($x/methodSet/method[@modifier!="public"])}</others>
									</methodCount>
									<attributeCount>
										<all>{count($x/attributeSet/attribute)}</all>
										<public>{count($x/attributeSet/attribute[@modifier="public"])}</public>
										<others>{count($x/attributeSet/attribute[@modifier!="public"])}</others>
									</attributeCount>
									<implementedInterfaceCount>{count($x/implementedInterfaceSet/interface)}</implementedInterfaceCount>
								</class>

					}
					</classSet>
				</file>
	}
	</fileSet>

</directoryResults>