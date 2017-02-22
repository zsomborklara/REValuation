<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

  <h1>Add Real Estate</h1>
  <br />

  <spring:url value="/rev/estates/add" var="addActionUrl" />

  <form:form class="form-horizontal" method="post" modelAttribute="reForm" action="${addActionUrl}">

    <form:hidden path="id" />

    <spring:bind path="area">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <label class="col-sm-2 control-label">Area</label>
        <div class="col-sm-10">
          <form:input path="area" type="text" class="form-control " id="area" placeholder="Area" />
          <form:errors path="area" class="control-label" />
        </div>
      </div>
    </spring:bind>

    <spring:bind path="coordLatitude">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <label class="col-sm-2 control-label">Coordinates Latitude</label>
        <div class="col-sm-10">
          <form:input path="coordLatitude"  type="text" class="form-control " id="coordLatitude" placeholder="XX XX XX N/S" />
          <form:errors path="coordLatitude" class="control-label" />
        </div>
      </div>
    </spring:bind>
    
    <spring:bind path="coordLongitude">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <label class="col-sm-2 control-label">Coordinates Longitudes</label>
        <div class="col-sm-10">
          <form:input path="coordLongitude" type="text" class="form-control " id="coordLongitude" placeholder="XX XX XX E/W" />
          <form:errors path="coordLongitude" class="control-label" />
        </div>
      </div>
    </spring:bind>
    
    <spring:bind path="price">
      <div class="form-group">
        <label class="col-sm-2 control-label">Price</label>
        <div class="col-sm-10">
          <form:input readonly="true" path="price" class="form-control" id="price" placeholder="Price" />
        </div>
      </div>
    </spring:bind>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn-lg btn-primary pull-right">Calculate</button>
      </div>
    </div>
  </form:form>

</div>
</body>
</html>