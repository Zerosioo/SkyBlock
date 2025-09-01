package me.zerosio.garden.visitors.build;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.zerosio.garden.visitors.npcs.exclusives.*;
import me.zerosio.garden.visitors.npcs.uncommon.*;

public class VisitorLocalization {
	
	public static void registerVisitorLocations(Player player) {
		World world = Bukkit.getWorld("garden_" + player.getUniqueId());
		
		if (world == null) return;
		
		VisitorManager.setVisitorLocations(
			new Location(world, 53, 78, 30),
			new Location(world, 53, 78, 33),
			new Location(world, 54, 77, 37),
			new Location(world, 57, 77, 37),
			new Location(world, 60, 77, 37),
			new Location(world, 63, 77, 37)
		);
	}
	
	public static void registerVisitors() {
		regVis(new AdventurerG());
		regVis(new AlchemistG());
		regVis(new AndrewG());
		regVis(new AnitaG());
		regVis(new ArthurG());
		regVis(new BakerG());
		regVis(new BankerBroadjawG());
		regVis(new BartenderG());
		regVis(new BethG());
		regVis(new ClerkSeraphineG());
		regVis(new DalbrekG());
		regVis(new DukeG());
		regVis(new DuskG());
		regVis(new EmissaryCarltonG());
		regVis(new EmissaryCeannaG());
		regVis(new EmissaryFraiserG());
		regVis(new EmissarySiskoG());
		regVis(new EmissaryWilsonG());
		regVis(new FarmerJonG());
		regVis(new FarmhandG());
		regVis(new FearMongererG());
		regVis(new FelixG());
		regVis(new FishermanGeraldG());
		regVis(new FragilisG());
		regVis(new FriendlyHikerG());
		regVis(new GeonathanGreatforgeG());
		regVis(new GimleyG());
		regVis(new GoldForgerG());
		regVis(new GrandmaWolfG());
		regVis(new GuyG());
		regVis(new GwendolynG());
		regVis(new HornumG());
		regVis(new HungryHikerG());
		regVis(new IronForgerG());
		regVis(new JackG());
		regVis(new JacobG());
		regVis(new JamieG());
		regVis(new JerryG());
		regVis(new JotraelineGreatforgeG());
		regVis(new LazyMinerG());
		regVis(new LeoG());
		regVis(new LiamG());
		regVis(new LibrarianG());
		regVis(new LumberJackG());
		regVis(new LuminaG());
		regVis(new LynnG());
		regVis(new MadameEleanorQGoldsworthIIIG());
		regVis(new MasonG());
		regVis(new MaeveG());
		regVis(new OdawaG());
		regVis(new OldManGarryG());
		regVis(new OringoG());
		//regVis(new PestWranglerG());
		//regVis(new PestWranglerG());
		regVis(new PeteG());
		regVis(new PlumberJoeG());
		regVis(new PuzzlerG());
		regVis(new QueenMismylaG());
		regVis(new RavenousRhinoG());
		regVis(new RhysG());
		regVis(new RoyalResidentG());
		regVis(new RoyalResidentNeighborG());
		regVis(new RoyalResidentSnootyG());
		regVis(new RustyG());
		regVis(new RyuG());
		regVis(new SargwynG());
		regVis(new SeymourG());
		regVis(new ShaggyG());
		regVis(new ShiftyG());
		regVis(new SiriusG());
		regVis(new SpacemanG());
		regVis(new StellaG());
		regVis(new TammyG());
		regVis(new TarwenG());
		regVis(new TerryG());
		regVis(new TiatheFairyG());
		regVis(new TomG());
		regVis(new TrevorG());
		regVis(new VexG());
		regVis(new VinylCollectorG());
		regVis(new WeaponsmithG());
		regVis(new WizardG());
		regVis(new XalxG());
		regVis(new ZogG());

	}
	
	private static void regVis(GardenVisitor g) {
		VisitorManager.registerVisitor(g);
	}
}