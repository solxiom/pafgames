(function($) {
    $.paf.package("pafGames");
    /**
     * 
     * @returns {App}
     * @author Kavan Soleimanbeigi
     */
    var _model = CoderLeopard.pafGames.Model = function() {
        'use strict';
        var _self = this;

        $.paf.subscribe("new_game", function(game) {

            $.ajax({
                type: "POST",
                url: $.paf.url.root_path + "/api/game/new",
                data: JSON.stringify(game),
                dataType: "json",
                async: true,
                contentType: 'application/json; charset=utf-8',
                complete: function(msg) {

                    $.getJSON($.paf.url.root_path + "/api/game/list", function(data) {
                        $.paf.publish({key: "update_view", data: data});
                    })

                }
            });
        });
        $.paf.subscribe("list_games", function(params) {
            var url;
            if (params.mode === "all") {
                url = $.paf.url.root_path + "/api/game/list";
            } else {
                url = $.paf.url.root_path + "/api/game/type/" + params.mode;
            }
            $.getJSON(url, function(data) {
                $.paf.publish({key: "update_view", data: data});
            })

        })
    }


}(jQuery));