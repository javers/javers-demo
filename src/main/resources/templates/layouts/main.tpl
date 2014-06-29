yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title(pageTitle)
        link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
    }
body {
        div(class: 'container') {

            nav(class: 'navbar navbar-default', role:'navigation') {
              div( class:"container-fluid") {
              div( class:"navbar-header"){
                              a(class: 'brand, col-lg-4, col-lg-8', href: 'http://javers.org'){
                                   img(src: '/img/javers_big.png', width:'200')
                               }
              }

              div( class:"collapse navbar-collapse"){
                ul(class: 'nav navbar-nav') {

                    li(){
                            a (href:'/', 'Home')
                    }

                    li(class:"dropdown"){
                            a (class:"btn dropdown-toggle", 'data-toggle':'dropdown', href:'#') {
                                span('Features')
                                span(class:'caret')
                            }
                            ul (class: 'dropdown-menu') {
                                li{
                                    a(href:'/object-diff', "&bullet; Object diff")
                                }
                                li (class: 'divider', role: 'presentation')
                                li{
                                   a(href:"#"){
                                        b("Data auditing")
                                   }
                                }
                                li{

                                    a(href:'/hierarchy-edit','&bullet; edit hierarchy')
                                }
                                li{
                                    a(href:'/emp-history','&bullet; show employee history')
                                }
                            }
                    }

                }}}
            }


    mainBody()
    }

    script(src: "/js/jquery.min.js"){}
    script(src: "/js/bootstrap.min.js"){}
}
}
