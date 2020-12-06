package edu.luc.etl.cs313.android.simplestopwatch.test.model.time;


import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractTimeModelTest {

    private TimeModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimeModel model) {
        this.model = model;
    }

    /**
     * Verifies that runtime and laptime are initially 0 or less.
     */
    @Test
    public void testPreconditions() {
        assertEquals(0, model.getRuntime());

    }

    /**
     * Verifies that runtime is incremented correctly.
     */

    @Test
    public void testDecrementRuntimeOne() {
        final int rt = model.getRuntime();
        model.decRuntime();
        assertEquals((rt - SEC_PER_TICK), model.getRuntime());
    }

    /**
     * Verifies that runtime turns over correctly.  @author Julie
     */
    @Test
    public void testDecrementRuntimeMany(){
        final int rt = model.getRuntime();
        for (int i = 0; i < model.getRuntime(); i++){
            model.decRuntime();
        }
        assertEquals(rt, model.getRuntime());
    }
    /**
     * Verifies that runtime turns over correctly for a period of time     @Julie
     */}