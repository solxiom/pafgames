(function($) {
    $.paf.package("pafGames");
    /**
     * 
     * @returns {App}
     * @author Kavan Soleimanbeigi
     */
    var _app = CoderLeopard.pafGames.App = function() {
        'use strict';
        var _self = this;
        _self.loader = new CoderLeopard.pafGames.Loader();




    }
    _app.prototype.start = function() {
        'use strict';

        var _self = this;
        _self.Model = new CoderLeopard.pafGames.Model();
        _self.View = new CoderLeopard.pafGames.View();

        _self.loader.loadHeader();
        $.getJSON($.paf.url.root_path + "/api/game/list", function(data) {
//            $.each(data, function() {
//                var result = Mustache.to_html($("#gameTemp").html(), this);
//                $("#main").append(result);
//            })
            $.paf.publish({key: "update_view", data: data});
        });
        window.location = $.paf.url.root_path + "/#home";

    }
    $("document").ready(function() {
        _app = new CoderLeopard.pafGames.App();
        _app.start();

    });
}(jQuery));


