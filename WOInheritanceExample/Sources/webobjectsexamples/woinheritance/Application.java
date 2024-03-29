/*
 * Application.java
 * [WOInheritance Project]
 *
 * � Copyright 2001-2007 Apple, Inc. All rights reserved.
 *
 * IMPORTANT:  This Apple software is supplied to you by Apple Computer,
 * Inc. (�Apple�) in consideration of your agreement to the following
 * terms, and your use, installation, modification or redistribution of
 * this Apple software constitutes acceptance of these terms.  If you do
 * not agree with these terms, please do not use, install, modify or
 * redistribute this Apple software.
 *
 * In consideration of your agreement to abide by the following terms,
 * and subject to these terms, Apple grants you a personal, non-
 * exclusive license, under Apple�s copyrights in this original Apple
 * software (the �Apple Software�), to use, reproduce, modify and
 * redistribute the Apple Software, with or without modifications, in
 * source and/or binary forms; provided that if you redistribute the
 * Apple Software in its entirety and without modifications, you must
 * retain this notice and the following text and disclaimers in all such
 * redistributions of the Apple Software.  Neither the name, trademarks,
 * service marks or logos of Apple Computer, Inc. may be used to endorse
 * or promote products derived from the Apple Software without specific
 * prior written permission from Apple.  Except as expressly stated in
 * this notice, no other rights or licenses, express or implied, are
 * granted by Apple herein, including but not limited to any patent
 * rights that may be infringed by your derivative works or by other
 * works in which the Apple Software may be incorporated.
 *
 * The Apple Software is provided by Apple on an "AS IS" basis.  APPLE
 * MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 * THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE, REGARDING THE APPLE SOFTWARE OR ITS
 * USE AND OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.
 *
 * IN NO EVENT SHALL APPLE BE LIABLE FOR ANY SPECIAL, INDIRECT,
 * INCIDENTAL OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE,
 * REPRODUCTION, MODIFICATION AND/OR DISTRIBUTION OF THE APPLE SOFTWARE,
 * HOWEVER CAUSED AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING
 * NEGLIGENCE), STRICT LIABILITY OR OTHERWISE, EVEN IF APPLE HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package webobjectsexamples.woinheritance;

import java.net.URL;

import com.webobjects.eoaccess.EOModelGroup;
import com.webobjects.foundation.NSLog;
import com.webobjects.foundation.NSPathUtilities;

import er.extensions.ERXApplication;

public class Application extends ERXApplication {
    public String selectedModelType=null;

    public static void main(String argv[]) {
        ERXApplication.main(argv, Application.class);
    }

    public Application() {
        super();
        System.out.println("Welcome to " + this.name() + "!");

		//Check that derby example database configured correctly
		String derby_home = System.getProperty("derby.system.home");

		if (derby_home == null || derby_home.length() < 1) {
			try {
				URL url = new URL(derby_home);
				if (!NSPathUtilities.fileExistsAtPathURL(url)) {
					NSLog.err.appendln("WARNING: derby.system.home is not configured correctly at "+derby_home+"  Also verify that you have run the installed the example databases correctly.");
					System.exit(1);
				}
			} catch (Exception e) {
				NSLog.err.appendln("WARNING: derby.system.home is not configured correctly at "+derby_home+"  Also verify that you have run the installed the example databases correctly.");
				System.exit(1);
			}
		}

        // This allows the first page to be set in DirectAction's defaultAction method
        setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));

        // If modelType is set in our Properties file then set it here and load the model
        selectedModelType=System.getProperty("ModelType");
        if (selectedModelType != null)
                SelectModelPage.loadModelType(selectedModelType);

		System.out.println("MG: "+ EOModelGroup.defaultGroup());

//		for (EOModel model : EOModelGroup.defaultGroup().models()) {
//			System.out.println("Model: "+ model);
//		}
   }

}
