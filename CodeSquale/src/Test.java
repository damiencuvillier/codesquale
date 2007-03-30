
import java.io.File;

import com.codesquale.exceptions.NotDirectoryException;
import com.codesquale.file.FileFilter;
import com.codesquale.file.ProjectBrowser;

/**
 * Test Class for project
 * 
 * Launch Repository Browser Which calls the parsing process
 * @author DCUVILLIER
 *
 */
public class Test 
{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Test.class);
	
public static void main(String[] args) 
{
		logger.info("Opening Test...");

		
	
		
		
		if(args.length < 2){
			System.err.println("Check your syntax. Right Syntax is : \n\t first arg: absolute path\n\t second arg : output file");
			return;
		}
		try 
		{
			logger.info("File Filter init");
			FileFilter filter = new FileFilter();
			filter.addFileType(FileFilter.JAVA_SOURCEFILE);
			logger.info("Browsing File...");
			ProjectBrowser browser = new ProjectBrowser(new File(args[0]),new File(args[1]),filter);
			
			
		} catch (NotDirectoryException e) {
			logger.fatal("Param is not a valid directory");
		}
		logger.info("Done...");
		logger.info("Results written in "+args[1]);
	}
}
