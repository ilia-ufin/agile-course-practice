package ru.unn.agile.MyAbstractSet.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MyAVLTreeTest {
    @Test
    public void canInstantiateTreeWithDefaultConstructor() {
        MyAVLTree t = new MyAVLTree();

        assertNotNull(t);
    }

    @Test
    public void isSizeOfTreeWithoutNodesEqualsZero() {
        MyAVLTree t = new MyAVLTree();

        assertEquals(0, t.size());
    }

    @Test
    public void isTreeWithoutNodesEmpty() {
        MyAVLTree t = new MyAVLTree();

        assertTrue(t.empty());
    }

    @Test
    public void isHeightOfEmptyTreeEqualsZero() {
        MyAVLTree t = new MyAVLTree();

        assertEquals(0, t.height());
    }

    @Test
    public void canAddOneNodeToTree() {
        MyAVLTree t = new MyAVLTree();

        t.add(42);

        assertNotNull(t);
    }

    @Test
    public void doesContainsReturnFalseForNodeThatHasNotBeenAddedToTree() {
        MyAVLTree t = new MyAVLTree();

        assertFalse(t.contains(42));
    }

    @Test
    public void doesContainsReturnTrueForNodeThatHasBeenAddedToTree() {
        MyAVLTree t = new MyAVLTree();

        t.add(42);

        assertTrue(t.contains(42));
    }

    @Test
    public void isSizeOfTreeWithOneNodeEqualsOne() {
        MyAVLTree t = new MyAVLTree();

        t.add(42);

        assertEquals(1, t.size());
    }

    @Test
    public void isTreeWithOneNodeNotEmpty() {
        MyAVLTree t = new MyAVLTree();

        t.add(42);

        assertFalse(t.empty());
    }

    @Test
    public void isHeightOfTreeWithOneNodeEqualsOne() {
        MyAVLTree t = new MyAVLTree();

        t.add(42);

        assertEquals(1, t.height());
    }

    @Test
    public void canAddNonPrimitiveTypeObjectToTree() {
        String str = "hello";
        MyAVLTree t = new MyAVLTree();

        t.add(str);

        assertTrue(t.contains(str));
    }

    @Test
    public void doesTreeContainValuesNotReferences() {
        String str = "hello";
        MyAVLTree t = new MyAVLTree();

        t.add(str);

        assertTrue(t.contains("hello"));
    }

    @Test
    public void canAddTwoNodesToTree() {
        MyAVLTree t = new MyAVLTree();

        t.add(42);
        t.add(24);

        assertNotNull(t);
    }

    @Test
    public void isSizeOfTreeWithTwoNodesEqualsTwo() {
        MyAVLTree t = new MyAVLTree();

        t.add(42);
        t.add(24);

        assertEquals(2, t.size());
    }

    @Test
    public void isHeightOfTreeWithTwoNodesEqualsTwo() {
        MyAVLTree t = new MyAVLTree();

        t.add(42);
        t.add(24);

        assertEquals(2, t.height());
    }

    @Test
    public void canCallDepthFirstApply() {
        MyAVLTree t = new MyAVLTree();
        Consumer<Object> fn = (Object o) -> {
            //do nothing
        };

        t.depthFirstApply(fn);

        assertNotNull(t);
    }

    @Test
    public void doesDepthFirstApplyApplyFunction() {
        StringBuilder sb = new StringBuilder();
        Consumer<Object> fn = n -> sb.append(n.toString());
        MyAVLTree t = new MyAVLTree();
        t.add(2);
        t.add(1);
        t.add(3);

        t.depthFirstApply(fn);

        assertEquals("123", sb.toString());
    }

    @Test
    public void canCallGetObjectList() {
        MyAVLTree t = new MyAVLTree();

        List<Object> l = t.getObjectList();

        assertNotNull(l);
    }

    @Test
    public void doesGetObjectListReturnEmptyListForEmptyTree() {
        MyAVLTree t = new MyAVLTree();

        List<Object> l = t.getObjectList();

        assertEquals(0, l.size());
    }

    @Test
    public void doesGetObjectListReturnThoseAndOnlyThoseNodesThatArePresentInTree() {
        List<Object> expectedList = new ArrayList<>();
        expectedList.add(new Integer(1));
        expectedList.add(new Integer(2));
        expectedList.add(new Integer(3));
        MyAVLTree t = new MyAVLTree();
        t.add(2);
        t.add(1);
        t.add(3);

        List<Object> objectList = t.getObjectList();

        assertEquals(expectedList, objectList);
    }

    @Test
    public void doesIsSameReturnTrueForTwoEmptyTrees() {
        MyAVLTree t1 = new MyAVLTree();

        MyAVLTree t2 = new MyAVLTree();

        assertTrue(t1.isSame(t2));
    }

    @Test
    public void doesIsSameReturnFalseForDifferentTrees() {
        MyAVLTree t1 = new MyAVLTree();
        t1.add(42);
        MyAVLTree t2 = new MyAVLTree();
        t2.add(24);

        assertFalse(t1.isSame(t2));
    }

    @Test
    public void doesIsSameReturnTrueForIdenticalTrees() {
        MyAVLTree t1 = new MyAVLTree();
        t1.add(42);
        MyAVLTree t2 = new MyAVLTree();
        t2.add(42);

        assertTrue(t1.isSame(t2));
    }

    @Test
    public void doesTreeInstantiatedWithVariadicArgumentsConstructorContainAllOfTheseObjects() {
        MyAVLTree expectedTree = new MyAVLTree();
        expectedTree.add(42);
        expectedTree.add("hello");

        MyAVLTree t = new MyAVLTree(42, "hello");

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void doesAddingAlreadyExistingNodeNotAlterTheTree() {
        MyAVLTree expectedTree = new MyAVLTree(42);
        MyAVLTree t = new MyAVLTree(42);

        t.add(42);

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void doesInstantiatingTreeWithCopyConstructorReturnIdenticalTree() {
        MyAVLTree original = new MyAVLTree(42);
        MyAVLTree copy = new MyAVLTree(original);

        assertTrue(copy.isSame(original));
    }

    @Test
    public void doesCopyConstructorReturnDeepNotShallowCopy() {
        MyAVLTree original = new MyAVLTree(42);
        MyAVLTree copy = new MyAVLTree(original);

        original.add("hello");

        assertFalse(copy.isSame(original));
    }

    @Test
    public void canCallRemoveOnEmptyTree() {
        MyAVLTree t = new MyAVLTree();

        t.remove(42);

        assertNotNull(t);
    }

    @Test
    public void canRemoveLeafNode() {
        MyAVLTree expectedTree = new MyAVLTree(2);
        MyAVLTree t = new MyAVLTree(2, 1);

        t.remove(1);

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void canRemoveNodeThatIsNeitherLeafNorRoot() {
        MyAVLTree expectedTree = new MyAVLTree(3, 4, 1);
        MyAVLTree t = new MyAVLTree(3, 4, 2, 1);

        t.remove(2);

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void canRemoveRootNode() {
        MyAVLTree expectedTree = new MyAVLTree(3, 1);
        MyAVLTree t = new MyAVLTree(2, 3, 1);

        t.remove(2);

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void doesRemovingAlreadyAbsentNodeNotAlterTheTree() {
        MyAVLTree t = new MyAVLTree(42);
        MyAVLTree tCopy = new MyAVLTree(t);

        t.remove(24);

        assertTrue(t.isSame(tCopy));
    }

    @Test
    public void doesTreeSizeDecreaseAfterRemovingNodeThatIsPresentInTree() {
        MyAVLTree t = new MyAVLTree(42);

        t.remove(42);

        assertEquals(0, t.size());
    }

    @Test
    public void doestreePerformLeftLeftRotation() {
        MyAVLTree expectedTree = new MyAVLTree(2, 1, 3);
        MyAVLTree t = new MyAVLTree();
        t.add(3);
        t.add(2);

        t.add(1); // This should trigger rotation

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void doestreePerformRightRightRotation() {
        MyAVLTree expectedTree = new MyAVLTree(2, 1, 3);
        MyAVLTree t = new MyAVLTree();
        t.add(1);
        t.add(2);

        t.add(3); // This should trigger rotation

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void doestreePerformLeftRightRotation() {
        MyAVLTree expectedTree = new MyAVLTree(2, 1, 3);
        MyAVLTree t = new MyAVLTree();
        t.add(3);
        t.add(1);

        t.add(2); // This should trigger rotation

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void doesTreePerformRightLeftRotation() {
        MyAVLTree expectedTree = new MyAVLTree(2, 1, 3);
        MyAVLTree t = new MyAVLTree();
        t.add(1);
        t.add(3);

        t.add(2); // This should trigger rotation

        assertTrue(t.isSame(expectedTree));
    }

    @Test
    public void canCallToStringOnEmptyTree() {
        MyAVLTree t = new MyAVLTree();
        String expectedString = "{}";

        String s = t.toString();

        assertEquals(expectedString, s);
    }

    @Test
    public void canCallToStringOnNonEmptyTree() {
        MyAVLTree t = new MyAVLTree(1, 2, 3);
        String expectedString = "{1, 2, 3}";

        String s = t.toString();

        assertEquals(expectedString, s);
    }
}
