if( count(//directory/fileSet/file/classSet/class) != 0) 
		then
			let $classCount := count(//directory/fileSet/file/classSet/class)
			let $otherattributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute/modifierSet/visibility[@value!="public"])
			return $otherattributeCount div $classCount
		else ()