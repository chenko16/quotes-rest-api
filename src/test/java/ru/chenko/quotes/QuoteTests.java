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

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteTests {

	@Autowired
	private EnergyLevelService energyLevelService;

	@Before
	public void init() {
	}

	@Test
	public void testProcessQuote_notExist() {
		Quote quote = new Quote("RU000A0JX0J0", 100.3, 101.9);
		energyLevelService.processQuote(quote);

		EnergyLevel energyLevel = energyLevelService.findEnergyLevelByIsin(quote.getIsin());

		Assert.assertEquals(quote.getBid(), energyLevel.getValue());
	}


	@Test
	public void testProcessQuote_alreadyExist_BidGraterElvl() {
		Quote quote = new Quote("RU000A0JX0J1", 100.2, 101.9);
		energyLevelService.processQuote(quote);

		quote.setBid(100.5);
		energyLevelService.processQuote(quote);

		EnergyLevel energyLevel = energyLevelService.findEnergyLevelByIsin(quote.getIsin());

		Assert.assertEquals(quote.getBid(), energyLevel.getValue());
	}


	@Test
	public void testProcessQuote_alreadyExist_AskLessElvl() {
		Quote quote = new Quote("RU000A0JX0J2", 100.8, 101.9);
		energyLevelService.processQuote(quote);

		quote.setBid(100.0);
		quote.setAsk(100.4);
		energyLevelService.processQuote(quote);

		EnergyLevel energyLevel = energyLevelService.findEnergyLevelByIsin(quote.getIsin());

		Assert.assertEquals(quote.getAsk(), energyLevel.getValue());
	}

	@Test
	public void testProcessQuote_notExist_NullBidNot() {
		Quote quote = new Quote("RU000A0JX0J3", null, 101.9);
		energyLevelService.processQuote(quote);

		EnergyLevel energyLevel = energyLevelService.findEnergyLevelByIsin(quote.getIsin());

		Assert.assertEquals(quote.getAsk(), energyLevel.getValue());
	}

	@Test
	public void testProcessQuote_alredeExist_NullBidNot() {
		Quote quote = new Quote("RU000A0JX0J4", 100.8, 101.9);
		energyLevelService.processQuote(quote);

		quote.setBid(null);
		energyLevelService.processQuote(quote);

		EnergyLevel energyLevel = energyLevelService.findEnergyLevelByIsin(quote.getIsin());

		Assert.assertEquals(quote.getAsk(), energyLevel.getValue());
	}


	@Test(expected = RuntimeException.class)
	public void testProcessQuote_wrondIsinLength() {
		Quote quote = new Quote("RU000A0JX0", 100.8, 101.9);
		energyLevelService.processQuote(quote);
	}


	@Test(expected = RuntimeException.class)
	public void testProcessQuote_BidAskCompare() {
		Quote quote = new Quote("RU000A0JX0",101.9, 100.8);
		energyLevelService.processQuote(quote);
	}
}
