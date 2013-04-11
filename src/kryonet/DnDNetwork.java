package kryonet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import objects.WorldObjectStack;
import objects.armor.Armor.ArmorType;
import objects.dndcharacter.Abilities;
import objects.dndcharacter.Abilities.AbilityType;
import objects.dndcharacter.Alignment;
import objects.dndcharacter.Alignment.AlignmentStance;
import objects.dndcharacter.BackPack;
import objects.dndcharacter.Defense;
import objects.dndcharacter.DnDCharacter;
import objects.dndcharacter.DnDCharacter.Gender;
import objects.dndcharacter.Experience;
import objects.dndcharacter.Language;
import objects.dndcharacter.Language.LanguageType;
import objects.dndcharacter.Language.ScriptType;
import objects.dndcharacter.classes.Cleric;
import objects.dndcharacter.classes.DnDClass;
import objects.dndcharacter.classes.DnDClass.ClassSkills;
import objects.dndcharacter.classes.DnDClass.PowerSource;
import objects.dndcharacter.classes.DnDClass.Role;
import objects.dndcharacter.classes.Fighter;
import objects.dndcharacter.races.Dragonborn;
import objects.dndcharacter.races.Human;
import objects.dndcharacter.races.Race;
import objects.util.Range;
import objects.weapons.Weapon.WeaponType;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class DnDNetwork {
	static public final int port = 54555;
	
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Login.class);
		kryo.register(GetCharacters.class);	
		kryo.register(ArrayList.class);
		kryo.register(DnDCharacter.class);
		kryo.register(Gender.class);
		kryo.register(DnDClass.class);
		kryo.register(ClassSkills.class);
		kryo.register(Role.class);
		kryo.register(PowerSource.class);
		kryo.register(Race.class);
		kryo.register(Defense.class);
		kryo.register(Experience.class);
		kryo.register(Alignment.class);
		kryo.register(AlignmentStance.class);
		kryo.register(Abilities.class);
		kryo.register(AbilityType.class);
		kryo.register(Language.class);
		kryo.register(BackPack.class);
		kryo.register(Range.class);
		kryo.register(WorldObjectStack.class);
		kryo.register(Fighter.class);
		kryo.register(Cleric.class);
		kryo.register(ArmorType.class);
		kryo.register(HashMap.class);
		kryo.register(WeaponType.class);
		kryo.register(LanguageType.class);
		kryo.register(ScriptType.class);
		kryo.register(Human.class);
		kryo.register(Dragonborn.class);
		kryo.register(Register.class);
		kryo.register(Message.class);
		kryo.register(UpdateNames.class);
		kryo.register(String[].class);
		kryo.register(LoginCharacter.class);
		kryo.register(DisconnectCharacter.class);
		kryo.register(CreateAccount.class);
		kryo.register(CreateCharacter.class);
	}
	
	public static class Register {
		
	}
	
	public static class Login {
		public String ip;
		public String username;
		public String password;
		public boolean accepted;
	}
	
	public static class CreateAccount {
		public String username;
		public String password;
		public String email;
		public boolean accepted;
		public String error;
	}
	
	public static class CreateCharacter {
		public String username;
		public String name;
		public String dnd_class;
		public String dnd_race;
		public boolean accepted;
		public String error;
	}
	
	public static class GetCharacters {
		public String name;
		public List<DnDCharacter> results;
	}
	
	public static class Message {
		public String text;
	}
	
	public static class LoginCharacter {
		public String name;
	}
	
	public static class DisconnectCharacter {
		
	}

	static public class UpdateNames {
		public String[] names;
	}
}
