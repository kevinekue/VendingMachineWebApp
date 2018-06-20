<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <!--<link rel="stylesheet" href="css/bootstrap.css">-->
    </head>

    <body>
        <div class="container">
            <h1 class="text-center"> Vending Machine </h1>
            <hr>
            <div class="row">
                <div class="col-md-9">
                    <div class="options" >
                        <form action="pickItem" method="POST">
                        <c:forEach var="item" items="${items}" >
                            
                            <button type="submit" class="itemToChoose" style="margin: 3%" value= "${item.itemID}"  name="testid">
                                <p name ="itemId" style="vertical-align:top; text-align: left"><c:out value="${item.itemID}"/></p>
                                <p name ="itemName" style="text-align: center"><c:out value="${item.itemName}"/></p>
                                <p name = "itemPrice" style="text-align: center"> $ <c:out value="${item.itemPrice}"/></p>
                                <p name = "itemQuantity" style="vertical-align: bottom"> Quantity Left: <c:out value="${item.itemStock}"/></p>
                            </button>
                        </c:forEach>
                        </form>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="updates">
                        <div id="Money">
                            <h1>Total $ In </h1>
                            <form:form method="POST" class="form-horizontal" id="userMoney" action="addUserMoney">
                                <div class="col-lg-10">
                                    <input class="form-control" name="add-userMoney" value = "${userMoney}">
                                </div>

                                <div id="addmoney-button-group" class="col-lg-10" >

                                    <button  type ="submit" class="ambg ; col-md-1.5" style="margin: 1%" id="add-dollar" type ="decimal" value = 1.00 name = "userMoneyy">Add Dollar</button>
                                    <button  type ="submit" class="ambg ; col-md-1.5" style="margin: 1%" id="add-quarter" type ="decimal" value = 0.25 name = "userMoneyy">Add Quarter</button>
                                    <button  type ="submit" class="ambg ; col-md-1.5" style="margin: 1%" id="add-dime" type ="decimal" value = 0.1 name = "userMoneyy">Add Dime</button>
                                    <button  type ="submit" class="ambg ; col-md-1.5" style="margin: 1%" id="add-nickel" type ="decimal" value = 0.05 name = "userMoneyy">Add Nickel</button>
                                </div>
                            </form:form>
                        </div>
                        <hr/>
                        <br>
                        <br> &nbsp;
                        <div id="Purchase" >
                            <h1>Messages </h1>
                            <form class="form-horizontal" role="form" id="messages">
                                <div class="col-lg-10">
                                    <textarea  class="form-control" id="purchase-message" >${purchaseAttemptMessage}</textarea>
                                </div>
                            </form>
                            <form class="form-horizontal" role="form" id="userPickId">
                                <div class="col-lg-10">
                                    <label for="messages" class="col-lg-4"> Item: </label>
                                    <input  class="form-control col-lg-4" id="purchase-itemId" value ="${userPickId}">
                                </div>
                            </form>
                            <div id="make-purchase-button" class="col-lg-10" >
                                <form action="makePurchase" method="POST">
                                <button type="submit" class="buyStuff" style="margin: 1%">Make Purchase</button>
                                </form>
                            </div>
                        </div>
                        <hr/>
                        <br>
                        <br> &nbsp;
                        <div id="Change">
                            <h1> Change </h1>
                            <form class="form-horizontal" id="userChange">
                                <div class="col-lg-10">
                                    <textarea type="text" class="form-control" id="get-change"> ${change} </textarea>
                                </div>
                            </form>
                            <div class="col-lg-10">
                                <form action="getChange" method="POST">
                                <button type="submit" class="col-md-1.5" style="margin: 1%" id="button-change">Get Change</button>
                                </form>
                                <!-- Hide button after calling it -->
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
        <footer>
            <hr>
            <br>
            <p>Just wanted some space down here</p>
        </footer>
        <!--<script src="js/jquery-2.2.4.min.js"></script>-->
        <!--<script src="js/bootstrap.js"></script>-->
        <!--<script src="js/home.js"></script>-->

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

