<html>
  <head>

  </head>

  <body>    
    Для получения доступа к Вашему аккаунту перейдите пожалуйста по ссылке:
    <a href="${baseUrl}/auth/email?uuid=${uuid?url}<#if socialUserId??>&socialUserId=${socialUserId}</#if>">${baseUrl}/auth/email?uuid=${uuid?url}<#if socialUserId??>&socialUserId=${socialUserId}</#if></a>
    <br />
    
    <#if !socialUserId??>
      Ваш пароль:
      <b>${password}</b>
    </#if>
  </body>
</html>