# Introduction #

Since the target audience of the examples include new users, it is best that the projects have a consistent layout that is the same as the layout these users see when they create projects in WOLips. Having it any other way would just make the learning process confusing.


# Details #

The steps to convert are generally like this. In general, it may be better to get the project launching/working before refactoring to the new layout.
  * Rename "src" to Sources
  * Refactor -> Move the Resources and WebServerResources to the top level of the project. These are often nested inside other folder in Apple's examples. If not there just create them.
  * Check for existence of and create missing folders for Resources, Libraries, Components
  * Move all WOComponents to Components folder
  * Move Properties and other resource files to Resources
  * Copy the build.properties from an example new Wonder application or framework to the project and configure
  * Replace the build.xml with one from an example new Wonder application or framework and change the project name in the first line.
  * Delete the woproject/ant.`*` files which are no longer used by new classpath hotness.
  * Copy the woproject/`*`.patternset files from example wodner application or framework and paste over the current ones
  * Delete all other superfluous stuff such as build-properties.xml, apple's patternset files, etc.



# Testing #
  * Run the application, ensuring it launches.
  * Right-click the build.xml and Run As -> Ant Build
  * Don't worry if it is not perfect. Other bugs can be fixed on svn working copies later.


# Commit #
If not already committed, commit it to the trunk ensuring that bin is added to svn:ignore