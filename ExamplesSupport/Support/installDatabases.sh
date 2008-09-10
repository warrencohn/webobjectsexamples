#!/bin/sh


## Debug script
set -x


############# Utility Functions #############

## USAGE:
# Example:
# WORKSPACE_DIR_NAME=`file_name_from_path ${WORKSPACE_DIR}`

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

##
# Remove dir without error by checking for it first
function remove_file {
        FILE_TO_REMOVE=$1
        if [ -e $FILE_TO_REMOVE ]; then
                rm $FILE_TO_REMOVE
        fi
}

## Get parent dir of a file or dir
# the arg can have a trailing / and it still works
# TODO: Handle filename with no directory separators and return the current working dir
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


## Must be run as sudo
function must_run_as_root {
	if [ `whoami` != "root" ]; then
		  echo "You must run this script as sudo! Usage: sudo $@"
	  exit 1
	fi
}

## Get a formatted date to use in filenames as a timestamp
function filestamp {
	echo `date +"%Y%m%d_%H%M"`
}

#################################################


function error {
    echo "An error occurred. Terminating..."
    exit 2
}
trap error ERR

##############################################

## Setup database SQL files location
db_home=`script_dir`/Databases

## Assuming Wonder and the DerbyPlugIn is installed
# TODO : Check for workspace derby plugin existence and if so
#        use that instead
# TODO : Compare versions of Library derby and workspace derby and output a warning if they are different
db_lib=/Library/Frameworks/DerbyPlugIn.framework/Resources/Java

if [ ! -d $db_lib ]; then
    echo "\nERROR: Cannot find Derby database files at \"$db_lib\".\n"
    echo $usage"\n"
    exit 1
fi

if [ ! -f $db_home/movies_create.sql ]; then
    echo "\nERROR: Cannot find example database import files at, for example, \"$db_home/movies_create.sql\".\n"
    echo $usage"\n"
    exit 1
fi

usage="Usage: installDatabases <username>"

if [ `id -u` != 0 ]; then
    echo "\nERROR: Please run this script as root.\n"
    echo $usage"\n"
    exit 1
fi

user=$1

if [ "$user" = "" ]; then
    echo "\nERROR: Please give a username as a parameter.\n"
    echo $usage"\n"
    exit 1
fi

data_home=/Users/$user/Library/Databases/derby-10.3.3.0

/bin/mkdir -p $data_home

if [ "$JAVA_HOME" = "" ]; then
    java="/usr/bin/java"
else
    java="$JAVA_HOME/bin/java"
fi

/bin/rm -rf $data_home/data

out=/tmp/derby_$$.log
/usr/bin/touch $out

opts="-classpath $db_lib/derby.jar:$db_lib/derbytools.jar -Dderby.system.home=$data_home/data"

$java $opts org.apache.derby.tools.ij < $db_home/bug_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/bug_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/discussion_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/discussion_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/inherit_horizontal_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/inherit_horizontal_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/inherit_singletable_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/inherit_singletable_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/inherit_vertical_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/inherit_vertical_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/movies_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/movies_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/petstore_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/petstore_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/realestate_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/realestate_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/school_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/school_insert.sql

$java $opts org.apache.derby.tools.ij < $db_home/vendor_create.sql
$java $opts org.apache.derby.tools.ij < $db_home/vendor_insert.sql

chown -R $user $data_home

$java $opts org.apache.derby.tools.sysinfo

echo "Ok"
