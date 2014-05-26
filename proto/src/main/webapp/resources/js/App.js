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
        _self.loader.loadHeader();
        $.getJSON($.paf.url.root_path+"/api/game/list",function(data){
           $.each(data,function(){
               var result = Mustache.to_html($("#gameTemp").html(),this);
               $("#main").append(result);
           })
        });
        window.location = $.paf.url.root_path+"/#home";

    }
}(jQuery));


