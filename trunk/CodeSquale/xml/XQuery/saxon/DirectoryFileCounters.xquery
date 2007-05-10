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
	<directoryResult name="{//directory/@name}" absolutePath="{//directory/@absolutePath}">
		<counters>
			<classes>
			    <all value="{$classCount}" />
			    <private value="{$privateClassCount}" />
			    <public value="{$publicClassCount}" />
			</classes>
			
			<methods>
				<all value="{$methodCount}" />
				<private value="{$privateMethodCount}" />
			    <public value="{$publicMethodCount}" />
			</methods>
			
			<attributes>
				<all value="{$attributeCount}" />
				<private value="{$privateAttributeCount}" />
				<public value="{$publicAttributeCount}" />
			</attributes>
			
			<interface value="{$interfaceCount}" />
		</counters>

		<ratios>
			<averageMethodNumberPerClass>
				<all value="{$methodCount div $classCount}" />
				<private value="{$privateMethodCount div $classCount}" />
				<public value="{$publicMethodCount div $classCount}" />
			</averageMethodNumberPerClass>
			<averageAttributeNumberPerClass>
				<all value="{$attributeCount div $classCount}" />
				<private value="{$privateAttributeCount div $classCount}" />
				<public value="{$publicAttributeCount div $classCount}" />
			</averageAttributeNumberPerClass>
		</ratios>
		
		<classCounter>
		{
			for $x in $classes
				return
					<class name="{$x/@name}">
						<methods>
							<all value="{count($x/methodSet/method)}" />
							<public value="{count($x/methodSet/method[@modifier="private"])}" />
							<private value="{count($x/methodSet/method[@modifier="public"])}" />
						</methods>
						<attributes>
							<all value="{count($x/attributeSet/attribute)}" />
							<private value="{count($x/attributeSet/attribute[@modifier="private"])}" />
							<public value="{count($x/attributeSet/attribute[@modifier="public"])}" />
						</attributes>
						<interfaceCount value="{count($x/implementedInterfaceSet/interface)}" />
					</class>
		}
		</classCounter>
	</directoryResult>