# Symphony Home Assignment

This is my take on the Symphony Home Assignment.

It follows the Clean-architecture paradigm, adapted to be more Android-friendly.

It is composed of these main layers:
- core (common classes used on all layers)
	- dependency injections
	- framework/platform classes
	- utility classes
- data (everything related to data source)
	- dto entities
	- api
	- database
	- repositories
- domain (the business logic)
	- use cases
	- domain entities
- presentation (the UI)
	- view models
	- fragments
	- views
	- adapters

## Features

- Retrieve data from the network via Retrofit and store it in database via Room
- Pagination with error / loading states
- Full dark mode support (I really recommend launching the application in dark mode, it is much nicer :wink: )

## Depedencies

- Retrofit (network engine) https://github.com/square/retrofit
- OkHttp (network layer) https://github.com/square/okhttp
- Glide (image downloading / caching) https://github.com/bumptech/glide
- Dagger (dependency injection) https://github.com/google/dagger

### Jetpack:

- Navigation : https://developer.android.com/guide/navigation
- Room : https://developer.android.com/topic/libraries/architecture/room
- Paging 3 : https://developer.android.com/topic/libraries/architecture/paging/v3-overview

## API description

Api description can be found at https://sym-json-server.herokuapp.com/

## Lack of Unit tests

You will see that this project is lacking unit / integration / ui tests.
I have to admit that this project does not fullfill the "Testing" requirement of the home assignment.

With more time, I would have done the following to implement a basic test suite:
- Created a TestApplication.kt class with Test version of the modules used by Dagger to inject mock versions of the database and API so that I can start testing in a control environment.
- Added an Espresso tests to check that a given item in the author, post and comment lists is well populated with the right piece of data.
- Added an Espresso test that go though all the 3 screens of the application
- Added an Espresso test to check the display of the empty states in case of no data to display
- Created unit tests to check the addition / deletion / retrieval of data in the Room database
- Created unit tests to check the conversion from JSON to entity at the API level

## What to do next

If I had more time, I would have added the following features to the project:
- **More testing!!**
- **Split the code into modules** representing the different layers of the application (core/data/domain/presentation), to better enforce clean separation
- **Search input in the authors list**, as it is quite tedious to search for an author at the end of the list
- **Sort menu on all entities** (by name, date, ...) to be able to sort authors, posts and comments.
- **Transitions with shared elements** between the screens
- **Deeplinks to directly access an author or a post** (I will need to add methods in the DAO/Api/Repository/UseCase/ViewModel to retrieve the data from a given author/post because right know, everything is retrieve as Lists). Then the deeplinks would be added to the Jetpack Navigation Graph.
- **A better separation between API and DB entities**: currently, for simplicity's sake, I've used the same entities for DB and API. A better approach would have been to used 2 different sets of entities, to be able to change the DB or API implementation more easily.
- **Use Coroutine/Flow instead of LiveData to retrieve/process the data:** Paging Library 3 is better suited to work with Coroutine / Flow than LiveData (even if it's working), but I'm currently not well versed into Coroutines yet, so I decided to go with LiveData
- Add an informative snackbar / toast in case we lost the network

## My thoughts on Paging Library 3

Starting this project, I wanted to test the **Jetpack Paging Library 3** (currently in alpha), as on paper, it seems much more refined, with a much nicer API than version 2.
It was working well at first, but when I started adding the loading indicators / error messages on pagination, it started to behave weirdly. Some pages of data were not retrieved in the right order, causing the data to stutter and flicker. I managed to get it "working" but it took me some precious hours. 
If I had to start over, I think I would have gone with the battle-tested version 2 of the Jetpack Paging Library.
Moreover, when first launching the application without network, the paging library do not propagate the error message and thus just displays an empty list, which is not ideal.
