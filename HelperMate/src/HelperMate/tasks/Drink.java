package HelperMate.tasks;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.Skills;

import HelperMate.Main;

public class Drink extends Task<ClientContext> {

	private boolean pray = false;
	private boolean overload = false;

	private int[] overloadId = { 15332,// Overload (4)
			15333,// Overload (3)
			15334,// Overload (2)
			15335,// Overload (1)
			23531,// Overload flask (6)
			23532,// Overload flask (5)
			23533,// Overload flask (4)
			23534,// Overload flask (3)
			23535,// Overload flask (2)
			23536,// Overload flask (1)
			26636,// Overload (4)
			26637,// Overload (3)
			26638,// Overload (2)
			26639,// Overload (1)
			26750,// Overload flask (6)
			26751,// Overload flask (5)
			26752,// Overload flask (4)
			26753,// Overload flask (3)
			26754,// Overload flask (2)
			26755,// Overload flask (1)
			33188,// Overload salve (1)
			33189,// Overload salve (1)
			33190,// Overload salve (2)
			33191,// Overload salve (2)
			33192,// Overload salve (3)
			33193,// Overload salve (3)
			33194,// Overload salve (4)
			33195,// Overload salve (4)
			33196,// Overload salve (5)
			33197,// Overload salve (5)
			33198,// Overload salve (6)
			33199,// Overload salve (6)
			33200,// Supreme overload potion (1)
			33201,// Supreme overload potion (1)
			33202,// Supreme overload potion (2)
			33203,// Supreme overload potion (2)
			33204,// Supreme overload potion (3)
			33205,// Supreme overload potion (3)
			33206,// Supreme overload potion (4)
			33207,// Supreme overload potion (4)
			33208,// Supreme overload potion (5)
			33209,// Supreme overload potion (5)
			33210,// Supreme overload potion (6)
			33211,// Supreme overload potion (6)
			33212,// Supreme overload salve (1)
			33213,// Supreme overload salve (1)
			33214,// Supreme overload salve (2)
			33215,// Supreme overload salve (2)
			33216,// Supreme overload salve (3)
			33217,// Supreme overload salve (3)
			33218,// Supreme overload salve (4)
			33219,// Supreme overload salve (4)
			33220,// Supreme overload salve (5)
			33221,// Supreme overload salve (5)
			33222,// Supreme overload salve (6)
			33223,// Supreme overload salve (6)
			33236,// Holy overload potion (1)
			33237,// Holy overload potion (1)
			33238,// Holy overload potion (2)
			33239,// Holy overload potion (2)
			33240,// Holy overload potion (3)
			33241,// Holy overload potion (3)
			33242,// Holy overload potion (4)
			33243,// Holy overload potion (4)
			33244,// Holy overload potion (5)
			33245,// Holy overload potion (5)
			33246,// Holy overload potion (6)
			33247,// Holy overload potion (6)
			33248,// Searing overload potion (1)
			33249,// Searing overload potion (1)
			33250,// Searing overload potion (2)
			33251,// Searing overload potion (2)
			33252,// Searing overload potion (3)
			33253,// Searing overload potion (3)
			33254,// Searing overload potion (4)
			33255,// Searing overload potion (4)
			33256,// Searing overload potion (5)
			33257,// Searing overload potion (5)
			33258,// Searing overload potion (6)
			33259 // Searing overload potion (6)
	};

	private int[] prayerPotId = { 139, // Prayer potion(3)
			140, // Prayer potion(3)
			141, // Prayer potion(2)
			142, // Prayer potion(2)
			143, // Prayer potion(1)
			144, // Prayer potion(1)
			2434, // Prayer potion(4)
			2435, // Prayer potion(4)
			11465, // Prayer mix(2)
			11466, // Prayer mix(2)
			11467, // Prayer mix(1)
			11468, // Prayer mix(1)
			14207, // Prayer potion(5)
			14208, // Prayer potion(5)
			14209, // Prayer potion(4)
			14210, // Prayer potion(4)
			14211, // Prayer potion(3)
			14212, // Prayer potion(3)
			14213, // Prayer potion(2)
			14214, // Prayer potion(2)
			14215, // Prayer potion(1)
			14216, // Prayer potion(1)
			15328, // Super prayer (4)
			15329, // Super prayer (3)
			15330, // Super prayer (2)
			15331, // Super prayer (1)
			23243, // Prayer flask (6)
			23244, // Prayer flask (6)
			23245, // Prayer flask (5)
			23246, // Prayer flask (5)
			23247, // Prayer flask (4)
			23248, // Prayer flask (4)
			23249, // Prayer flask (3)
			23250, // Prayer flask (3)
			23251, // Prayer flask (2)
			23252, // Prayer flask (2)
			23253, // Prayer flask (1)
			23254, // Prayer flask (1)
			23525, // Super prayer flask (6)
			23526, // Super prayer flask (5)
			23527, // Super prayer flask (4)
			23528, // Super prayer flask (3)
			23529, // Super prayer flask (2)
			23530, // Super prayer flask (1)
			26632, // Super prayer (4)
			26633, // Super prayer (3)
			26634, // Super prayer (2)
			26635, // Super prayer (1)
			26744, // Super prayer flask (6)
			26745, // Super prayer flask (5)
			26746, // Super prayer flask (4)
			26747, // Super prayer flask (3)
			26748, // Super prayer flask (2)
			26749 // Super prayer flask (1)
	};

	public Drink(ClientContext ctx) {
		super(ctx);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean activate() {
		return false;
		//return checkPot();
	}

	@Override
	public void execute() {
		Item i = null;
		if (overload) {
			i = ctx.backpack.select().id(overloadId).first().poll();
		}

		if (pray) {
			i = ctx.backpack.select().id(prayerPotId).first().poll();

		}

		i.interact("Drink");

		Main.setPrayerPotSwigs(ctx.backpack.id(prayerPotId).count());
		Main.setOverloadSwigs(ctx.backpack.id(overloadId).count());
	}

	private boolean checkPot() {
		if (ctx.skills.level(Skills.ATTACK) <= ctx.skills
				.realLevel(Skills.ATTACK)
				&& ctx.skills.level(Skills.STRENGTH) <= ctx.skills
						.realLevel(Skills.STRENGTH)
				&& ctx.skills.level(Skills.DEFENSE) <= ctx.skills
						.realLevel(Skills.DEFENSE)
				&& ctx.skills.level(Skills.RANGE) <= ctx.skills
						.realLevel(Skills.RANGE)
				&& ctx.skills.level(Skills.MAGIC) <= ctx.skills
						.realLevel(Skills.MAGIC))
			overload = true;

		if (ctx.powers.prayerPoints() <= (int) (ctx.powers.prayerPoints() * 0.25))
			pray = true;

		return overload || pray;
	}

}
