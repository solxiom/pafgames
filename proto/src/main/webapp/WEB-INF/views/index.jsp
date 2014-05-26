<%-- 
    Document   : index
    Created on : Dec 15, 2013, 10:29:53 PM
    Author     : kavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favourite games</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/style/standard_style.css" type="text/css">
        <!--Development Scripts start -->
        <!-- external -->
        <script src="<%=request.getContextPath()%>/resources/js/lib/jquery-2.0.0.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/lib/mustache.min.js"></script>
        <!-- -->

        <script src="<%=request.getContextPath()%>/resources/js/root.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/js/App.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/js/loader.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/js/init.js" type="text/javascript"></script>

    </head>
    <body>
        <div id="main" > 

        </div>  
         <div id="addGameDialog">
             <form>
                 <span> Game name: <input type="text" /></span>
                 <span> Game Link: <input type="text" /></span>
                 <span> Game poster: <input type="text" /></span>
                 <span> Type: <input type="text" /></span>
                 <span> Stars: <input type="text" /></span>
                 <span><input type="button" value="cancel" /><input type="submit" value="add new game" /></span>
             </form>
         </div>
        <script id="addGameTemp" type="text/template">
           
            
        </script>
        <script id="gameTemp" type="text/template">
            <div class="game_div">
                <img src="{{imgLink}}" />
                <h3>{{name}}</h3>
                <span>{{gameType}}</span>
               
                <span>Score: {{stars}}</span>
                <a href="{{playLink}}">play</a>
            </div>
        </script>
    </body>
</html>
