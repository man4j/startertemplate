<#import "/lib/composition.ftlh" as ui />
<#import "/spring.ftl" as spring />

<@ui.composition "/base.ftlh">  
  <#assign title in ui>
    Signup
  </#assign>

  <#assign content in ui>
    <div class="row" style="margin-top:10px;">
      <div class="span12">
        <div class="alert alert-error">        
          <@spring.messageText 'sigin.expiredLink' 'К сожалению данная ссылка уже была использована и больше не действительна.'/>
        </div>        
      </div>
    </div>      
  </#assign>
</@ui.composition>
