<%@page import="com.googlecode.tomcatlsd.common.model.HeartBeat"%>
<%@page import="com.googlecode.tomcatlsd.lsdagent.util.injection.ManiocFramework.BeanFactory"%>
<%@page import="com.googlecode.tomcatlsd.lsdagent.service.HeartBeatService"%>
<html>
<body>
<h2>Tomcat LDS Agent is running</h2>
<br/>
<%
	HeartBeatService service = BeanFactory.getFactory().getBean(HeartBeatService.class);
HeartBeat content = service.buildHeartBeatContent();
%>
</body>
</html>
