# Introduction #

Objective is to get thesed working with WO 5.3.3. We need at least ERExtensions, ERJars and WOOgnl added to every project so that we are compatible with inline template format and get the generics foundation class patches from ERExtensions.


# Details #

Add your content here.  Format your content with:
  * Add ERExtensions, ERJars, WOOgnl as libraries to the classpath. Make sure WOOgnl is below ERExtensions in the build path order.
  * If we need database access, add Wonder's DerbyPlugIn also.
  * Have Application, Session, DirectAction and EO's extend Wonder's subclasses of same.
  * Remove build path references to WO 5.4 jar files if those exist. We only want to use the WOLips new classpath hotness.