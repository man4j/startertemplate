<#import "/lib/composition.ftl" as ui />
<#import "/lib/util.ftl" as util />
<#import "/spring.ftl" as spring/>

<@ui.composition "/base.ftl">  
  <#assign title in ui>
    Index  
  </#assign>
  
  <@ui.addScript>
    <script defer="defer">
    </script>
  </@ui.addScript>
  
  <#assign content in ui>
    Access Denied
    
    <hr />
    
    <a href="<@spring.url '/logout' />">Выйти</a>
  </#assign>  
</@ui.composition>