(function($) {
    $.paf.package("pafGames");
    /**
     * 
     * @returns {App}
     * @author Kavan Soleimanbeigi
     */
    var _view = CoderLeopard.pafGames.View = function() {
        'use strict';
        var _self = this;
        $("#addGameDialog form").on("submit", function(event) {
            event.preventDefault();
            $.paf.publish({key: "new_game", data: {name: $("#gameName").val(),
                    imgLink: $("#imgLink").val(),
                    playLink: $("#playLink").val(),
                    gameType: $("#gameType").val(),
                    stars: $("#gameStars").val()}
            });
        });
       
        $.paf.subscribe("update_view", function(data) {
            $("#main").empty();
            $.each(data, function() {
                $("#addGameDialog").css("display", "none");
                var result = Mustache.to_html($("#gameTemp").html(), this);
                $("#main").prepend(result);
            })
        });

    }


}(jQuery)
        );