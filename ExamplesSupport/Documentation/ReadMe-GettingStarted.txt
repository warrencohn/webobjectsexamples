Introduction
==============
This collection of examples WebObjects applications was derived from Apple's examples that were shipped with WO 5.4.

The objective of making this collection available in this way is to make it easier for new users to simply checkout
all the examples into their workspace and run them after performing initial database configuration.

Having said that, Project Wonder is required to run these projects, but don't let that scare you.
In fact you are better off having Wonder linked into your projects from the start since it will make your life easier.

Getting Started Instructions
=============================

1) If you don't have recent version of WOnder installed, then you can install it in any number of ways documented
in the tutorials at:

    http://wiki.objectstyle.org/confluence/display/WOL/Tutorials

However, for your convenience, a simple script is provided in this project's support
folder named installWonder.sh which just downloads the latest Wonder and installs the frameworks only.

TIP: To quickly get to the Support folder in Terminal.app, simply right-click the Support folder in Eclipse's Explorer view and
select 'Mac Goodies' -> 'Cd in Terminal' from the context menu.

Simply navigate into the Support folder in Terminal.app and execute the script like this and take a 5-minute break while it
downloads and installs:
(TIP: Don't type the $ symbol, that (and sometimes %) is just a convention to show that this is a terminal command)

$ sudo ./installWonder.sh
Password:
$

BTW, the Wonder source will be placed in the same folder as your workspace folder inside a folder named
"wonder_for_<workspace-folder-name>" where <workspace-folder-name> is the current name of your workspace folder.

2) Some of the applications require the use of databases. For your convenience, the bundled java derby database platform
if used for running the examples. To install the databases, simply run the command line script named installDatabases.sh
which can be found in the Support folder of this project. Since the database files are installed in your user's library folder
you don't need to use sudo.

./installDatabases.sh



