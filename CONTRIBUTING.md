I intend to open this project to Hacktoberfest 2023.

# Guidelines for Hacktoberfest 2023 Contributors

I will definitely consider pull requests associated with an issue and maybe 
consider pull requests associated with a TODO (considered an Action Item in 
NetBeans). Pull requests about build tools will be flatly rejected, with special 
prejudice against Maven and sbt.

Contributors should have a proper Java IDE (most are available for free) and 
JUnit. This project uses Java 8 and JUnit 4. Contributors may use later versions 
of Java provided they don't use features not available in Java 8 (I for one have 
upgraded to Java 21). Using JUnit 4 should be no problem in an IDE that also has 
JUnit 5 (e.g., IntelliJ).

Maybe the most straightforward way to get started on this project is to use the 
latest version of Apache NetBeans. Clone the project into your NetBeansProjects 
folder, then in NetBeans use the command File &gt; New Project... and select the 
category Java with Ant and the project type Java Project with Existing Sources. 
Select the folder where you cloned the project and mark src/ as Source Packages 
and test/ as Test Packages.
