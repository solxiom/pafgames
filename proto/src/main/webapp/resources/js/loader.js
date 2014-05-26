/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
(function() {
    $.paf.package("pafGames");
    "use strict";

    var _loader = CoderLeopard.pafGames.Loader = function() {

    }


    _loader.prototype.getResourcesRoot = function() {
        return $.paf.url.root_path + "/resources/";
    };

    _loader.prototype.setRootForLocalLinks = function() {
        this.setImagesRoot();
    };

    _loader.prototype.setImagesRoot = function() {
        var _self = this;
        $("img.x-local").each(function() {
            $(this).attr("src", _self.getResourcesRoot() + $(this).attr("src"));
            $(this).removeClass("x-local");
        });
    };
    _loader.prototype.getMetaDataAndLoad = function() {

        var _self = this;
        var head_meta_path = _self.getResourcesRoot() + "/js/json/head_meta.json";

        $.ajax({
            async: "false",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            url: head_meta_path,
            success: function(data) {
                _self.loadstyles(data.styles);
                _self.loadFavicon(data.favicon);
                _self.loadScripts(data.scripts);
                $("#main").css('visibility', 'visible');
            }
        });
    };

    _loader.prototype.loadScripts = function(scripts) {
        var _self = this;
        $(scripts).each(function() {
            if (!_self.fileExists("script", "src", this.file)) {
                var script_path = _self.buildPath(this);
                $("head").append("<script type='text/javascript' src='" + script_path + "'> </script>");
            }
        });
    };
    _loader.prototype.loadstyles = function(styles) {
        var _self = this;
        $(styles).each(function() {
            if (!_self.fileExists("link", "href", this.file)) {
                var style_path = _self.buildPath(this);
                $("head").append("<link rel='stylesheet' href='" + style_path + "' type='text/css' />");
            }
        });
    };
    _loader.prototype.loadFavicon = function(favicon) {
        if (!this.fileExists("link", "href", favicon.file)) {
            var icon_path = this.buildPath(favicon);
            $("head").append("<link rel='icon' href='" + icon_path + "' type='image/png' />");
        }
    };
    _loader.prototype.loadHeader = function() {
        var _self = this;
        $("header").remove();
        $("body").prepend("<header></header>");
        $("header").load(_self.getResourcesRoot() + "/html/header.html", function() {
            _self.setRootForLocalLinks();
            $("#addButton").click(function() {
                $("#addGameDialog").css("display", "inline-block");
            });
            $("#addGameDialog input[type='button']").click(function(){
                 $("#addGameDialog").css("display", "none");
            })
             $("#type_nav > span").click(function() {
            if ($(this).attr("id") === "all") {
                $.paf.publish({key: "list_games", data: {mode: "all"}});
            } else if ($(this).attr("id") === "casino") {
                $.paf.publish({key: "list_games", data: {mode: "casino"}});
            }else if ($(this).attr("id") === "slots") {
                $.paf.publish({key: "list_games", data: {mode: "slots"}});
            }else if ($(this).attr("id") === "lotteries") {
                $.paf.publish({key: "list_games", data: {mode: "lotteries"}});
            }
            $("#type_nav > span").removeClass("choosed");
            $(this).addClass("choosed");
        });
        });
    };
    _loader.prototype.loadFooter = function() {
        var _self = this;
        $("footer").remove();
        $("body").append("<footer></footer>");
        $("footer").load(_self.getResourcesRoot() + "/html/footer.html", function() {
            _self.setRootForLocalLinks();
        });
    };
    _loader.prototype.fileExists = function(element_type, attr_name, filename) {
        var b = false;
        $(element_type).each(function() {
            if (this !== undefined && $(this).attr(attr_name) !== undefined) {
                var arr = $(this).attr(attr_name).split("/");
                if (arr[arr.length - 1] === filename) {
                    b = true;
                }
            }
        });
        return b;
    };
    _loader.prototype.buildPath = function(meta) {
        var _self = this;
        var path = _self.getResourcesRoot() + meta.path + meta.file;
        if (meta.local === "false") {
            path = meta.path + meta.file;
        }
        return path;
    };
}());