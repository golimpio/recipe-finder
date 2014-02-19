# Recipe Finder

## Exercise

Given a list of items in the fridge (presented as a csv list), and a collection of recipes (a collection of JSON formatted recipes), produce a recommendation for what to cook tonight.


### Input
#### 1) Fridge CSVFormat: `item, amount, unit, use-by` 
Where:- ￼Item (string) = the name of the ingredient – e.g. egg) - Amount (int) = the amount- Unit (enum) = the unit of measure, values:	- of (for individual items; eggs, bananas etc) 
	- grams	- ml (milliliters)	- slices
- Use-By (date) = the use by date of the ingredient (dd/mm/yy)￼￼￼￼￼￼
E.g.
	bread,10,slices,25/12/2014	cheese,10,slices,25/12/2014	butter,250,grams,25/12/2014	peanut butter,250,grams,2/12/2014	mixed salad,150,grams,26/12/2013


#### 2) Recipes JSON
Array of recipes with format specified as below:
- ￼name : String ingredients[]- item : String amount : int - unit : enumE.g.
	[ {	  "name": "grilled cheese on toast",	  "ingredients": [	   { "item":"bread", "amount":"2", "unit":"slices"},	   { "item":"cheese", "amount":"2", "unit":"slices"}	  ]	} , {	  "name": "salad sandwich",	  "ingredients": [	   { "item":"bread", "amount":"2", "unit":"slices"},	   { "item":"mixed salad", "amount":"100", "unit":"grams"}	  ]	} ]


## Application

This application provides a lightweight RESTful API using the Java API for RESTful Web Services (JAX-RS) on top of an embedded Jetty (based on the Heroku template for JAX-RS).

A deployed version is running and hosted on: [http://recipe-finder.herokuapp.com/](http://recipe-finder.herokuapp.com/)


### Frameworks

Server:

- [Jetty - Servlet Engine and HTTP Server](http://www.eclipse.org/jetty/)
- [Jersey - RESTful Web Services in Java](https://jersey.java.net/)
- [FasterXML - Jackson JAX-RS JSON Provider](https://github.com/FasterXML/jackson-jaxrs-json-provider)

Test:

- [TestNG](http://testng.org/)
- [AssertJ - Fluent assertions for Java](http://joel-costigliola.github.io/assertj/index.html)


### Target platforms

- [Heroku](https://www.heroku.com/)
- Java stand alone application


### Minimum requirements

- Java 7
- Maven 3


### Running the application locally

First build with:

    $ mvn clean package

Then run it with:

    $ mvn exec:java -Dexec.mainClass="com.example.Main"
    
Alternatively, there is a *run* script at the project root folder:

	$ ./run


## API

The REST API can be accessed from: `http://recipe-finder.herokuapp.com/services`

There are two available services, both expose two operations:

1. **fridge** (list stored items - *GET*)
2. **fridge/add** (add new items to the fridge, replacing the existing ones - *POST*)
3. **recipes** (list available recipes - *GET*)
4. **recipes/add** (add new recipes to the fridge, replacing the existing ones - *POST*)

If the request succeeds, the response’s content type will be *JSON*.


### Command line interface

The simplest way to access the service API from the command line is via CURL (a command line tool for transferring data with URL syntax).


#### Add new items to the fridge

**Request:**

	curl -X POST -H "Content-Type: text/plain" -d $'bread,10,slices,25/12/2014\ncheese,5,slices,3/2/2015' http://recipe-finder.herokuapp.com/services/fridge/add

The **response** will be a JSON with a suggestion for dinner:

	{
	    "message": "Suggestion for dinner",
	    "recipe": {
	        "name": "grilled cheese on toast",
	        "ingredients": [
	            {
	                "item": "bread",
	                "amount": 2,
	                "unit": "slices"
	            },
	            {
	                "item": "cheese",
	                "amount": 2,
	                "unit": "slices"
	            }
	        ]
	    }
	}

If there is no match for the ingredients in the fridge, a *null* recipe will be returned, e.g.

	{
	    "message": "Order Takeout",
	    "recipe": null
	}

Sending an invalid content will result on a status ***400** (Bad Request)* with the following message:

	Failed to parse line 2. Item should have exactly 4 fields: [cheese, 5, slices, 3/2/2015, xxx]


### List items added to the fridge

**Request:**

	curl -X GET -H "Content-Type: application/json" http://recipe-finder.herokuapp.com/services/fridge

**Response:**

	[
	    {
	        "item": "bread",
	        "amount": 10,
	        "unit": "slices",
	        "useBy": "25/12/2014"
	    },
	    {
	        "item": "cheese",
	        "amount": 5,
	        "unit": "slices",
	        "useBy": "3/2/2015"
	    }
	]


### Add new recipes

**Request:**

	curl -X POST -H "Content-Type: application/json" -d $'[ { "name": "grilled cheese on toast", "ingredients": [ { "item":"bread", "amount":"2", "unit":"slices"}, { "item":"cheese", "amount":"2", "unit":"slices"} ] } , { "name": "salad sandwich", "ingredients": [ { "item":"bread", "amount":"2", "unit":"slices"}, { "item":"mixed salad", "amount":"100", "unit":"grams"} ] } ]' http://recipe-finder.herokuapp.com/services/recipes/add

The **response** will be the same as described for the fridge service.


### List all recipes

**Request:**

	curl -X GET -H "Content-Type: application/json" http://recipe-finder.herokuapp.com/services/recipes

**Response:**

	[
	    {
	        "name": "grilled cheese on toast",
	        "ingredients": [
	            {
	                "item": "bread",
	                "amount": 2,
	                "unit": "slices"
	            },
	            {
	                "item": "cheese",
	                "amount": 2,
	                "unit": "slices"
	            }
	        ]
	    },
	    {
	        "name": "salad sandwich",
	        "ingredients": [
	            {
	                "item": "bread",
	                "amount": 2,
	                "unit": "slices"
	            },
	            {
	                "item": "mixed salad",
	                "amount": 100,
	                "unit": "grams"
	            }
	        ]
	    }
	]


### Sending a file instead

For example, sending fridge items from a file located at ‘*/tmp/items.txt*’

**Request:**

	curl -X POST -H "Content-Type:text/plain" --data-binary "$(</tmp/items.txt)" http://recipe-finder.herokuapp.com/services/fridge/add

Sending recipes from a file located at ‘*/tmp/recipes.json*’

**Request:**

	curl -X POST -H "Content-Type:application/json" --data-binary "$(</tmp/recipes.json)" http://recipe-finder.herokuapp.com/services/recipes/add

