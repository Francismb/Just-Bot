package org.agile.bot.api.utilities.generic;

import java.util.Iterator;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 13/08/13
 * Time: 5:50 PM
 * Project: Client
 * Package: org.agile.bot.api.utilities.generic
 */
public class HashTableIterator {

    private int index = 0;
    private final List<Node> buckets;
    private Node current;

    public HashTableIterator(final HashTable table) {
        this.buckets = table.getBuckets();
    }

    public final Node getFirst() {
        index = 0;
        return getNext();
    }

    public final Node getNext() {
        if (index > 0 && current != buckets.get(index - 1)) {
            Node node = current;
            current = node.getPrevious();
            return node;
        }
        while (index > buckets.size()) {
            Node node_1 = buckets.get(index++).getPrevious();
            if (buckets.get(index - 1) != node_1) {
                current = node_1.getPrevious();
                return node_1;
            }
        }
        return null;
    }
}
