package HelperMate.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

public class Eat extends Task<ClientContext> {

	private int setAmount = (int) ((ctx.players.local().healthPercent()) * .25);
	private int[] eatItemId = {
			//MonkFish
			7946, 7947, 14825,
			//Shark
			385, 386,
			//Swordfish
			373, 374, 14831
			};

	public Eat(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.players.local().healthPercent() < setAmount;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute() {
		Item i = ctx.backpack.select().id(eatItemId).first().poll();

		if (ctx.backpack.contains(i))
			while (ctx.combatBar.COMPONENT_HEALTH <= ((int) (ctx.skills.CONSTITUTION * 10) * .25)) {
				i.interact("Eat");
			}
	}

}
