xquery version "1.0";

(: counting number of classes in package :)
let $packageClassCount := count(//directory/fileSet/file/classSet/class)
let $packageOtherClassCount := count(//directory/fileSet/file/classSet/class[@modifier!="public"])
let $packagePublicClassCount := count(//directory/fileSet/file/classSet/class[@modifier="public"])

(: counting number of method in package :)
let $packageMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method)
let $packageOtherMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method[@modifier!="public"])
let $packagePublicMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method[@modifier="public"])

(: counting number of attributes in package :)
let $packageAttributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute)
let $packageOtherAttributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute[@modifier!="public"])
let $packagePublicAttributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute[@modifier="public"])

(: counting number of interfaces in package :)
let $packageInterfaceCount := count(//directory/fileSet/file/classSet/class/implementedInterfaceSet/interface)

(: counting number of files in package :)
let $packageFileCount := count(//directory/fileSet/file)

(: counting package lines of code :)
let $packageToli := sum(data(//directory/fileSet/file/@toli))
let $packagePloc := sum(data(//directory/fileSet/file/@ploc))
let $packageBlli := sum(data(//directory/fileSet/file/@blli))

(: package total size :)
let $packageSize := sum(data(//directory/fileSet/file/@filesize))

(: iterators :)
let $files := //directory/fileSet/file


return
<directoryResults packageName="{//directory/@completeName}" absolutePath="{//directory/@absolutePath}">

<packageAnalysis>
	<packageGlobalMetrics>
		<files>{$packageFileCount}</files>
		<totalSize unit="kB">{$packageSize}</totalSize>
		<linesOfCode>
			<toli>{$packageToli}</toli>
			<ploc>{$packagePloc}</ploc>
			<blli>{$packageBlli}</blli>
		</linesOfCode>
		<classes>
			<all>{$packageClassCount}</all>
			<public>{$packagePublicClassCount}</public>
			<others>{$packageOtherClassCount}</others>
		</classes>
		<methods>
			<all>{$packageMethodCount}</all>
			<public>{$packagePublicMethodCount}</public>
			<others>{$packageOtherMethodCount}</others>
		</methods>
		<attributes>
			<all>{$packageAttributeCount}</all>
			<public>{$packagePublicAttributeCount}</public>
			<others>{$packageOtherAttributeCount}</others>
		</attributes>
		<implementedInterfaces>{$packageInterfaceCount}</implementedInterfaces>
	</packageGlobalMetrics>
	
	{
	if($packageClassCount != 0) 
		then 		
			<packageRatios>
				<averageMethodNumberPerClass>
					<all>{$packageMethodCount div $packageClassCount}</all>
					<public>{$packagePublicMethodCount div $packageClassCount}</public>
					<others>{$packageOtherMethodCount div $packageClassCount}</others>
				</averageMethodNumberPerClass>
				<averageAttributeNumberPerClass>
					<all>{$packageAttributeCount div $packageClassCount}</all>
					<public>{$packagePublicAttributeCount div $packageClassCount}</public>
					<others>{$packageOtherAttributeCount div $packageClassCount}</others>
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
						<lastmodified>{data($file/@lastmodified)}</lastmodified>
					</attributes>
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
										<public>{count($class/attributeSet/attribute[@modifier="public"])}</public>
										<others>{count($class/attributeSet/attribute[@modifier!="public"])}</others>
									</attributeCount>
									<implementedInterfaceCount>{count($class/implementedInterfaceSet/interface)}</implementedInterfaceCount>
								</class>

					}
					</classSet>
				</file>
	}
	</fileSet>

</directoryResults>