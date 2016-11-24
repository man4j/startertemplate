<#import "/lib/composition.ftlh" as ui />
<#import "/spring.ftl" as spring />
<#import "/lib/util.ftlh" as util />
<#import "/component/error_block.ftlh" as errorBlock />

<@ui.composition "/base.ftlh">  
  <#assign title in ui>
    Signup
  </#assign>
  
  <#assign content in ui>
    <@errorBlock.show />
    
    <div>
      <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
          <form class="form-signin" role="form" action="" method="post" autocomplete="off">
            <h3 class="form-signin-heading"><@spring.messageText 'signin.signup' 'Регистрация'/></h3>
  	  
  	        <label><@spring.messageText 'signup.email' 'E-mail'/></label>	    
  	        <@spring.formInput 'form.email' 'class="${util.errClass("form.email", "err form-control", "form-control")}" maxlength="50" autocomplete="off"' />
  	        
  	        <label><@spring.messageText 'signup.password' 'Пароль'/></label>	    
  	        <@spring.formPasswordInput 'form.password' 'class="${util.errClass("form.password", "err form-control", "form-control")}" maxlength="18" autocomplete="off"'/>
  	        
  	        <label><@spring.messageText 'signup.confirmPassword' 'Подтвердите пароль'/></label>	    
  	        <@spring.formPasswordInput 'form.confirmPassword' 'class="${util.errClass("form.confirmPassword", "err form-control", "form-control")}" maxlength="18" autocomplete="off"'/>       
  	      
	          <div class="height: 20px;">&nbsp;</div>        
  	        <button type="submit" class="btn btn-lg btn-primary btn-block"><@spring.messageText 'button.save' 'Сохранить' /></button>
      	  </form>
  		  </div>
  	  </div>
  	</div>
  </#assign>

</@ui.composition>
