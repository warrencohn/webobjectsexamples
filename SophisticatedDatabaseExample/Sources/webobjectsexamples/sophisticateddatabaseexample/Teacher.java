/*******************************************************************************
 *
 * [SophisticatedDatabaseExample Project]
 *
 * � 2005, 2007 Apple Computer, Inc.. All rights reserved.
 *
 * IMPORTANT:  This Apple software is supplied to you by Apple Computer, Inc. (�Apple�) in consideration of your agreement to the following terms, and your use, installation, modification or redistribution of this Apple software constitutes acceptance of these terms.  If you do not agree with these terms, please do not use, install, modify or redistribute this Apple software.
 *
 * In consideration of your agreement to abide by the following terms, and subject to these terms, Apple grants you a personal, non-exclusive license, under Apple�s copyrights in this original Apple software (the �Apple Software�), to use, reproduce, modify and redistribute the Apple Software, with or without modifications, in source and/or binary forms; provided that if you redistribute the Apple Software in its entirety and without modifications, you must retain this notice and the following text and disclaimers in all such redistributions of the Apple Software.  Neither the name, trademarks, service marks or logos of Apple Computer, Inc. may be used to endorse or promote products derived from the Apple Software without specific prior written permission from Apple.  Except as expressly stated in this notice, no other rights or licenses, express or implied, are granted by Apple herein, including but not limited to any patent rights that may be infringed by your derivative works or by other works in which the Apple Software may be incorporated.
 *
 * The Apple Software is provided by Apple on an "AS IS" basis.  APPLE MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, REGARDING THE APPLE SOFTWARE OR ITS USE AND OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.
 *
 * IN NO EVENT SHALL APPLE BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION, MODIFICATION AND/OR DISTRIBUTION OF THE APPLE SOFTWARE, HOWEVER CAUSED AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE), STRICT LIABILITY OR OTHERWISE, EVEN IF APPLE HAS BEEN  ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 *******************************************************************************/

package webobjectsexamples.sophisticateddatabaseexample;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

public class Teacher extends Employee {
	private static final long	serialVersionUID	= -978601600836412654L;

	public Teacher() {
		super();
	}

	/**
	 * Set proper Employee type
	 */
	@Override
	public void awakeFromInsertion(EOEditingContext ec) {
		super.awakeFromInsertion(ec);
		setEmployeeType(new Integer(Employee.TYPE_TEACHER));
	}

	public String department() {
		return (String) storedValueForKey("department");
	}

	public void setDepartment(String value) {
		takeStoredValueForKey(value, "department");
	}

	public String degree() {
		return (String) storedValueForKey("degree");
	}

	public void setDegree(String value) {
		takeStoredValueForKey(value, "degree");
	}

	public NSArray students() {
		return (NSArray) storedValueForKey("students");
	}

	public void setStudents(NSMutableArray value) {
		takeStoredValueForKey(value, "students");
	}

	public void addToStudents(Student object) {
		NSMutableArray array = (NSMutableArray) students();

		willChange();
		array.addObject(object);
	}

	public void removeFromStudents(Student object) {
		NSMutableArray array = (NSMutableArray) students();

		willChange();
		array.removeObject(object);
	}

	public NSArray scheduledClasses() {
		return (NSArray) storedValueForKey("scheduledClasses");
	}

	public void setScheduledClasses(NSMutableArray value) {
		takeStoredValueForKey(value, "scheduledClasses");
	}

	public void addToScheduledClasses(ScheduledClass object) {
		NSMutableArray array = (NSMutableArray) scheduledClasses();

		willChange();
		array.addObject(object);
	}

	public void removeFromScheduledClasses(ScheduledClass object) {
		NSMutableArray array = (NSMutableArray) scheduledClasses();

		willChange();
		array.removeObject(object);
	}
}
