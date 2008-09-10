@author Kieran 6/26/2008
---------------------------------

NOTE: ONLY FOR USE WITH WONDER SVN REPOSITORY WHICH CAME INTO EXISTENCE AROUND MAY 2008.

This project is designed to make it easy for a team to manage a Project Wonder local working copy.

This project contains the scripts and current team revision ensuring that everyone is working with the same version.

How it works:
==============
The install/update script looks for a directory named wonder_for_<workspace_name> in the same directory
 as the workspace containing this project. Hence you are forced to abide by the best practice of
  having a unique Wonder working copy for a specific workspace.

If not found it is automatically created and Wonder is checked out inside that directory.
You can then link to Wonder projects from your workspace.


Usage
======
Right-click the project (WonderTeamUtilities) and select Mac Goodies -> cd in Terminal

Close eclipe (to stop workspace building chaos while Wonder is updated.

Next, run

	$ sudo ./install.sh
