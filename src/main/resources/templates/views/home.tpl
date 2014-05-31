layout 'layouts/main.tpl',
pageTitle: 'Javers-demo',
mainBody: contents {
    h3("This is a JaVers demo App")

    span('JaVers website:')
    a(href:'http://javers.org'){code('javers.org')}
    br()

    span('JaVers on github:')
    a(href:'https://github.com/javers/javers'){code('github.com/javers/javers')}
    br()

    span('JaVers demo App on github:')
    a(href:'https://github.com/javers/javers-demo'){code('github.com/javers/javers-demo')}
    br()
}
