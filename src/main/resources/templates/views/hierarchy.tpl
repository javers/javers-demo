layout 'layouts/main.tpl',
pageTitle: 'show Hierarchy',
mainBody: contents {
    h3("hierarchy $hier.hierarchyName")

    pre(){
        code(hier.print())}
}
