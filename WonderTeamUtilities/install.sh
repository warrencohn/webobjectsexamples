#!/bin/bash

## This script downloads or updates the WOnder SVN source tree on your HD.
## This script manages the Wonder source in a dir named 
## wonder_for_<workspace_name>/Wonder
## that is in the same directory as the workspace containing this project
## (two directories above this project dir, assuming the enclosing dir is the workspace dir).
## If projects are linked to Eclipse workspace, it is recommended that those
## workspaces be closed for the duration of the update (saves Eclipse from 
## building continuously while files being updated by an outside process)


## Uncomment next line for debugging the script
set -x

# Must be run as sudo
#+ for installation of frameworks
if [ `whoami` != "root" ]; then
	  echo "You must run this script as sudo! Usage: sudo $@"
  exit 1
fi


############# Utility Functions #############

## Works for scripts executed like this:
#	./doscript.sh
#	/opt/scripts/doscript.sh
#	./mydir/scripts/anotherdir/doscript.sh
function script_dir {
	CUR_WORKING_DIR=`pwd`
	SCRIPT_EXEC_CMD=$0
	SCRIPT_EXEC_DIR=`dirname $0`
	cd $SCRIPT_EXEC_DIR
	SCRIPT_DIR=`pwd`
	## go back to working dir
	cd $CUR_WORKING_DIR
	echo "$SCRIPT_DIR"
}

##
# Remove dir without error by checking for it first
function remove_dir {
	DIR_TO_REMOVE=$1
	if [ -d $DIR_TO_REMOVE ]; then
		rm -r $DIR_TO_REMOVE
	fi
}

## Get parent dir of a file or dir
# the arg can have a trailing / and it still works
function parent_dir {
	FILE_TO_CHECK=$1
	# Remove trailing / if exists
	FILE_NO_SLASH="${FILE_TO_CHECK%/}"
	# Get parent dir
	PARENT_DIR="${FILE_NO_SLASH%/*}"
	echo "$PARENT_DIR"
}

## Takes a full path argument and returns the file
# or dir name
function file_name_from_path {
	FULL_PATH=$1
	# Remove trailing / if exists
	FULL_PATH_TRIMMED="${FULL_PATH%/}"
	## Get file name itself
	FILE_NAME="${FULL_PATH_TRIMMED##/*/}"
	echo "$FILE_NAME"
}

# Gets the svn version of the current working dir. This will fail if current working dir
# not an svn working copy
function get_svn_version {
	SVN_VERSION=`svn info | grep "Revision: "`
	SVN_VERSION=${SVN_VERSION#Revision:\ }
	echo "$SVN_VERSION"
}

function error {
    echo "An error occurred. Terminating..."
    exit 2
}
trap error ERR

############# Initialization #############
STARTING_WORKING_DIR=`pwd`
SCRIPT_DIR=`script_dir`
MY_USER_NAME=$SUDO_USER
USER_HOME=$HOME
FRAMEWORKS_DIR="/Library/Frameworks"

## Other variables from properties.txt
# The file declares the var 'WONDER_TEAM_SVN_VERSION'
source $SCRIPT_DIR/wonder_team_properties.txt

## Navigate to the dir containing the script (the project dir)
cd $SCRIPT_DIR

## Navigate to the workspace dir
cd ../
WORKSPACE_DIR=`pwd`

## Get workspace folder name
WORKSPACE_DIR_NAME=`file_name_from_path ${WORKSPACE_DIR}`

## Calculate Wonder Mgt Dir name
WONDER_MGT_DIR_NAME="wonder_for_${WORKSPACE_DIR_NAME}"

## Navigate to dir containing workspace
cd ../
WORKSPACE_CONTAINER_DIR=`pwd`

## calculate Wonder Mgt Dir (fullpath)
WONDER_MGT_DIR="${WORKSPACE_CONTAINER_DIR}/${WONDER_MGT_DIR_NAME}"

## If the wonder mgt dir does not exist create it
if [ ! -d ${WONDER_MGT_DIR} ]; then
	mkdir ${WONDER_MGT_DIR}
fi

## Debug all vars
#declare -p

############# Script Logic #############

cd $WONDER_MGT_DIR

if [ -d "Wonder" ]; then
	## OK, we have it, but is it the same version as we desire. If so, we don't need to update it
	cd Wonder
	CURRENT_SVN_VERSION=`get_svn_version`
	if [ ! $CURRENT_SVN_VERSION -eq $WONDER_TEAM_SVN_VERSION ]; then
		
		cd $WONDER_MGT_DIR
		echo "Downloading since current version was $CURRENT_SVN_VERSION and we require version $WONDER_TEAM_SVN_VERSION. Starting upgrade at "`date` >> install_log.txt
		
		## We must download the correct version
		## Fix permissions before
		chown -R $MY_USER_NAME:staff Wonder
		## Everyone on this computer read/write .... open source, who cares
		chmod -R 775 Wonder


		## Make a backup in case the update fails and we end up with a half updated unstable source
		## At least we can delete the half updated one and restore from this backup
		WONDER_ARCHIVE="Wonder_Backup.tar.gz"
		if [ -e $WONDER_ARCHIVE ]; then
			## Delete backup if exists
			rm $WONDER_ARCHIVE
		fi
	
		## Create the backup
		tar -czf $WONDER_ARCHIVE Wonder

		## Note, I am assuming svn version 1.4. If we had 1.5 we can update accepting all remote changes and avoid conflicts, but
		## to avoid conflicts, I must delete and checkout clean copy.
		## Delete old Wonder
		rm -r Wonder
		svn checkout http://wonder.svn.sourceforge.net/svnroot/wonder/trunk/Wonder Wonder -r $WONDER_TEAM_SVN_VERSION
		
	else
		echo "Current version $CURRENT_SVN_VERSION is OK, so not downloading" >> install_log.txt
	fi
else
	cd $WONDER_MGT_DIR
	
	# We don't have any Wonder working copy, so download it
	echo "Wonder Download of new copy at version $WONDER_TEAM_SVN_VERSION since old does not exist start date and time "`date` >> install_log.txt

	svn checkout http://wonder.svn.sourceforge.net/svnroot/wonder/trunk/Wonder Wonder -r $WONDER_TEAM_SVN_VERSION
	echo "Download completed at "`date` >> install_log.txt
fi

########################################################################################################################
## Install
########################################################################################################################


cd $WONDER_MGT_DIR

## Set permissions on the source tree to make sure we have no issues
chown -R $MY_USER_NAME:staff Wonder
chmod -R 775 Wonder

## Delete old installed frameworks so we don't end up with 'merged' files having old and new version jars for example
## This is a partial list and is only really important for the ones I am actually using to ensure I get clean updates
## Also any framework containing jars whose rev is in the name should be deleted since otherwise you get the old and new version
cd $FRAMEWORKS_DIR
FRAMEWORKS_CLEAN="Ajax AjaxLook DRGrouping ERCalendar ERWorkerChannel ERExtensions ERJars ERSelenium ERPrototypes"

for FRAMEWORK in $FRAMEWORKS_CLEAN
do
	echo "Uninstalling ... deleting $FRAMEWORKS_DIR/${FRAMEWORK}.framework if exists"
	remove_dir "$FRAMEWORKS_DIR/${FRAMEWORK}.framework"
done


## now install/update Wonder
cd $WONDER_MGT_DIR
cd Wonder

ant clean
ant frameworks
ant frameworks.install

# ## Build documentation. Creates the JavaDoc API in ~/dist
# ## Comment out this line if you don't want the Wonder API docs locally on your hard drive
# ant docs


## Delete the frameworks we especially do not use or never want to use for whatever reason
cd $FRAMEWORKS_DIR
FRAMEWORKS_DELETE="ERWorkerChannel"
for FRAMEWORK in $FRAMEWORKS_DELETE
do
	remove_dir "$FRAMEWORKS_DIR/${FRAMEWORK}.framework"
done

########################################################################################################################


cd $WONDER_MGT_DIR
##echo "Installation completed at "`date` >> install_log.txt

