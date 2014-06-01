package org.javers.democlient.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

/**
 * @author bartosz walacik
 */
public class Hierarchy {
    private Employee root;
    @Id
    private String hierarchyName;

    public Hierarchy(Employee root, String hierarchyName) {
        Preconditions.checkArgument(root != null);

        this.root = root;
        this.hierarchyName = hierarchyName;
    }

    public Employee getRoot() {
        return root;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String print(){
        StringBuilder b = new StringBuilder();
        printTree(root, 1, b);
        return b.toString();
    }

    private void printTree(Employee node, int level, final StringBuilder acc){
        acc.append( StringUtils.rightPad(".", level*2) );
        acc.append( node.toString() );
        acc.append( "\n" );
        node.forEachSubordinate(s -> printTree(s, level + 1, acc));
    }
}
