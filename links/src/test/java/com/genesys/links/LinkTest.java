package com.genesys.links;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LinkTest extends TestCase
{

    public void testItCanLinkToItself()
    {
        Link foo = new Link();
        foo.linkTo(foo);
        assertTrue(foo.isLinkedTo(foo));
    }

    public void testItDoesNotLinkToItself()
    {
        Link foo = new Link();
        assertFalse(foo.isLinkedTo(foo));
    }

    public void testUnidirectionalLinkToNeighbour()
    {
        Link foo = new Link();
        Link bar = new Link();
        bar.linkTo(foo);
        assertTrue(bar.isLinkedTo(foo));
        assertFalse(foo.isLinkedTo(bar));
    }

    public void testNeighboursWithConnectionsToThemselves()
    {
        Link foo = new Link();
        Link bar = new Link();
        Link baz = new Link();

        // Connect the Objs to themselves.
        foo.linkTo(foo);
        bar.linkTo(bar);
        baz.linkTo(baz);

        // Connect baz => bar => foo.
        baz.linkTo(bar);
        bar.linkTo(foo);

        assertTrue(baz.isLinkedTo(foo));
        assertTrue(baz.isLinkedTo(bar));
        assertTrue(bar.isLinkedTo(foo));
        assertFalse(bar.isLinkedTo(baz));
    }

    public void testCyclicGraph()
    {
        Link foo = new Link();
        Link bar = new Link();
        Link baz = new Link();

        // Connect the nodes baz => bar => foo => baz.
        baz.linkTo(bar);
        bar.linkTo(foo);
        foo.linkTo(baz);

        assertTrue(baz.isLinkedTo(foo));
        assertTrue(baz.isLinkedTo(bar));
        assertTrue(baz.isLinkedTo(baz));
    }

    public void testItCanHaveNeighboursInCyclicGraph()
    {
        Link foo = new Link();
        Link bar = new Link();
        Link baz = new Link();

        // Connect the nodes baz => bar <=> foo.
        baz.linkTo(bar);
        bar.linkTo(foo);
        foo.linkTo(bar);

       // assertTrue(baz.isLinkedTo(foo));
       // assertTrue(baz.isLinkedTo(bar));
        assertFalse(baz.isLinkedTo(baz));
    }

    public void testCanHaveACycleOfMoreThanTwo()
    {
        Link foo = new Link();
        Link bar = new Link();
        Link baz = new Link();
        Link qux = new Link();

        // Connect the nodes baz => bar => foo => qux => bar.
        baz.linkTo(bar);
        bar.linkTo(foo);
        foo.linkTo(qux);
        qux.linkTo(bar);

        
        assertFalse(qux.isLinkedTo(baz));
        assertTrue(baz.isLinkedTo(foo));
        assertTrue(baz.isLinkedTo(bar));
        assertTrue(baz.isLinkedTo(qux));
        assertFalse(baz.isLinkedTo(baz));
    }

    public void testCanHaveACycleOfMoreThanFive()
    {
        Link foo = new Link();
        Link bar = new Link();
        Link baz = new Link();
        Link qux = new Link();
        Link quux = new Link();
        Link quuz = new Link();
        Link corge = new Link();

        // Connect the nodes baz => bar => foo => qux => bar.
        baz.linkTo(bar);
        bar.linkTo(foo);
        foo.linkTo(qux);
        qux.linkTo(bar);
        bar.linkTo(quux);
        quux.linkTo(quuz);
        quuz.linkTo(corge);

        assertFalse(qux.isLinkedTo(baz));
        assertTrue(baz.isLinkedTo(foo));
        assertTrue(baz.isLinkedTo(bar));
        assertTrue(baz.isLinkedTo(qux));
        assertFalse(baz.isLinkedTo(baz));
        assertTrue(baz.isLinkedTo(quux));
        assertTrue(baz.isLinkedTo(quuz));
        assertTrue(baz.isLinkedTo(corge));

    }
}
