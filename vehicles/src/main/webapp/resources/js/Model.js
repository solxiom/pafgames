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

        $.paf.subscribe("save_order", function(order) {
            $.ajax({
                type: "POST",
                url: $.paf.url.root_path + "/api/order/new",
                data: JSON.stringify(order),
                dataType: "json",
                async: true,
                contentType: 'application/json; charset=utf-8',
                complete: function(msg) {
                    if (msg.status >= 400) {
                        $.paf.publish({key: "show_notification", data: "Bad Command Request! Server rejected your Command"});

                    } else {
                        $.getJSON($.paf.url.root_path + "/api/order/list", function(data) {
                            $.paf.publish({key: "colors_request", data: undefined});
                            $.paf.publish({key: "orders_request", data: undefined});
                            //to be continued....
                        })
                    }
                }
            });
        });
        $.paf.subscribe("colors_request", function() {
            $.getJSON($.paf.url.root_path + "/api/color/list", function(data) {
                $.paf.publish({key: "update_colors", data: data});
            });
        });
        $.paf.subscribe("orders_request", function() {
            $.getJSON($.paf.url.root_path + "/api/order/list", function(data) {
                $.paf.publish({key: "update_orders", data: data});
            });
        });
        $.paf.subscribe("refill_request", function() {

            $.ajax({
                type: "POST",
                url: $.paf.url.root_path + "/api/color/refill",
                dataType: "json",
                async: true,
                contentType: 'application/json; charset=utf-8',
                complete: function(msg) {
                    $.paf.publish({key: "colors_request", data: undefined});
                }
            });
        });
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
                        $("form  input[type=text]").val("");
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