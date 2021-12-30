<%--
  Created by IntelliJ IDEA.
  User: HowardY
  Date: 2021/12/27
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页条开始--%>
<div id="page_nav">
    <a href="${pageScope.basePath}${ requestScope.page.url }&pageNum=1">首页</a>
    <c:if test="${requestScope.page.pageNum > 1}">
        <a href="${pageScope.basePath}${ requestScope.page.url }&pageNum=${requestScope.page.pageNum - 1}">上一页</a>
    </c:if>
<%--    <%=request.getAttribute("page")%>--%>
    <c:if test="${requestScope.page.pageTotalNum < 5}">
        <c:set var="begin" value="1" />
        <c:set var="end" value="${requestScope.page.pageTotalNum}"/>
    </c:if>
    <c:if test="${requestScope.page.pageTotalNum >= 5}">
        <c:choose>
            <c:when test="${requestScope.page.pageNum < 3}">
                <c:set var="begin" value="1" />
                <c:set var="end" value="5"/>
            </c:when>
            <c:when test="${requestScope.page.pageNum + 2 > requestScope.page.pageTotalNum}">
                <c:set var="begin" value="${requestScope.page.pageTotalNum - 4}" />
                <c:set var="end" value="${requestScope.page.pageTotalNum}"/>
            </c:when>
            <c:otherwise>
                <c:set var="begin" value="${requestScope.page.pageNum - 2}" />
                <c:set var="end" value="${requestScope.page.pageNum + 2}"/>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNum}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNum}">
            <a href="${pageScope.basePath}${ requestScope.page.url }&pageNum=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.page.pageTotalNum > requestScope.page.pageNum}">
        <a href="${pageScope.basePath}${ requestScope.page.url }&pageNum=${requestScope.page.pageNum + 1}">下一页</a>
    </c:if>
    <a href="${pageScope.basePath}${ requestScope.page.url }&pageNum=${requestScope.page.pageTotalNum}">末页</a>
    共${requestScope.page.pageTotalNum}页，${requestScope.page.itemsTotalNum}条记录 到第<input value="${param.pageNum}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $("#searchPageBtn").click(function () {
            var targetPage = $("#pn_input").val();
            // javaScript语言中提供了一个location地址栏对象
            // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
            // href属性可读，可写
            location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNum=" + targetPage;
        });
    </script>
</div>
<%--分页条开始--%>