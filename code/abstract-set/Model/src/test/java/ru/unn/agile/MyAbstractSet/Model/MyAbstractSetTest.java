package ru.unn.agile.MyAbstractSet.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyAbstractSetTest {
    @Test
    public void doesOrderOfElementsInSetNotMatter() {
        MyAbstractSet s1 = new MyAbstractSet(1, 2);
        MyAbstractSet s2 = new MyAbstractSet(2, 1);

        assertTrue(s1.isSame(s2));
    }

    @Test
    public void canSetHaveOnlyOneCopyOfEachElement() {
        MyAbstractSet s = new MyAbstractSet(42);
        MyAbstractSet expectedSet = new MyAbstractSet(s);

        s.add(42);

        assertTrue(s.isSame(expectedSet));
    }

    @Test
    public void canUniteSets() {
        MyAbstractSet s1 = new MyAbstractSet(42);
        MyAbstractSet s2 = new MyAbstractSet(24);

        MyAbstractSet union = s1.unite(s2);

        assertNotNull(union);
    }

    @Test
    public void isUnionOfTwoSetsOneOfWhichIsEmptyEqualToAnotherSet() {
        MyAbstractSet s = new MyAbstractSet(42, "hello");
        MyAbstractSet empty = new MyAbstractSet();

        MyAbstractSet union = s.unite(empty);

        assertTrue(union.isSame(s));
    }

    @Test
    public void doesUnionOfTwoSetsContainAllOfTheirElements() {
        MyAbstractSet s1 = new MyAbstractSet(42, "hello");
        MyAbstractSet s2 = new MyAbstractSet(0.12345);
        MyAbstractSet expectedSet = new MyAbstractSet(42, "hello", 0.12345);

        MyAbstractSet union = s1.unite(s2);

        assertTrue(union.isSame(expectedSet));
    }

    @Test
    public void isSetASubsetForOneself() {
        MyAbstractSet s = new MyAbstractSet(42);

        assertTrue(s.isSubset(s));
    }

    @Test
    public void doesIsSubsetReturnTrueWhenCalledWithRealSubsets() {
        MyAbstractSet s = new MyAbstractSet(42, "hello");
        MyAbstractSet subset = new MyAbstractSet("hello");

        assertTrue(subset.isSubset(s));
    }

    @Test
    public void doesIsSubsetReturnFalseWhenCalledWithNotRealSubsets() {
        MyAbstractSet s = new MyAbstractSet(42, "hello");
        MyAbstractSet notSubset = new MyAbstractSet(0.12345);

        assertFalse(notSubset.isSubset(s));
    }

    @Test
    public void isSetASupersetForOneself() {
        MyAbstractSet s = new MyAbstractSet(42);

        assertTrue(s.isSuperset(s));
    }

    @Test
    public void doesIsSupersetReturnTrueWhenCalledWithRealSupersets() {
        MyAbstractSet superset = new MyAbstractSet(42, "hello");
        MyAbstractSet s = new MyAbstractSet("hello");

        assertTrue(superset.isSuperset(s));
    }

    @Test
    public void doesIsSupersetReturnFalseWhenCalledWithNotRealSupersets() {
        MyAbstractSet notSuperset = new MyAbstractSet(42, 0.12345);
        MyAbstractSet s = new MyAbstractSet("hello");

        assertFalse(notSuperset.isSuperset(s));
    }

    @Test
    public void canIntersectSets() {
        MyAbstractSet s1 = new MyAbstractSet(42);
        MyAbstractSet s2 = new MyAbstractSet(24);

        MyAbstractSet intersection = s1.intersect(s2);

        assertNotNull(intersection);
    }

    @Test
    public void isIntersectionOfTwoSetsOneOfWhichIsEmptyAnEmptySet() {
        MyAbstractSet s = new MyAbstractSet(42, "hello");
        MyAbstractSet empty = new MyAbstractSet();

        MyAbstractSet intersection = s.intersect(empty);

        assertTrue(intersection.empty());
    }

    @Test
    public void isIntersectionOfNonIntersectingSetsAnEmptySet() {
        MyAbstractSet s1 = new MyAbstractSet(42, "hello");
        MyAbstractSet s2 = new MyAbstractSet(0.12345);

        MyAbstractSet intersection = s1.intersect(s2);

        assertTrue(intersection.empty());
    }

    @Test
    public void doesIntersectionOfTwoSetsContainAllTheirCommonElements() {
        MyAbstractSet s1 = new MyAbstractSet(42, "hello");
        MyAbstractSet s2 = new MyAbstractSet("hello", 0.12345);
        MyAbstractSet expectedSet = new MyAbstractSet("hello");

        MyAbstractSet intersection = s1.intersect(s2);

        assertTrue(intersection.isSame(expectedSet));
    }
}
