layout 'layouts/main.tpl',
pageTitle: 'edit Hierarchy',
mainBody: contents {
    h3("Edit hierarchy $hierarchy.hierarchyName")

    pre(){
        code(hierarchy.print())
    }

div(class:"row"){
   div(class:"col-md-6"){
    div (class: "panel panel-default"){
      div (class:"panel-heading", 'Change boss')
      div (class:"panel-body"){
        form(method:'POST', class:"form-horizontal", action:'/hierarchy-edit/change-boss'){
            div (class:"form-group"){
              label( class:"control-label col-xs-3", for:"subordinateLogin", "subordinate")
              div (class:"col-xs-4"){
                  input(type:"text", name:"subordinateLogin", class:"form-control", placeholder:"")
              }
            }

            div (class:"form-group"){
              label( class:"control-label col-xs-3", for:"newBossLogin", "new boss")
              div (class:"col-xs-4"){
                  input(type:"text", name:"newBossLogin", class:"form-control", placeholder:"")
              }
            }

            div (class:"form-group"){
              label( class:"control-label col-xs-3", "")
              div (class:"col-xs-4"){
                  button(class:"btn", type:"submit", "change")
              }
            }

        }
      }
    }
   }
   div(class:"col-md-6"){
       div (class: "panel panel-default"){
             div (class:"panel-heading", 'Update employee')
             div (class:"panel-body") {
                 form(method:'POST', class:"form-horizontal", action:'/hierarchy-edit/update-position'){
                     div (class:"form-group"){
                       label( class:"control-label col-xs-3", for:"empLogin", "employee")
                       div (class:"col-xs-4"){
                           input(type:"text", name:"empLogin", class:"form-control", placeholder:"")
                       }
                     }

                     div (class:"form-group"){
                       label( class:"control-label col-xs-3", for:"newPosition", "new position")
                       div (class:"col-xs-4"){
                           input(type:"text", name:"newPosition", class:"form-control", placeholder:"")
                       }
                     }

                      div (class:"form-group"){
                        label( class:"control-label col-xs-3", for:"newSalary", "new salary")
                        div (class:"col-xs-4"){
                            input(type:"text", name:"newSalary", class:"form-control", placeholder:"")
                        }
                      }

                     div (class:"form-group"){
                       label( class:"control-label col-xs-3", "")
                       div (class:"col-xs-4"){
                           button(class:"btn", type:"submit", "change")
                       }
                     }

                 }

             }
       }
  }
}
}