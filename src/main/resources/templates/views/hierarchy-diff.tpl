layout 'layouts/main.tpl',
pageTitle: 'show Hierarchy',
mainBody: contents {
    h3("diff from $leftHierName to $rightHierName")

    pre(){
        code(diffJson)}
}
