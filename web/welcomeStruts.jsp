<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <html:base/>
    </head>
    <body style="background-color: blueviolet">
        
        <logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
            <div  style="color: red">
                ERROR:  Application resources not loaded -- check servlet container
                logs for error messages.
            </div>
        </logic:notPresent>
        
        <h3><bean:message key="welcome.heading"/></h3>
        <p><bean:message key="welcome.message"/></p>
        <hr>
        <h1><center>FastLearn Login Page</center></h1>
        <center><img alt=""  src="STICKMAN.gif" /></center>
        <form action="./roleBasedRedirection"  name="form1">
            <hr>
            Enter your login:<input type="text" name="login" value="admin" /><br>
            Enter your password:<input type="password" name="password" value="password" /><br><br>
            You are logging in as:<select name="role">
                <option >student</option>
                <option>faculty</option>
                <option>administrator</option>
            </select>
            <hr>
            <input type="submit" value="LOGIN" />
            <input type="reset" value="CANCEL" /><br>
        </form>
        <hr>

        
    </body>
</html:html>
