##  ##
# Counting metrics #
##  ##

## 1. General metrics ##
| **CS Abbr.** | **Other Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:----------------|:------------|:----------------|:----------------------------|
|**NPAC**      | Packages        |Number of packages.|Total number of distinct packages in the project. Classes that are not defined in a package belong to the _default package_.|_N/A_                        |
|**NFIL**      | Files           |Number of source files.|The total number of sources files included in the project.|_N/A_                        |
|**NINT**      | Interfaces      |Number of interfaces.|Total number of interfaces(**_public_** and **_private_**).|_Yes_                        |

## 2. Class metrics ##
| **CS Abbr.** | **Other Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:----------------|:------------|:----------------|:----------------------------|
|**NTLA**      | Classes         |Number of classes.|Total number of classes.|_Yes_                        |
|**NPCL**      | Public Classes  |Number of _**public class**_.|Total number of **_public class_** (suffixed by the keyword **_public_**).|_Yes_                        |
|**NPPC**      | Private Classes |Number of _**private class**_.|Total number of **_private class_** (suffixed by the keyword **_private_**).|_Yes_                        |
|**NFCL**      | Final Classes   |Number of _**final class**_.|Total number of **_final class_** (suffixed by the keyword **_final_**).|_N/A_                        |
|**NACL**      | Ac              |Number of _**abstract class**_.|Total number of **_abstract class_** (suffixed by the keyword **_abstract_**).|_N/A_                        |
|**NCCL**      | Cc              |Numer of _**concrete class**_.|Total number of **_concrete class_** (all classes which are not **_abstract_**).|_N/A_                        |
|**NLCL**      | NLC             |Number of _**local class**_.|Total number of **_local class_** (suffixed by the keyword **_private_** or not suffixed by keyword).|_N/A_                        |
|**NLSC**      |                 |Number of _**local and static class**_.|Total number of **_local_ and static class** (suffixed by the keyword **_private static_** or **_static_**).|_N/A_                        |
|**NNCL**      | Nested Classes  |Number of **_nested class_**.|Total number  of **_nested class_** (all classes nested by another class).|N/A|
|**NTCL**      |                 |Number of _**child class**_.|Total number  of **_child class_** (all classes which derived directly from another class).|_N/A_                        |
|**NCII**      | Interfaces implemented |Number of class implementing at least one interface.|This measure provides a count of the total number classes implementing an interface or more.|_N/A_                        |

## 3. Method metrics ##
| **CS Abbr.** | **Other Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:----------------|:------------|:----------------|:----------------------------|
|**NOME**      | Methods         |Number of methods.|Total number  of methods in the project (without counting the class constructors).| Yes                         |
|**NONM**      | Native Methods  |Number of native methods.|A native method is a method implemented in a “native” language, usually C or C++. This measure provides a count of the total number of native methods in all the classes.|_N/A_                        |
|**NOPM**      | Public Methods  |Number of **_public_** methods.|Count all the **_public_** methods.|_Yes_                        |
|**NPRM**      |                 |Number of **_private_** methods.|Count all the **_private_** methods.|_Yes_                        |
|**NORM**      | NRM             |Number of remote methods.|A remote method call is defined as an invocation of a method that is not declared in any of: the class itself; a class or interface that the class extends or implements; a class or method that extends the class.|_N/A_                        |

## 4. Line of source code metrics ##
| **CS Abbr.** | **Other Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:----------------|:------------|:----------------|:----------------------------|
|**NSTA**      | Statements      |Number of statement|Total number of statements in the project.|N/A|
|**ELOC**      | eLOC            |Number of efferent lines of source code.|Count all line of source code except blank and commented lines.|_N/A_                        |
|**PLOC**      | pLOC            |Number of physical line of code.|Count all line of source code except blank lines.|N/A|
|**TOLI**      | Top Lines       |Number of line of source code without any exception.|Count all lines of code contained in the project without any exception.|_N/A_                        |
|**COLI**      | Cmt Lines       |Number of commented lines.|Count only commented lines.|_N/A_                        |
|**BLLI**      | Blnk Lines      |Number of blank lines.|Count only blank lines.|_N/A_                        |


## 5. Variable and attribute metrics ##
| **CS Abbr.** | **Other Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:----------------|:------------|:----------------|:----------------------------|
|**NOAT**      | NOA             |Number of attributes.|Total number of attributes in the project. Attributes are class variables.|_Yes_                        |
|**NOPA**      |                 |Number of **_public_** attributes.|Total number of **_public_** attributes in the project.|_Yes_                        |
|**NPRA**      |                 |Number of **_private_**  attributes.|Total number of **_private_** attributes in the project.|_Yes_                        |
|**NMSV**      |                 |Number of **_method scope_**  variables.|Total number of **_method scope_** variables in the project. "Method scope" variables are variables that are declared and initialized inside a method corpse. They are destroyed when the method finishes processing.|_N/A_                        |

##  ##
# Unit metrics #
##  ##

## 2. General metrics ##
| **CS Abbr.** | **Other Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:----------------|:------------|:----------------|:----------------------------|
|**MLIM**      |                 |Maximun number of LOC detected for a method.|This measure indicates the maximun number of LOC detected for a method.|_N/A_                        |

## 2. Class metrics ##

| **CS Abbr.** | **ISO Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:--------------|:------------|:----------------|:----------------------------|
|**NOII**      |               |Number of implemented interface.|A Java class may implement zero or more interfaces. This measure indicates the total number of interfaces implemented by a class.|_N/A_                        |
|**NOCM**      |               |Number of class methods.|This measure indicates the number of methods that have been explicitly defined in a class.It does not include the methods inherited from a super class.|_Yes_                        |
|**RROC**      |               |Reuse ratio of a class.|The reuse ratio is given by  U = Number of superclasses/Total number of classes.|_N/A_                        |
|**RSOC**      |               |Specialization ratio of a class.|This ration measures the extent to which a superclass has captured abstraction. The specialization ratio is given as S=Number of subclasses/Number of superclasses.|_N/A_                        |



##  ##
# Ratio metrics #
##  ##

| **CS Abbr.** | **Other Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:----------------|:------------|:----------------|:----------------------------|
|**RSPD**      |                 |Percentage of blank line and space compared to the total of LOC.|The space percentage is given by the formula : Space percentage = Total number of characters / Total number of blank characters.|_N/A_                        |
|**RCPD**      |                 |Percentage of comment compared to the total of LOC.|The comment density is given by the formula: Comment density = Number of comment lines / Total number of lines.|N/A|
|**RELM**      |                 |Average number of ELOC by method.|This measure provides the average quantity of ELOC(see _4.Line of source code metrics_) by method.|N/A|
|**RMBC**      |                 |Average number of methods by class.|This measure provides the average quantity of methods by class.|N/A|
|**AIDR**      |                 | Average Inheritance Depth.|The inheritance structure can be measured in terms of depth of class within its hierarchy. The AIDR is given as : AIDR = Sum of  depth of each class / Number of classes. |N/A|
|**CHDR**      |                 | Class Hierarchy Depth.|In object oreientedf design, the application domain is modeled as a hierarchy of classes. This measure indicates the maximun depth of any class in the project. |N/A|

##  ##
# ISO9196 Based metrics #
##  ##


| **CS Abbr.** | **Other Abbr.** | **Details** | **Description** | **Feature supported by CS** |
|:-------------|:----------------|:------------|:----------------|:----------------------------|
|**CCIM**      | V(G)            |Cyclomatic complexity.|Number of paths tought a method. The higher the number, the more testing effort will be required to cover all paths. A method with no conditional struct will have a CCIR of 1.|_N/A_                        |
|**RFCM**      | RFC             |Response For Class.|This measure captures the size of the response set of a class. The response set of a class consists of a all the methods called by local methods. RFC is the sum of the number of local methods and the number of methods called by local methods.|_N/A_                        |
|**LCOM**      | LCOM            |Lack of cohesion of methods.|The cohesion of a class is characterized by how closely the local methods are related to the local instances variables of a class. LCOM is defined as the number of disjoint(that is non intersecting) set of local methods.|_N/A_                        |
|**LCOM\_CK**  | LCOM\_CK        |Lack of cohesion of methods (Chidamber-Kemerer).|C&K defines the LCOM as a count of the number of method pairs whose similarity is not zero. For a perfectly cohesive class the value of LCOM\_CK is 0, and for a totally non cohesive class the LCOM\_CK value equals (n(n-1))/2 where n represents the total number of methods present in class.|_N/A_                        |