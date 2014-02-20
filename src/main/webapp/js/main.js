function loadMainFragment() {
	$("#new-body").empty();
    $("#new-body").load("main-body.html");
}

function loadFridgeFragment() {
	$("#new-body").empty();
	$("#load-items").hide();
	$("#new-body").load("fridge-body.html", function() {
        $.getJSON( "services/fridge", function() {})
        .done(function(data) {
            loadItems(data);
        })
        .fail(function() {
            $("#load-items").hide();
        })
    });
}

function loadRecipesFragment() {
    $("#new-body").empty();
    $("#new-body").load("recipes-body.html");
}

function loadItems(itemsList) {
    var itemsTemplate = _.template($('#itemsTemplate').text());
    var html = itemsTemplate({'itemsList' : itemsList});
    $("#items-body").html(html);
    $("#load-items").hide();
    $("#items").show();
}

function loadFridge() {
    disableFridgeLoadButton(true);

    var data = $("#fridgeTextArea").val()

    var posting = $.ajax({
        type: "POST",
        url: "services/fridge/add",
        data: data,
        contentType: "text/plain",
    });

    posting.done(function(response) {
        $("#fridgeInfoMessage").html(response.message);
        $("#fridgeInfo").show();
    });

    posting.fail(function(xhr) {
        $("#fridgeErrorMessage").html(xhr.responseText);
        $("#fridgeError").show();
    });

    posting.always(function() {
        disableFridgeLoadButton(false);
    });

    return false;
};

function disableFridgeLoadButton(disable) {
    if (disable) hideAlerts();
    $("#loadFridgeButton").attr('disabled', disable);
}

function loadRecipes() {
    disableRecipesLoadButton(true);

    var data = $("#recipesTextArea").val()
var obj = jQuery.parseJSON( '{ "name": "John" }' );
alert( obj.name === "John" )
    var posting = $.ajax({
        type: "POST",
        url: "services/recipes/add",
        data: data,
        contentType: "application/jspn",
    });

    posting.done(function(response) {
        $("#recipeInfoMessage").html(response.message);
        $("#recipeInfo").show();
    });

    posting.fail(function(xhr) {
        $("#recipeErrorMessage").html(xhr.responseText);
        $("#recipeError").show();
    });

    posting.always(function() {
        disableRecipesLoadButton(false);
    });

    return false;
};

function disableRecipesLoadButton(disable) {
    if (disable) hideAlerts();
    $("#loadRecipesButton").attr('disabled', disable);
}

function hideAlerts() {
    $("#recipeError").hide();
    $("#recipeInfo").hide();
    $("#fridgeError").hide();
    $("#fridgeInfo").hide();
}