package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class ResourcesTest {
    @Test
    public void addResources() throws Exception {
        Resources resources = new Resources();
        Resources resources1 = new Resources();
        resources.setWoods(3);
        resources.setCoins(4);
        resources.setServants(5);
        resources1.setWoods(5);
        resources1.setCoins(7);
        resources1.setServants(8);
        resources1.addResources(resources);
        assertEquals(resources1.getWoods(),8);
        assertEquals(resources1.getCoins(),11);
        assertEquals(resources1.getServants(),13);
    }


    @Test
    public void resIsGreater() throws Exception {
        Resources resources = new Resources();
        Resources resources1 = new Resources();
        resources.setWoods(3);
        resources.setCoins(4);
        resources.setServants(5);
        resources1.setWoods(5);
        resources1.setCoins(7);
        resources1.setServants(8);
        assertTrue(resources1.resIsGreater(resources));
    }

    @Test
    public void getCouncilPrivilegeChoices() throws Exception {
    }

}