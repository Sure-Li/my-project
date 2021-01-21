<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
				<c:if test="${!empty menuList }">
				<c:forEach items="${menuList}" var="menu">
					<li class="list-group-item tree-closed" >
						<a href="${menu.url}"><i class="${menu.icon}"></i> ${menu.name}</a> 
						<c:if test="${!empty menu.children}">
						<span class="badge" style="float:right">${menu.children.size()}</span>
						<ul style="margin-top:10px;display:none;">
							<c:forEach items="${menu.children}" var="menuChild">
								<li style="height:30px;">
									<a href="${menuChild.url}"><i class="${menuChild.icon}"></i> ${menuChild.name}</a>  
								</li>
							</c:forEach>
						</ul>
						</c:if>
					</li>
				</c:forEach>
					</c:if>
				</ul>
			</div>
        </div>