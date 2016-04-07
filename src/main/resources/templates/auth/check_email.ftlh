<#import "/lib/composition.ftlh" as ui />
<#import "/spring.ftl" as spring />

<@ui.composition "/base.ftlh">  
  <#assign title in ui>
    Signup
  </#assign>

  <#assign content in ui>
    <@spring.messageText 'signup.checkMail' 'Проверьте почту!'/>
  </#assign>
</@ui.composition>
