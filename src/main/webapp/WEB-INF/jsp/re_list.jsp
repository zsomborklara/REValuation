<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

  <div class="container">

    <h1>All Real Estates</h1>

    <table class="table table-striped">
      <thead>
        <tr>
          <th>#ID</th>
          <th>Area (m2)</th>
          <th>Location</th>
          <th>Price</th>
        </tr>
      </thead>

      <c:forEach var="realEstate" items="${realEstates}">
        <tr>
          <td>${realEstate.id}</td>
          <td>${realEstate.area}</td>
          <td>${realEstate.coordLatitude} - ${realEstate.coordLongitude}</td>
          <td>${realEstate.price}</td>
        </tr>
      </c:forEach>
    </table>

  </div>

</body>
</html>