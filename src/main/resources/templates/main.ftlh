<#import "/lib/composition.ftlh" as ui />
<#import "/lib/util.ftlh" as util />
<#import "/spring.ftl" as spring/>

<@ui.composition "/base.ftlh">  
  <#assign title in ui>
    Index  
  </#assign>
  
  <@ui.addScript>
    <script defer="defer">
    </script>
  </@ui.addScript>
  
  <#assign content in ui>
    Index page
    
    <hr />
    
    <a href="<@spring.url '/logout' />">Выйти</a>
  </#assign>  
</@ui.composition>