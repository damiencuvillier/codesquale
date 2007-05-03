  ###########################################################################
  #                                                                         # 
  #  CODESQUALE : An open source tool for measuring the software quality    #
  #                                                                         #
  #      		www.codesquale.googlepages.com                      #
  #                                                                         #
  ###########################################################################


                            ---------------------------------
                           | INFORMATIONS ABOUT THIS RELEASE |
                            ---------------------------------
                           |     Release date : 27-04-2007   |
                           | Production State : Alpha        |
                           |          Version : 0.2          |         
                            ---------------------------------
* Requirement
-----------------------------------------------------------------------------
	- Java 1.5 installed on your computer
	- Some java sources files :)


* What is CodeSquale ?
-----------------------------------------------------------------------------

CodeSquale is an open source project initied by the company Ajilon IT (based in Villeneuve d'Ascq, North, France).
CodeSquale aims to provide software metrics (explained in metrics.pdf) about java source code file. 
These metrics are indicator of the code quality and are calculated from a detailled analyze of the code.


* What CodeSquale does ?
-----------------------------------------------------------------------------

1/ Browses a set of source files
 	-> Exploring a directory
        -> Filtering .java file
        -> Constructing files project tree files


2/ Parses the set of java source files
	-> Create some abstract xml file that describes the project's classes
	-> Analyse the project package hierarchie

3/ Format results
	-> You've 3 types of output directories : 
			AntlrXML : contains the first parsing result under Antlr format
			CodeSqualeXML: the xslt transformation result xml files
			MetricsXML: contains all the metrics results. For each directory (package for java) a xml file is generated
						which includes all classes metrics for the directory


* What indicators and metrics are calculated ?
-----------------------------------------------------------------------------

- Indicators :
	- Line count
	- File count
	- Class count
	- Method Count
	- interface

- Metrics :
	- Average number of methods by class


* Why you need CodeSquale ?
-----------------------------------------------------------------------------

1/ Controling the code source quality
2/ Identifying a development problem
3/ Improving your programing skills



* How to join us ?
-----------------------------------------------------------------------------
Send a email to damien.cuvillier@gmail.com. 
Describe your motivation and we'll reply you quickly.
Thanks for you consideration.


For futher informations about this project, visit this URL :  www.codesquale.googlepages.com


Best regards.

CodeSquale team.

                             ,_.                                              
                           ./  |                                          _-
                         ./    |                                       _-'/
      ______.,         ./      /                                     .'  (
 _---'___._.  '----___/       (                                    ./  /`'
(,----,_  G \                  \_.                               ./   :
 \___   "--_                      "--._,                       ./    /
  /^^^^^-__          ,,,,,               "-._       /|         /     /
 `,       -        /////                    "`--__/ (_,    ,_/    ./
   "-_,           ''''' __,                            `--'      /
       "-_,             \\ `-_                                  (
           "-_.          \\   \.                                 \_
          /    "--__,      \\   \.                       ____.     "-._,
         /        ./ `---____\\   \.______________,---\ (     \,        "-.,
        |       ./             \\   \        /\  |     \|       `--______---`
        |     ./                 \\  \      /_/\_!
        |   ./                     \\ \
        |  /                         \_\
        |_/                                   




