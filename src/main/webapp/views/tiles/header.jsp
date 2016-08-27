<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="/resources/css/templatemo_style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="/resources/css/ddsmoothmenu.css"/>
<!-- templatemo 360 bookstore -->
<!--
Bookstore Template
http://www.templatemo.com/preview/templatemo_360_bookstore
-->


<sec:authorize access="isAnonymous()">
    <a style="
        float: right;
	    width: 80px;
	    border-left: 10px solid #444443;
	    padding-left: 15px;
	    line-height: 30px;


    " href="/loginpage">
        <button style="font-size: 12pt" type="submit">Login</button>
    </a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <form:form method="post" action="/logout">
        <button type="submit" style="
            float: right;
	        width: 80px;
	        border-left: 1px solid #444443;
	        padding-left: 15px;
	        /*line-height: 30px;*/
            font-size: 12pt

    ">Logout
        </button>
    </form:form>
</sec:authorize>
<sec:authorize access="isAnonymous()">
    <a style="
            float: right;
	        width: 80px;
	        border-left: 0px solid #444443;
	        /*padding-left: 30px;*/
	        line-height: 30px;


            " href="/registration">
        <button style="font-size: 12pt" type="submit">Registraion</button>
    </a>
</sec:authorize>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/ddsmoothmenu.js">

    /***********************************************
     * Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
     * This notice MUST stay intact for legal use
     * Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
     ***********************************************/

</script>

<script type="text/javascript">

    ddsmoothmenu.init({
        mainmenuid: "templatemo_menu", //menu DIV id
        orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
        classname: 'ddsmoothmenu', //class added to menu's outer DIV
        //customtheme: ["#1c5a80", "#18374a"],
        contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
    })

</script>

<link rel="stylesheet" type="text/css" media="all" href="/resources/css/jquery.dualSlider.0.2.css"/>

<script src="/resources/js/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/resources/js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="/resources/js/jquery.timers-1.2.js" type="text/javascript"></script>
<script src="/resources/js/jquery.dualSlider.0.3.min.js" type="text/javascript"></script>

<script type="text/javascript">

    $(document).ready(function () {

        $("#carousel").dualSlider({
            auto: true,
            autoDelay: 6000,
            easingCarousel: "swing",
            easingDetails: "easeOutBack",
            durationCarousel: 1000,
            durationDetails: 600
        });

    });

</script>

<script type="text/javascript" src="/resources/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" href="/resources/css/slimbox2.css" type="text/css" media="screen"/>
<script type="text/JavaScript" src="/resources/js/slimbox2.js"></script>
<div id="templatemo_header_wrapper">
    <div id="templatemo_header">
        <div id="site_title"><a href="/">Bookstore Template</a></div>
        <div id="templatemo_menu" class="ddsmoothmenu">
            <ul>
                <li><a href="/">Головна</a></li>

                <li style="z-index: 99;"><a href="/allPizza" class="">Піци</a>
                    <ul style="display: none; top: 39px; visibility: visible;">
                        <li><span class="top"></span><span class="bottom"></span></li>

                        <li><a href="/allPizza">Всі піци</a></li>
                        <li>
                            <sec:authorize access="hasRole('ADMIN')">
                                <a href="/pizzaNew">Додати піцу</a>
                            </sec:authorize>
                        </li>

                    </ul>
                </li>
                <li><a href="/gallery">Фірмові піци</a></li>
                <li><sec:authorize access="hasRole('USER')">
                    <a href="/test">Моя піца</a>
                </sec:authorize>
                    <ul style="display: none; top: 39px; visibility: visible;">
                        <li><span class="top"></span><span class="bottom"></span></li>
                        <li><a href="/allProduct">Продукти</a></li>

                        <li>
                            <sec:authorize access="hasRole('ADMIN')">
                                <a href="/productNew">Додати продукт</a>
                            </sec:authorize>
                        </li>
                    </ul>
                </li>
                <li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <a href="/allOrder">Замовлення</a>
                    </sec:authorize>

                </li>

                <li>
                    <sec:authorize access="hasRole('USER')">
                        <a href="/pizz">Замовлення</a>
                    </sec:authorize>
                    <ul style="display: none; top: 39px; visibility: visible;">
                        <li><span class="top"></span><span class="bottom"></span></li>
                        <li> <sec:authorize access="hasRole('USER')">
                            <a href="/oneOrder">Історія</a>
                        </sec:authorize></li>
                    </ul>
                </li>

                <li style="z-index: 100;"><a href="#" class="">Про нас </a>
                    <ul style="display: none; top: 39px; visibility: visible;">
                        <li><span class="top"></span><span class="bottom"></span></li>
                        <li><a href="/contact">Контакти</a></li>
                        <li><a href="/delivery">Доставка</a></li>
                        <%--<li><a href="#submenu3">Sub menu 3</a></li>--%>
                    </ul>
                </li>
                <%--<li><a href="contact.html">Contact</a></li>--%>
            </ul>
            <br style="clear: left">
        </div> <!-- end of templatemo_menu -->
        <div class="clear"></div>
    </div> <!-- END of header -->
</div>

