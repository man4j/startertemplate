<#import "/spring.ftl" as spring/>
<#import "/lib/composition.ftlh" as ui />
<#import "/lib/util.ftlh" as util />
<#import "/component/error_block.ftlh" as errorBlock />

<@ui.composition "/base.ftlh">  
  <#assign title in ui>
    Signin  
  </#assign>
  
  <@ui.addScript>
    <script defer="defer">
      $(document).ready(function() {
        $(".connectButton").click(function(event){
          event.preventDefault();
          
          var w = 900;
          var h = 600;
          
          var left = (screen.width/2)-(w/2);
          var top = (screen.height/2)-(h/2);
          
          window.open("", "connectWindow", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
          
          var cTP = $(event.currentTarget).parent();    
          
          cTP[0].setAttribute("target", "connectWindow");
          cTP[0].submit();
        });
      });
    </script>
  </@ui.addScript>
  
  <#assign content in ui>
    <@errorBlock.show />

    <div>
      <h2>
        <form action="<@spring.url '/auth/facebook'/>" method="POST">
          <input type="hidden" name="scope" value="public_profile,email,user_friends" />
          
          <a class="connectButton" href="#">
            <img style="width:25px; padding-right:8px;" class="" src="<@util.r '/img/facebook_flat.png' />" alt="Facebook" />
          </a>
        </form>
        
        <@spring.messageText "signin.enter" "Вход" />
      </h2>
      
      <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
          <form class="form-signin" role="form" action="<@spring.url '/auth/signin' />" method="post">
            <h3 class="form-signin-heading"><@spring.messageText 'signin.in' 'Вход'/></h3>
            
            <label><@spring.messageText 'signin.email' 'E-mail'/></label>
            <@spring.formInput 'form.email' 'type="email" class="${util.errClass("form.email", "err form-control", "form-control")}" maxlength="50"'/>
                            
            <label><@spring.messageText 'signin.password' 'Пароль'/></label>
            <@spring.formPasswordInput 'form.password' 'class="${util.errClass("form.password", "err form-control", "form-control")}" maxlength="50"'/>     
              
            <a href="<@spring.url '/auth/restore' />"><@spring.messageText 'signin.restore' 'Забыли пароль?'/></a>
              
            <div class="checkbox">
              <label for="rememberMe">          
                <@spring.formCheckbox "form.rememberMe" />
                <@spring.messageText "signin.rememberMe" "Запомнить меня" />
              </label>
            </div>
              
            <button id="submitButton" type="submit" class="btn btn-lg btn-primary btn-block"><@spring.messageText 'signin.send' 'Войти'/></button>
          </form>          
        </div>
        <div class="row">
          <div class="col-xs-4 col-xs-offset-4" style="background-color: white; border-width: 0px;">
            <a href="<@spring.url '/auth/signup' />"><@spring.messageText 'signin.restore' 'Регистрация'/></a>
          </div>
        </div>
      </div>
    </div>
  </#assign>

</@ui.composition>