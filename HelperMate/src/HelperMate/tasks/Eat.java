package HelperMate.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

public class Eat extends Task<ClientContext> {

	private int setAmount = (int) ((ctx.skills.CONSTITUTION * 10) * .25);
	private int eatItemId = 520;

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
