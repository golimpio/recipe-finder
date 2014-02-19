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
