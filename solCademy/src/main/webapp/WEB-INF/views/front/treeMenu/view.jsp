<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    th,td { border: 1px solid #000; padding: 10px; }
    th { background: #ddd; }
    input, select { width: 100%; }
</style>

  <table>
    <tr>
      <th>메뉴명</th>
      <td>
        <input type="text">
      </td>
      <th>메뉴url</th>
      <td colspan="3">
        <input type="text">
      </td>
    </tr>
    <tr>
      <th>메뉴설명</th>
      <td colspan="5">
        <input type="text">
      </td>
    </tr>
    <tr>
      <th>단계1</th>
      <td>
        <select>
            <c:foreach var="result" items="${result.menuDepth1 == 0}">
                <option value=""></option>
            </c:foreach>
        </select>
      </td>
      <th>단계2</th>
      <td>
        <select>

        </select>
      </td>
      <th>단계3</th>
      <td>
        <select>

        </select>
      </td>
    </tr>
    <tr>
      <th>순서</th>
      <td>
        <input type="text">
      </td>
      <th>팝업여부</th>
      <td>
        <select>
          <option>Y</option>
          <option>N</option>
        </select>
      </td>
      <th>사용여부</th>
      <td>
        <select>
          <option>Y</option>
          <option>N</option>
        </select>
      </td>
    </tr>
    <tr>
      <th>추가파라미터</th>
      <td colspan="5">
        <input type="text">
      </td>
    </tr>
  </table>

<script>


</script>