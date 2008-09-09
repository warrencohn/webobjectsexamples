/*
 * DAUploadIteratorPage.java
 * [FileUpload Project]

 � Copyright 2005 Apple Computer, Inc. All rights reserved.

 IMPORTANT:  This Apple software is supplied to you by Apple Computer, Inc. (�Apple�) in consideration of your agreement to the following terms, and your use, installation, modification or redistribution of this Apple software constitutes acceptance of these terms.  If you do not agree with these terms, please do not use, install, modify or redistribute this Apple software.

 In consideration of your agreement to abide by the following terms, and subject to these terms, Apple grants you a personal, non-exclusive license, under Apple�s copyrights in this original Apple software (the �Apple Software�), to use, reproduce, modify and redistribute the Apple Software, with or without modifications, in source and/or binary forms; provided that if you redistribute the Apple Software in its entirety and without modifications, you must retain this notice and the following text and disclaimers in all such redistributions of the Apple Software.  Neither the name, trademarks, service marks or logos of Apple Computer, Inc. may be used to endorse or promote products derived from the Apple Software without specific prior written permission from Apple.  Except as expressly stated in this notice, no other rights or licenses, express or implied, are granted by Apple herein, including but not limited to any patent rights that may be infringed by your derivative works or by other works in which the Apple Software may be incorporated.

 The Apple Software is provided by Apple on an "AS IS" basis.  APPLE MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, REGARDING THE APPLE SOFTWARE OR ITS USE AND OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.

 IN NO EVENT SHALL APPLE BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION, MODIFICATION AND/OR DISTRIBUTION OF THE APPLE SOFTWARE, HOWEVER CAUSED AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE), STRICT LIABILITY OR OTHERWISE, EVEN IF APPLE HAS BEEN  ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.
 */
/**
 The class and the associated component are the "face" page to the example using
 DirectActions.  This page is returned as a result of the "directIteratorAction"
 method defined in the DirectAction class, and all processing for this pages is
 done by the "ProcessFileUploadWithIteratorAction" action in the DirectAction class.
 The only details for this page are the dummy instance variables for the form input
 elements.
 NOTE: Because this page does not attempt to display the uploaded file, it can be
 used to upload arbitrarily large files.
 */
package webobjectsexamples.fileupload;

import java.io.InputStream;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;



public class DAUploadIteratorPage extends WOComponent {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8083989257178031692L;
	// variables for the data displayed on screen, submitted into the form
    public String fileName;
    public InputStream inputStream  = null;

    /**
     * Constructor
     */
    public DAUploadIteratorPage( WOContext context ) {
        super( context );
    }


    // Setter for fileName
    public void setFileName(String aFileName) {
        fileName = aFileName;
    }

    // Setter for inputStream
    public void setInputStream(InputStream anIS) {
        inputStream = anIS;
    }

    /**
     * Method to conditionally return true or false based on the filename
     */
    public boolean hasFile() {
        if ( (fileName != null) && (fileName.length() > 0) ) {
            return true;
        }
        return false;
    }
}
