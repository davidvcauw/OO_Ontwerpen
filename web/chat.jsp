<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: David VC
  Date: 19/02/2019
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Chat" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat" />
</jsp:include>

<main>
    <p>Status:</p>
    <div id="status"></div>


    <select id="statusInput">
        <option value="online">Online</option>
        <option value="offline">Offline</option>
        <option value="away">Away</option>
        <option value="custom">Custom</option>
    </select>
    <input type="text" id="statusCustomInput"/>
    <input type="button" id="setStatus" value="Set status" onclick="setStatus()"/>


    <!--<table>
        <caption  class="clickable_row" data-friend="testing friend">friends</caption>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Status</th>
        </tr>-->
        <div  id="friendslist">
        <!--<c:forEach items="${friends}" var="friend">
            <div class="clickable_row" data-friend="${friend.userId}">
                <p>${friend.firstName}</p>
                <p style="position: ">${friend.status}</p>
                <p>${friend.lastName}</p>
                <div class="chatbox">
                    <p>testing</p>
                </div>
            </div>
        </c:forEach>-->
        </div>

    <!--</table>-->

    <div id="possiblefriend"></div>

    <div id="chatdiv"></div>

    <div id="tabs">
        <ul id="tabslist">
            <!--<li><a href="#tab-1">test</a></li>
            <li><a href="#tab-2">test 2</a></li>-->
        </ul>
        <!--<div id="tab-1">
            <p>tesstttqnsmldfhqlsdhfhqsf</p>
        </div>
        <div id="tab-2">
            <p>dit is tab 2!</p>
        </div>-->
    </div>

    <script src="js/jquery-3.4.0.js"></script>

    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="js/chat.js"></script>
    <script src="js/opdracht3.js"></script>

</main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home" />
</jsp:include>
</body>
</html>
