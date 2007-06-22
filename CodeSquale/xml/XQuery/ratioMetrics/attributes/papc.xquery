if( count(//directory/fileSet/file/classSet/class) != 0) 
		then
			let $classCount := count(//directory/fileSet/file/classSet/class)
			let $publicattributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute/modifierSet/visibility[@value="public"])
			return $publicattributeCount div $classCount
		else ()