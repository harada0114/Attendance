<!-- 勤怠状況一覧画面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.CreateDayLogic" %>    
    
    <%
    String random_word = (String) request.getAttribute("random_word");
    String mail = (String) request.getAttribute("mail");
    int year = (int) request.getAttribute("year");
    int month = (int) request.getAttribute("month");
    int count = (int) request.getAttribute("count");
    int[] calendarDay = (int[]) request.getAttribute("calendarDay");
    
    String name = (String) session.getAttribute("staff.getName()");
    
    int startDayNo = 0;
    int i;
	%>
    
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>勤怠状況</title>
	
	<style>
		table {
			border:1px solid #a9a9a9; 
			width:90%; 
			padding:0px;
			margin:0px; 
			border-collapse:collapse;
		}		
        td {
        	width:12%;
        	border-top:1px solid #a9a9a9;
        	border-left:1px solid #a9a9a9;
        	vertical-align:top;
        	margin:0px;
        	padding:2px;
        }
        td.week {
        	background-color:#f0f8ff;
        	text-align:center;
        }
        td.day {
        	background-color:#f5f5f5;
        	text-align:right;
        	font-size:0.75em;
        }
        td.otherday { 
        	background-color:#f5f5f5;
        	color:#d3d3d3;
        	text-align:right;
        	font-size:0.75em;
        }
        td.sche { 
        	background-color:#fffffff;
        	text-align:left;
        	height:80px;
        }
        img { 
        	border:0px;
        }
        span.small {
        	font-size:0.75em;
        }
        </style>
	</head>
	
	<body>
	
		<% System.out.println("JSPで受けとった値"); %>
		<% System.out.println("mail="+mail); %>
		<% System.out.println("year="+year); %>
		<% System.out.println("month="+month); %>
		<% System.out.println("count="+count); %>
		<% System.out.println("calendarDay="+calendarDay); %>
	
		<p><%= name %>さんの勤怠状況です。</p>
		
		<p>
		<!-- 先月ボタン -->
        <form action='CalendarServlet' method='post'>
        	<input type='hidden' name='random_word' value='<%= random_word %>'>
        	<input type='hidden' name='YEAR' value='<%= year %>'>
        	<input type='hidden' name='MONTH' value='<%= month - 1 %>'>
        	<span class='small'><input type='submit' value='前月'></span>
        </form>
        
		<!-- 今月の表示 -->
        <%= year %>年
        <%= month + 1 %>月

		<!-- 翌月ボタン -->
        <form action='CalendarServlet' method='post'>
        	<input type='hidden' name='random_word' value='<%= random_word %>'>
        	<input type='hidden' name='YEAR' value='<%= year %>'>
        	<input type='hidden' name='MONTH' value='<%= month + 1 %>'>
			<span class='small'><input type='submit' value='翌月'></span>
		</form>
        </p>
        
        <table>
        <!-- 日〜土 -->
        	<tr>
        		<td class='week'>日</td>
        		<td class='week'>月</td>
        		<td class='week'>火</td>
        		<td class='week'>水</td>
        		<td class='week'>木</td>
        		<td class='week'>金</td>
        		<td class='week'>土</td>
			</tr>
        
        <!-- 日付表示部分 -->
        <% int weekCount = count / 7; %>
        <% System.out.println("weekCount="+weekCount); %>
       
        <!-- 行 -->
        <% for (i = 0 ; i < weekCount ; i++) { %> <!-- 親 -->
        	<% System.out.println("i="+i+"行"); %>
       
            <tr>
			
			<!-- 列 -->
			<% for (int j = i * 7 ; j < i * 7 + 7 ; j++) { %> <!-- 子 -->
            	<% System.out.println("j="+j+"列"); %>
            	<% if (calendarDay[j] > 35) { %>
                    <td class='otherday'><%= calendarDay[j] - 35 %>     <!-- 日数を表示 -->
                    													
            	<% } else { %>
                    <td class='day'><%= calendarDay[j] %>
                <% } %>
                
                </td>
                
            <% } %> <!-- 子ここまで -->
            
            </tr>

            <% System.out.println("i * 7="+i * 7); %>
			<% System.out.println("mail="+mail); %>
			<% System.out.println("1日作成 i="+i); %>
			
            <% CreateDayLogic find = new CreateDayLogic(); %>
            <% String day = find.OnDay(year, month, i * 7, calendarDay, mail); %>
			<%= day %> <!-- 表示 -->
		
		<% } %> <!-- 親ここまで -->

        </table>
        
    <p>
  	<form action="MainServlet" method="post">
		<input type="hidden" name="random_word" value="<%= random_word %>">
		<input type="submit" name="MySubmit" value="戻る">
	</form>
	</p>
	
	</body>
	</html>