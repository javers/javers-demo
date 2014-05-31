yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title(pageTitle)
        link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
    }
body {
        div(class: 'container') {
            div(class: 'navbar') {
                div(class: 'navbar-inner') {
                    a(class: 'brand, col-lg-4, col-lg-8', href: 'http://javers.org'){
                        img(src: 'img/javers_big.png', width:'200')
                    }

                    div (class:"btn-group", style:'margin-left:20px') {
                        a (class:"btn dropdown-toggle", 'data-toggle':'dropdown', href:'#') {
                            span('Features')
                            span(class:'caret')
                        }
                        ul (class: 'dropdown-menu') {
                            li{
                                a(href:'/object-diff', "object diff")
                            }
                        }
                    }
              }
        }
    mainBody()
}

    script(src: "/js/jquery.min.js"){}
    script(src: "/js/bootstrap.min.js"){}
}
}
