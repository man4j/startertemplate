<#import "/lib/composition.ftl" as ui />
<#import "/spring.ftl" as spring />
<#import "/lib/util.ftl" as util />
<#import "/component/error_block.ftl" as errorBlock />

<@ui.composition "/base.ftl">
  <#assign title in ui>
    Restore
  </#assign>
  
  <#assign content in ui>    
	  <@errorBlock.show />
    
    <div>
      <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
          <form class="form-signin" role="form" action="<@spring.url '/auth/restore' />" method="post">
            <h3 class="form-signin-heading"><@spring.messageText 'auth.restorePassword' 'Восстановление пароля' /></h3>          
            
            <label><@spring.messageText 'auth.email' 'E-mail'/></label>
            <@spring.formInput 'form.email' 'class="${util.errClass("form.email", "err form-control", "form-control")}" maxlength="50"'/>
      
            <div>&nbsp;</div>
      
            <button type="submit" class="btn btn-lg btn-primary btn-block"><@spring.messageText 'auth.email.send' 'Выслать' /></button>
          </form>
        </div>
      </div>
    </div>
  </#assign>
</@ui.composition>
