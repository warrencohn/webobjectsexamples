/*
� Copyright 2005-2007 Apple, Inc. All rights reserved.

IMPORTANT:  This Apple software is supplied to you by Apple Computer, Inc. (�Apple�) in consideration of your agreement to the following terms, and your use, installation, modification or redistribution of this Apple software constitutes acceptance of these terms.  If you do not agree with these terms, please do not use, install, modify or redistribute this Apple software.

In consideration of your agreement to abide by the following terms, and subject to these terms, Apple grants you a personal, non-exclusive license, under Apple�s copyrights in this original Apple software (the �Apple Software�), to use, reproduce, modify and redistribute the Apple Software, with or without modifications, in source and/or binary forms; provided that if you redistribute the Apple Software in its entirety and without modifications, you must retain this notice and the following text and disclaimers in all such redistributions of the Apple Software.  Neither the name, trademarks, service marks or logos of Apple Computer, Inc. may be used to endorse or promote products derived from the Apple Software without specific prior written permission from Apple.  Except as expressly stated in this notice, no other rights or licenses, express or implied, are granted by Apple herein, including but not limited to any patent rights that may be infringed by your derivative works or by other works in which the Apple Software may be incorporated.

The Apple Software is provided by Apple on an "AS IS" basis.  APPLE MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, REGARDING THE APPLE SOFTWARE OR ITS USE AND OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.

IN NO EVENT SHALL APPLE BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION, MODIFICATION AND/OR DISTRIBUTION OF THE APPLE SOFTWARE, HOWEVER CAUSED AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE), STRICT LIABILITY OR OTHERWISE, EVEN IF APPLE HAS BEEN  ADVISED OF THE POSSIBILITY OF
SUCH DAMAGE.
 */
// Generated by the WebObjects Wizard Wed Nov 22 14:28:44 US/Pacific 2000
package webobjectsexamples.petstorewojava;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORedirect;
import com.webobjectsexamples.petstore.Account;

public class Template extends WOComponent {

    //NOTE: this component is a Component Content container!

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3066816680452831328L;
	public boolean enableJavascript = false;
    public String keywords;

    public Template(WOContext cxt) {
        super(cxt);
    }

    public boolean isFrontPage(){
        return parent().name().equals("Main");
    }

    public Account account(){
        return (Account)parent().valueForKey("currentAccount");
    }


    @Override
	public WOComponent pageWithName(String page){
        //use the "parents" implementation of page with name since it does some
        // stuff for us, like set the last page, etc.
        return parent().pageWithName(page);
    }


    public WOComponent search() {
        WOComponent page = pageWithName("ShowSearchResults");
        page.takeValueForKey(keywords,"searchKeyword");
        keywords=null;//null this out since it is a shared, stateless component
        return page;
    }

    WOComponent prodCatPageForCategory(String cat){
        WOComponent page = pageWithName("ProductCategory");
        page.takeValueForKey(cat,"categoryName");
        return page;
    }

    public WOComponent showFish() {
        return prodCatPageForCategory("Fish");
    }

    public WOComponent showDogs() {
        return prodCatPageForCategory("Dogs");
    }

    public WOComponent showReptiles() {
        return prodCatPageForCategory("Reptiles");
    }

    public WOComponent showCats() {
        return prodCatPageForCategory("Cats");
    }

    public WOComponent showBirds() {
        return prodCatPageForCategory("Birds");
    }

    public WOComponent showCart()
    {
        return pageWithName("ShowShoppingCart");
    }

    public WOComponent showMain()
    {
        return pageWithName("Main");
    }

    public WOComponent signIn()
    {
        return pageWithName("SignIn");
    }

    public WOComponent signOut()
    {
        session().terminate();
        WORedirect redirect = new WORedirect(context());
        redirect.setUrl(context().directActionURLForActionNamed("default",null));
        return redirect;
    }

    public WOComponent showAccount()
    {
        return pageWithName("UpdateAccount");
    }

    public WOComponent showHelpPage()
    {
        return pageWithName("HelpPage");
    }

    @Override
	public boolean isStateless(){
        return true;
    }

    @Override
	public boolean synchronizesVariablesWithBindings(){
        return false;
    }

}
