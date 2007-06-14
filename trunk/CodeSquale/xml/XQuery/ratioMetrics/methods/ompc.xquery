if( count(//directory/fileSet/file/classSet/class) != 0) 
		then
			let $classCount := count(//directory/fileSet/file/classSet/class)
			let $otherMethodCount := count(//directory/fileSet/file/classSet/class/methodSet/method[@modifier!="public"])
			return $otherMethodCount div $classCount
		else ()