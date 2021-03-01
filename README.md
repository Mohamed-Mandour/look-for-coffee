**Look For venue (coffee)**

## Prerequisites
 * Android Studio
 * Android Gradle Plugin
 * Kotlin

## How to run the project?
 * Clone/Download the project from GitHub **You must be on the master branch**.
 * Use Android Studio to import the Project.
 * After compiling and before running the project you need to add your own Foursquare Client ID & Client Secret. 
 * Search on a class named `RetrofitClient` and replace the value of  `CLIENT_ID` and  `CLIENT_SECRET` with your own. 
 * Run the project and you should see the app running
 
## Development approach and architecture
This project follows the MVVM architecture design pattern to achieve the separation of concerts between the app components and further decoupling. It divides the code into three distinct domain layers.
 * **Mode:** It represents the data and business logic include local and remote data source, model classes, repository. 
 * **View:** It consists of the UI Code(Activity, Fragment), XML. 
 * **ViewModel:** It is a bridge between the View and Model. It interacts with the Model and exposes the observable that can be observed by the View. 

## Tools, Decisions and Libraries
 * This App makes a networking call to the Foursquare’s explore API endpoint to fetch a list of coffee shop venues. `Retrofit` has been used to handle all the networking calls to the API.
 * The data that comes from the API comes in JSON format, to parse JSON data and converted into Java object, `Gson` Libabry have been used to do that.
 * To save the data into the persistence database. `Room`have has been used to create an SQLLite database.
 * To handle the background thread and all the asynchronous call  `kotlin coroutines` have been used. 
 * The observable data holder class `LiveData` have been used to hold the data between the views and the `viewModel`.
 * The `material design` guidelines have also been used to design and structure the UI as well as the use of `Picasso` for parsing the images endpoint.
 
 ## Testing
This project includes three different types of testing:
  * **Unit test:** This test is for testing the individual components. `JUnit, mockito` has been used to create and structure the tests. 
  * **Integration test:** This test is for testing mulitple components or layers in one test such as testing persistence layer and the network layer.
  * **UI test:** This test is for testing the UI component of the app, `espresso` have been used.


 ![Look for Coffee](![Screenshot_20210301-013644](https://user-images.githubusercontent.com/33729802/109442050-f86e6200-7a2e-11eb-9644-622214a4f5d1.png)
