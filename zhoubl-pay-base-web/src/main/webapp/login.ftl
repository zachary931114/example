<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>login</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body>
<@spring.message code="welcome" /><br>

<form action="login/login" method="post">
    <input type="text" name="username"/>
<@spring.bind "loginParam.username" />
<@spring.showErrors "<br>"/>
    <input type="password" name="pwd"/>
<@spring.bind "loginParam.pwd" />
<@spring.showErrors "<br>"/>
    <input type="text" name="code"/>
<@spring.bind "loginParam.code" />
<@spring.showErrors "<br>"/>
    <img alt="验证码" src="kaptcha">
    <input type="submit" value="提交"/>
</form>
</body>
</html>
