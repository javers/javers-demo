layout 'layouts/main.tpl',
pageTitle: 'Employee History',
mainBody: contents {

    div(class:"row"){
       div(class:"col-md-8"){
        div (class: "panel panel-default"){
          div (class:"panel-heading", "State history (snapshots) of employee $login")
          div (class:"panel-body"){
            form(method:'POST', class:"form-horizontal", action:'/emp-state-history'){
                div (class:"form-group"){
                  label( class:"control-label col-xs-3", for:"login", "employee")
                  div (class:"col-xs-4"){
                      input(type:"text", name:"login", class:"form-control", placeholder:"$login")
                  }
                }

                div (class:"form-group"){
                  label( class:"control-label col-xs-3", "")
                  div (class:"col-xs-4"){
                      button(class:"btn", type:"submit", "show")
                  }
                }

            }

                pre(){
                    //div("Snapshots: $snapshotsCount")
                    code(snapshots)
                }
          }
        }
       }
    }
}
