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

This application provides a lightweight RESTful API using the Java API for RESTful Web Services (JAX-RS) on top of an embedded Jetty.

It was started using the Heroku template for JAX-RS.


### Frameworks

- [Jersey - RESTful Web Services in Java](https://jersey.java.net/)
- [Jetty - Servlet Engine and HTTP Server](http://www.eclipse.org/jetty/)


### Target platform

- [Heroku](https://www.heroku.com/)


### Minimum requirements

- Java 7


### Running the application locally

First build with:

    $ mvn clean install

Then run it with:

    $ mvn exec:java -Dexec.mainClass="com.example.Main"


## API



