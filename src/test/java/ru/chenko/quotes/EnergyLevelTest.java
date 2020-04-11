package ru.chenko.quotes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chenko.quotes.model.EnergyLevel;
import ru.chenko.quotes.model.Quote;
import ru.chenko.quotes.service.EnergyLevelService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnergyLevelTest {

    @Autowired
    private EnergyLevelService energyLevelService;

    private Integer listSize = 9;
    List<String> initIsinList = new ArrayList<>();

    @Before
    public void init() {
        for(int i = 0; i < listSize; i++) {
            Quote quote = new Quote("RU000A0JX0J" + i, 100.3 + i, 101.9 + i);
            initIsinList.add("RU000A0JX0J" + i);
            energyLevelService.processQuote(quote);
        }
    }


    @Test
    public void testFindNotExistEnergyLevel() {
        EnergyLevel energyLevel = energyLevelService.findEnergyLevelByIsin("RU000A0JX1J0");
        Assert.assertNull(energyLevel);
    }

    @Test
    public void testFindExistEnergyLevel() {
        EnergyLevel energyLevel = energyLevelService.findEnergyLevelByIsin("RU000A0JX0J3");
        Assert.assertEquals((Double) 103.3, energyLevel.getValue());
    }

    @Test
    public void testGetEnergyLevelList() {
        List<EnergyLevel> energyLevelList = energyLevelService.getEnergyLevelList();
        Assert.assertTrue(energyLevelList.size() == listSize &&
                                        energyLevelList.stream().allMatch(elvl -> initIsinList.contains(elvl.getIsin())));
    }

}
