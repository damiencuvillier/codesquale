if( count(//directory/fileSet/file/classSet/class) != 0) 
		then
			let $classCount := count(//directory/fileSet/file/classSet/class)
			let $publicMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method[@modifier="public"])
			return $publicMethodCount div $classCount
		else ()