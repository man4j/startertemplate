<#import "/lib/composition.ftlh" as ui />
<#import "/spring.ftl" as spring />
<#import "/lib/util.ftlh" as util />
<#import "/component/error_block.ftlh" as errorBlock />

<@ui.composition "/base.ftlh">  
  <#assign title in ui>
    Set Email
  </#assign>

  <#assign content in ui>
    <b>Данные полученные от провайдера:</b> <br />
    
    <img src="${img}" /> <br />
    ${name}, ${email}  <br />
    
    <hr />
    
    <#if profile??>
      В системе уже зарегистрирован пользователь с данным email. Аккаунт социальной сети будет привязан к существующему аккаунту.
      
      <#assign readonly = "readonly">
    <#else>
      Укажите email для нового пользователя или пользователя в системе, с которым следуем связать аккаунт социальной сети.
    </#if>
    
    <@errorBlock.show />
    
    <div>
      <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
          <form class="form-signin" role="form" action="" method="post" autocomplete="off">
            <h3 class="form-signin-heading"><@spring.messageText 'social.signup' 'Привязка аккаунта'/></h3>
      
            <label><@spring.messageText 'signup.email' 'E-mail'/></label>     
            <@spring.formInput 'form.email' 'class="${util.errClass("form.email", "err form-control", "form-control")}" maxlength="50" autocomplete="off" ${readonly!}' />
                        
            <div class="height: 20px;">&nbsp;</div>        
            <button type="submit" class="btn btn-lg btn-primary btn-block"><@spring.messageText 'button.link' 'Привязать' /></button>              
          </form>
        </div>
      </div>
    </div>
  </#assign>
</@ui.composition>
