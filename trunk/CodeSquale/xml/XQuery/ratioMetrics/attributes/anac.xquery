if( count(//directory/fileSet/file/classSet/class) != 0) 
		then
			let $classCount := count(//directory/fileSet/file/classSet/class)
			let $attributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute) 
			return $attributeCount div $classCount
		else ()