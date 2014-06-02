layout 'layouts/main.tpl',
pageTitle: 'show Hierarchy',
mainBody: contents {
    h3("Object diff feature")

    a(href:'/hierarchy/Hier_2013','hierarchy 2013')
    br()
    a(href:'/hierarchy/Hier_2014','hierarchy 2014')

    br()
    br()
    br()

    span('JaVers can compare those graphs with two lines of code:')
    br()
    pre(){
        code('''Javers javers = JaversBuilder.javers().build()
Diff diff = javers.compare(oldHier.getRoot(), currentHier.getRoot())''')
    }

    a(href:'/hierarchy-diff/Hier_2013/Hier_2014','diff "hierarchy 2013" -> "hierarchy 2014"')
    br()
    a(href:'/hierarchy-diff/Hier_2014/Hier_2013','diff "hierarchy 2014" -> "hierarchy 2013"')
}
