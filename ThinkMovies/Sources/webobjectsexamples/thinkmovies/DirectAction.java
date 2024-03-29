/*
 * DirectAction.java [ThinkMovies Project] Copyright 2005 - 2007  Apple, Inc. All rights reserved. IMPORTANT: This Apple software is supplied to you by Apple Computer, Inc. (�Apple�) in consideration of your agreement to the following terms, and your use, installation, modification or
 * redistribution of this Apple software constitutes acceptance of these terms. If you do not agree with these terms, please do not use, install, modify or redistribute this Apple software. In consideration of your agreement to abide by the following terms, and subject to these terms, Apple grants
 * you a personal, non-exclusive license, under Apple�s copyrights in this original Apple software (the �Apple Software�), to use, reproduce, modify and redistribute the Apple Software, with or without modifications, in source and/or binary forms; provided that if you redistribute the Apple Software
 * in its entirety and without modifications, you must retain this notice and the following text and disclaimers in all such redistributions of the Apple Software. Neither the name, trademarks, service marks or logos of Apple Computer, Inc. may be used to endorse or promote products derived from the
 * Apple Software without specific prior written permission from Apple. Except as expressly stated in this notice, no other rights or licenses, express or implied, are granted by Apple herein, including but not limited to any patent rights that may be infringed by your derivative works or by other
 * works in which the Apple Software may be incorporated. The Apple Software is provided by Apple on an "AS IS" basis. APPLE MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE,
 * REGARDING THE APPLE SOFTWARE OR ITS USE AND OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS. IN NO EVENT SHALL APPLE BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION, MODIFICATION AND/OR DISTRIBUTION OF THE APPLE SOFTWARE, HOWEVER CAUSED AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE), STRICT LIABILITY OR OTHERWISE, EVEN IF APPLE HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package webobjectsexamples.thinkmovies;

import webobjectsexamples.businesslogic.movies.common.Voting;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WODirectAction;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.appserver.WORequest;
import com.webobjects.eoaccess.EODatabaseDataSource;
import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSLog;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSNumberFormatter;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;
import com.webobjects.foundation.NSValidation;

import er.extensions.appserver.ERXDirectAction;

/*
 * This class, along with the Application class, is the main focal point of the application. This application is exclusively DirectAction based, so all processing for the application (including searching) happens in this class. This class demonstrates how to obtain form value with DirectActions: the
 * important concept is the use of the pre-defined form value names.
 */

public class DirectAction extends ERXDirectAction {

	// Instance variables for the form value names for the Movie object
	public static String				TITLE					= "title";

	public static String				TITLEOPERATOR			= "titleOperator";

	public static String				DATEOPERATOR			= "dateOperator";

	public static String				DATERELEASED			= "dateReleased";

	public static String				CATEGORY				= "category";

	public static String				CATEGORYOPERATOR		= "categoryOperator";

	public static String				STUDIO					= "studio";

	public static String				REVENUE					= "revenue";

	// Private instance variables for formatters
	private static NSNumberFormatter	IntegerNumberFormatter	= new NSNumberFormatter("#0");

	private static NSTimestampFormatter	StandardDateFormatter	= new NSTimestampFormatter("%m/%d/%Y");

	/**
	 * Constructor for the DirectAction class. This constructor is required in Java for all WODirectAction subclasses.
	 */

	public DirectAction(WORequest aRequest) {
		super(aRequest);
	}

	/**
	 * Default action for the DirectAction class. When the default request handler for the application is set to the directAction handler (as this application is), this is the method performed. This replaces the componentActionHandler's call to "Main" as a default.
	 */

	@Override
	public WOActionResults defaultAction() {
		WOComponent homePage = pageWithName("HomePage");
		return homePage;
	}

	/**
	 * Method to go to the home page ("HomePage") for the toolbar.
	 */

	public WOComponent HomeAction() {
		return pageWithName("HomePage");
	}

	/**
	 * Method to go to the help page ("HelpPage") for the toolbar
	 */

	public WOComponent HelpAction() {
		return pageWithName("HelpPage");
	}

	/**
	 * Method to process a new movie. This method pulls a movie from the request, inserts it into the application's editing context, and saves the object. The method then sends the movie to the main detail page for movies and shows the new information. There is elaborate error handling in this
	 * method to ensure that if something goes awry that bogus data is not saved into the database or pollutes the editing context.
	 */

	public WOComponent ProcessMovieAction() {

		WORequest aRequest = request();
		EOEnterpriseObject aMovie = newMovieFromRequest(aRequest);
		Application anApp = Application.myApplication();
		NSMutableDictionary queryDict = new NSMutableDictionary();
		WODisplayGroup aDG = displayGroupFromRequest(queryDict);
		NSValidation.ValidationException exception = null;

		EOEditingContext anEC = anApp.lockEC();
		try {
			anEC.insertObject(aMovie);
			anEC.saveChanges();
			aDG.qualifyDataSource();
		} catch (NSValidation.ValidationException ex) {
			exception = ex;
			anEC.undo();
		} finally {
			anApp.unlockEC();
		}

		if (exception != null) {
			if (NSLog.debugLoggingAllowedForLevelAndGroups(NSLog.DebugLevelInformational, NSLog.DebugGroupValidation)) {
				NSLog.out.appendln(exception);
			}

			WOComponent failurePage = pageWithName("AddMoviePage");
			failurePage.takeValueForKey(exception, "validationException");
			failurePage.takeValueForKey(aRequest.stringFormValueForKey(CATEGORY), "aCategory");
			failurePage.takeValueForKey(aRequest.stringFormValueForKey(REVENUE), "aRevenue");
			failurePage.takeValueForKey(aRequest.stringFormValueForKey(DATERELEASED), "aDateReleased");
			Number aNumber = aRequest.numericFormValueForKey(STUDIO, IntegerNumberFormatter);

			if (aNumber != null) {
				aNumber = new Integer(aNumber.intValue());
			}

			EOEnterpriseObject aStudio = anApp.lookupByID("Studio", aNumber);
			if (aStudio != null) {
				String name = (String) aStudio.valueForKey("name");
				NSDictionary<String, Object> aStudioDictionary = new NSDictionary<String, Object>(new Object[] { aNumber, name }, new String[] { "studioID", "name" });
				failurePage.takeValueForKey(aStudioDictionary, "aStudio");
			}

			return failurePage;
		}

		MovieComponent aPage = (MovieComponent) pageWithName("DisplayVideoPage");
		aPage.takeValueForKey(aMovie, MovieComponent.MovieKey);
		NSArray movielist = aDG.displayedObjects();
		aPage.takeValueForKey(movielist, "list");
		aPage.takeValueForKey(queryDict, "queryDict");

		int index = aPage.indexOfMovie(movielist, aMovie);
		aPage.takeValueForKey(movielist.objectAtIndex(index), MovieComponent.RawMovieKey);

		return aPage;
	}

	/**
	 * Method to return the search page ("SearchPage") for the toolbar. The dictionary of query information for the page to use is obtained from the request and passed to the search page.
	 */

	public WOComponent SearchAction() {
		WOComponent aPage = pageWithName("SearchPage");
		NSDictionary aDict = queryDictionaryFromRequest(request());
		aPage.takeValueForKey(aDict, "queryDict");
		return aPage;
	}

	/**
	 * Method to return the page to add a movie ("AddMoviePage") for the toolbar.
	 */

	public WOComponent AddMovieAction() {
		WOComponent aPage = pageWithName("AddMoviePage");
		return aPage;
	}

	/**
	 * Method to return the results of a search. This method takes the query dictionary from the request and then uses a WODisplayGroup to perform the fetch for matches. (This is very easy since we can set the query dictionary on the displayGroup and just qualifyDataSource()... ) If more than one
	 * match is found, the results are batched in groups of 10 and sent to the results page. If there is one match, we go straight to the detail page ("DisplayVideoPage"). If there are no results, we stay on the search page.
	 */

	public WOComponent ResultsAction() {

		WOComponent aPage = null;
		WORequest aRequest = request();
		NSMutableDictionary aDict = queryDictionaryFromRequest(aRequest);
		Number aBatch = aRequest.numericFormValueForKey("batchIndex", IntegerNumberFormatter);

		int aBatchIndex = (aBatch == null) ? 1 : aBatch.intValue();

		Application anApp = Application.myApplication();
		WODisplayGroup aDG = displayGroupFromRequest(aDict);
		try {
			EOEditingContext anEC = anApp.lockEC();

			aDG.qualifyDataSource();
			NSArray aList = aDG.displayedObjects();

			if (aList.count() > 1) {

				aPage = pageWithName("DisplaySetPage");
				aDG.setNumberOfObjectsPerBatch(10);
				int maxBatch = aDG.batchCount();

				if (aBatchIndex < 1) {
					aBatchIndex = maxBatch;
				}

				aDG.setCurrentBatchIndex(aBatchIndex);
				aPage.takeValueForKey(aDG, "displayGroup");

			} else if (aList.count() == 1) {

				aPage = pageWithName("DisplayVideoPage");
				NSDictionary rawMovie = (NSDictionary) aList.objectAtIndex(0);
				EOEnterpriseObject currentMovie = anApp.deepFetchMovie(rawMovie.objectForKey(MovieComponent.MovieIDKey), anEC);
				aPage.takeValueForKey(currentMovie, MovieComponent.MovieKey);
				aPage.takeValueForKey(aList, "list");
				aPage.takeValueForKey(rawMovie, MovieComponent.RawMovieKey);

			} else {

				aPage = pageWithName("SearchPage");
				aDict.setObjectForKey("No movies found.", SearchPage.QueryFailedKey);
			}

		} finally {
			anApp.unlockEC();
		}
		aPage.takeValueForKey(aDict, "queryDict");
		return aPage;
	}

	/**
	 * Method to return the master list of movies. This method puts the complete list of movies into a WODisplayGroup and then passes that to a result page ("DisplaySetPage").
	 */

	public WOComponent MasterIndexAction() {
		Application anApp = Application.myApplication();

		NSMutableDictionary queryDict = new NSMutableDictionary();
		WODisplayGroup aDG = displayGroupFromRequest(queryDict);

		try {
			/* EOEditingContext anEC = */anApp.lockEC();
			aDG.qualifyDataSource();
		} finally {
			anApp.unlockEC();
		}

		aDG.setNumberOfObjectsPerBatch(10);
		WOComponent aPage = pageWithName("DisplaySetPage");
		aPage.takeValueForKey(aDG, "displayGroup");
		aPage.takeValueForKey(queryDict, "queryDict");
		return aPage;
	}

	/**
	 * Method to display the information for a specific movie (using the "DisplayVideoPage"). This method takes the query information from the request and uses a WODisplayGroup to get the information for the movie. The movie (as well as the full list of movies) is passed to the display page; we use
	 * the index of the selected movie in the list to set the index for the batching.
	 */

	public WOComponent DisplayMovieAction() {

		WORequest aRequest = request();
		NSDictionary aDict = queryDictionaryFromRequest(aRequest);
		WODisplayGroup aDG = displayGroupFromRequest(aDict);

		MovieComponent aPage = (MovieComponent) pageWithName("DisplayVideoPage");
		Application anApp = Application.myApplication();
		Number movieID = Integer.valueOf((String) aRequest.formValueForKey(MovieComponent.MovieIDKey));
		EOEnterpriseObject currentMovie;

		try {
			EOEditingContext anEC = anApp.lockEC();
			currentMovie = anApp.deepFetchMovie(movieID, anEC);
			aDG.qualifyDataSource();
		} finally {
			anApp.unlockEC();
		}

		NSArray aMovieList = aDG.displayedObjects();
		aPage.takeValueForKey(currentMovie, MovieComponent.MovieKey);
		aPage.takeValueForKey(aMovieList, "list");
		aPage.takeValueForKey(aDict, "queryDict");
		int index = aPage.indexOfMovieID(aMovieList, movieID);
		aPage.takeValueForKey(aMovieList.objectAtIndex(index), MovieComponent.RawMovieKey);

		return aPage;
	}

	/**
	 * Method to return the voting page ("VotingPage") to allow users to vote for a particular movie. This method gets the movie by taking the query information from the request and getting the movie by ID number. Then the movie is pased to the next page.
	 */

	public WOComponent VotingAction() {

		WORequest aRequest = request();
		WOComponent aComponent = pageWithName("VotingPage");
		NSDictionary aDict = queryDictionaryFromRequest(aRequest);
		EOEnterpriseObject aMovie = lookupMovieIDFromRequest(aRequest);
		aComponent.takeValueForKey(aMovie, MovieComponent.MovieKey);
		aComponent.takeValueForKey(aDict, "queryDict");
		return aComponent;
	}

	/**
	 * Method to return the list of reviews for a particular movie. This method gets the movie from the ID number in the request, and the list of reviews from the request. If there are no reviews in the request, they are pulled from the "reviews" relationship on the Movie object.
	 */

	public WOComponent DisplayReviewsAction() {

		WORequest aRequest = request();
		WOComponent aComponent = pageWithName("DisplayReviewsPage");
		EOEnterpriseObject aMovie = lookupMovieIDFromRequest(aRequest);
		EOEnterpriseObject aReview = lookupReviewIDFromRequest(aRequest);
		aComponent.takeValueForKey(aMovie, MovieComponent.MovieKey);

		if (aReview == null) {
			NSArray reviews = (NSArray) aMovie.valueForKey("reviews");
			if (reviews.count() > 0) {
				aReview = (EOEnterpriseObject) reviews.objectAtIndex(0);
			}
		}
		aComponent.takeValueForKey(aReview, "review");
		return aComponent;
	}

	/**
	 * Method to add a review for a movie. This method gets the movie from the ID number in the request and then passes the movie to the page to add reviews ("AddReviewPage").
	 */

	public WOComponent AddReviewAction() {

		WOComponent aComponent = pageWithName("AddReviewPage");
		EOEnterpriseObject aMovie = lookupMovieIDFromRequest(request());
		aComponent.takeValueForKey(aMovie, MovieComponent.MovieKey);
		return aComponent;
	}

	/**
	 * Method to process the adding of a new review for a movie. The movie and the new review are obtained from the request. If the review text is not null, the review object is added to the Movie object and then saved into the database. After the review is added, the application returns to the page
	 * to display the reviews for a movie.
	 */

	public WOComponent ProcessAddReviewAction() {

		Application anApp = Application.myApplication();
		WORequest aRequest = request();
		WOComponent aComponent = pageWithName("DisplayReviewsPage");
		EOEnterpriseObject aMovie = lookupMovieIDFromRequest(aRequest);
		EOEnterpriseObject aReview = reviewFromRequest(aRequest);

		try {
			EOEditingContext anEC = anApp.lockEC();
			String reviewText = (String) aReview.valueForKey("review");
			if ((reviewText != null) && (reviewText.length() > 0)) {
				aMovie.addObjectToBothSidesOfRelationshipWithKey(aReview, "reviews");
				anEC.saveChanges();
			}
		} finally {
			anApp.unlockEC();
		}

		aComponent.takeValueForKey(aMovie, MovieComponent.MovieKey);
		aComponent.takeValueForKey(aReview, "review");
		return aComponent;
	}

	/**
	 * Method to process the adding of a new vote to a Movie object. The movie and query information is obtained from the request, and the voting information is made into a Number (since it comes back as a String). A new Vote object is then added to the editing context, related to the Movie, and
	 * then saved to the database. We then go to the main viewing page for the movie (the "DisplayVideoPage").
	 */

	public WOComponent ProcessVoteAction() {

		WORequest aRequest = request();
		MovieComponent aComponent = (MovieComponent) pageWithName("DisplayVideoPage");
		NSDictionary aDict = queryDictionaryFromRequest(aRequest);

		WODisplayGroup aDG = displayGroupFromRequest(aDict);
		Application anApp = Application.myApplication();
		EOEnterpriseObject aMovie = lookupMovieIDFromRequest(aRequest);

		Number temp = aRequest.numericFormValueForKey("vote", IntegerNumberFormatter);
		int newVote = (temp == null) ? 0 : temp.intValue();
		NSArray aMovieList;
		Voting voting;

		try {
			EOEditingContext anEC = anApp.lockEC();
			voting = (Voting) aMovie.valueForKey("voting");
			voting.addVote(newVote);
			anEC.saveChanges();
			aDG.qualifyDataSource();
		} finally {
			anApp.unlockEC();
		}

		aMovieList = aDG.displayedObjects();
		aComponent.takeValueForKey(aMovie, MovieComponent.MovieKey);
		aComponent.takeValueForKey(aMovieList, "list");
		aComponent.takeValueForKey(aDict, "queryDict");
		int index = aComponent.indexOfMovie(aMovieList, aMovie);
		aComponent.takeValueForKey(aMovieList.objectAtIndex(index), MovieComponent.RawMovieKey);

		return aComponent;
	}

	/**
	 * This action is really implemented in the RentalStore example, which requires both RentalStore and ThinkMovies to be running on the same host with access through a Web Server, not Direct Connect. Direct Connect would call this version, so we just redisplay the movie.
	 */

	public WOComponent AddMovieToShoppingBasketAction() {
		return DisplayMovieAction();
	}

	// *****************************************************
	// *** Non-Action methods ***
	// *****************************************************

	/**
	 * Method to return a WODisplayGroup initialized with the query information passed in (obtained froma WORequest). This displayGroup has the datasource set, and the queryMatch and queryOperator dictionaries are pre-populated with the correct information.
	 *
	 * @param queries
	 *            the dictionary of query information from the request
	 * @return a pre-initialized WODisplayGroup
	 */

	public WODisplayGroup displayGroupFromRequest(NSDictionary queries) {

		WODisplayGroup aDisplayGroup = new WODisplayGroup();
		Application anApp = Application.myApplication();
		EODatabaseDataSource aDataSource = anApp.movieDataSource();
		aDataSource.setAuxiliaryQualifier(null); // need to do this since we share DS
		aDisplayGroup.setDataSource(aDataSource);

		NSMutableDictionary queryMatch = aDisplayGroup.queryMatch();
		NSMutableDictionary queryOperator = aDisplayGroup.queryOperator();

		queryMatch.addEntriesFromDictionary(queries);
		Object titleOp = queryMatch.removeObjectForKey(TITLEOPERATOR);
		Object categoryOp = queryMatch.removeObjectForKey(CATEGORYOPERATOR);
		Object dateOp = queryMatch.removeObjectForKey(DATEOPERATOR);

		queryOperator.takeValueForKey(titleOp, TITLE);
		queryOperator.takeValueForKey(categoryOp, CATEGORY);
		queryOperator.takeValueForKey(dateOp, DATERELEASED);

		return aDisplayGroup;
	}

	/**
	 * Method to return an NSMutableDictionary with the preset information from the form values in the request. The pertinent information for a movie is pulled in from the values and put into the dictionary, and the proper date (and date operator) is added.
	 *
	 * @param aRequest
	 *            a WORequest with form values to use
	 * @return a dictionary of information (set for querying)
	 */

	public NSMutableDictionary queryDictionaryFromRequest(WORequest aRequest) {

		String title = aRequest.stringFormValueForKey(TITLE);
		String titleOp = aRequest.stringFormValueForKey(TITLEOPERATOR);
		String category = aRequest.stringFormValueForKey(CATEGORY);
		String categoryOp = aRequest.stringFormValueForKey(CATEGORYOPERATOR);
		NSTimestamp date = aRequest.dateFormValueForKey(DATERELEASED, StandardDateFormatter);
		String dateOp = aRequest.stringFormValueForKey(DATEOPERATOR);

		NSMutableDictionary aDict = new NSMutableDictionary(6);

		if (title != null) {
			aDict.takeValueForKey(title, TITLE);
			aDict.takeValueForKey(titleOp, TITLEOPERATOR);
		}

		if (category != null) {
			aDict.takeValueForKey(category, CATEGORY);
			aDict.takeValueForKey(categoryOp, CATEGORYOPERATOR);
		}

		if (date != null) {
			aDict.takeValueForKey(date, DATERELEASED);
			if (dateOp != null) {
				if (dateOp.equals("before")) {
					dateOp = "<";
				} else if (dateOp.equals("after")) {
					dateOp = ">";
				} else if (dateOp.equals("is")) {
					dateOp = "=";
				}
				// else just use the dateOp text raw
				aDict.takeValueForKey(dateOp, DATEOPERATOR);
			}
		}

		return aDict;
	}

	/**
	 * Method to return a new movie from the request (for use with the method to add a new movie to the database. This method creates a new movie from the form information and sets up the relationship to the proper studio. The object is places into the editing context here but saved later.
	 *
	 * @param aRequest
	 *            the WORequest with the new movie form values
	 * @return the new Movie object
	 */

	public EOEnterpriseObject newMovieFromRequest(WORequest aRequest) {

		// Instantiate a new Movie from form-data
		Application anApp = Application.myApplication();
		EOClassDescription aCD = EOClassDescription.classDescriptionForEntityName("Movie");
		EOEnterpriseObject aMovie;
		try {
			EOEditingContext anEC = anApp.lockEC();
			aMovie = aCD.createInstanceWithEditingContext(anEC, null);
		} finally {
			anApp.unlockEC();
		}

		aMovie.takeValueForKey(aRequest.stringFormValueForKey(TITLE), TITLE);
		aMovie.takeValueForKey(aRequest.stringFormValueForKey(CATEGORY), CATEGORY);
		aMovie.takeValueForKey("", "posterName");
		aMovie.takeValueForKey("", "trailerName");

		// Assign revenue
		Number aNumber = aRequest.numericFormValueForKey(REVENUE, IntegerNumberFormatter);
		if (aNumber == null) {
			aNumber = new Integer(0);
		} else {
			aNumber = new Integer(aNumber.intValue());
		}
		aMovie.takeValueForKey(aNumber, REVENUE);

		// Assign dateReleased
		NSTimestamp aDate = aRequest.dateFormValueForKey(DATERELEASED, StandardDateFormatter);
		aMovie.takeValueForKey(aDate, DATERELEASED);

		// Assign Studio
		aNumber = aRequest.numericFormValueForKey(STUDIO, IntegerNumberFormatter);
		if (aNumber != null) {
			aNumber = new Integer(aNumber.intValue());
		}

		EOEnterpriseObject aStudio = anApp.lookupByID("Studio", aNumber);
		aMovie.takeValueForKey(aStudio, STUDIO);

		return aMovie;
	}

	/**
	 * Method to return a new Review object from the form data in the request. This method is much like the method to create a new Movie object, but does not set relationship information to anything before being returned.
	 *
	 * @param aRequest
	 *            the WORequest with the new movie form values
	 * @return the new Review object
	 */

	public EOEnterpriseObject reviewFromRequest(WORequest aRequest) {

		Application anApp = Application.myApplication();
		EOClassDescription aCD = EOClassDescription.classDescriptionForEntityName("Review");
		EOEnterpriseObject aReview;
		try {
			EOEditingContext anEC = anApp.lockEC();
			aReview = aCD.createInstanceWithEditingContext(anEC, null);
		} finally {
			anApp.unlockEC();
		}
		aReview.takeValueForKey(aRequest.stringFormValueForKey("review"), "review");
		aReview.takeValueForKey(aRequest.stringFormValueForKey("reviewer"), "reviewer");

		return aReview;
	}

	/**
	 * Method to return a Movie object based on the ID value for the movie. This method uses the cached list of movies in the Application class and, based on the form value for the ID in the request, returns the appropriate movie.
	 */

	public EOEnterpriseObject lookupMovieIDFromRequest(WORequest aRequest) {
		Number movieID = Integer.valueOf((String) aRequest.formValueForKey(MovieComponent.MovieIDKey));
		return Application.myApplication().lookupByID("Movie", movieID);
	}

	/**
	 * Method to return a Review object based on the ID value for the review. This method uses the cached list of reviews in the Application class and, based on the form value for the ID in the request, returns the appropriate review.
	 */

	public EOEnterpriseObject lookupReviewIDFromRequest(WORequest aRequest) {
		Number reviewID = Integer.valueOf((String) aRequest.formValueForKey("reviewID"));
		return Application.myApplication().lookupByID("Review", reviewID);
	}
}
