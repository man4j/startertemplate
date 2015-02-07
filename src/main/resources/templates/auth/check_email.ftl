<#import "/lib/composition.ftl" as ui />
<#import "/spring.ftl" as spring />

<@ui.composition "/base.ftl">  
  <#assign title in ui>
    Signup
  </#assign>

  <#assign content in ui>
    <@spring.messageText 'signup.checkMail' 'Проверьте почту!'/>
  </#assign>
</@ui.composition>
