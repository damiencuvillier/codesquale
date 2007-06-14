if( count(//directory/fileSet/file/classSet/class) != 0) 
		then
			let $classCount := count(//directory/fileSet/file/classSet/class)
			let $methodCount := count(//directory/fileSet/file/classSet/class/methodSet/method) 
			return $methodCount div $classCount
		else ()