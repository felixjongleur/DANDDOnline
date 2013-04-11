package objects.dndcharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import enums.Enums.RaceType;

public class Language {

	public enum LanguageType { COMMON, DEEP_SPEECH, DRACONIC, DWARVEN, ELVEN, GLANT, GOBLIN, PRIMORDIAL, SUPERNAL, ABYSSAL };
	
	public enum ScriptType { COMMON, RELLANIC, IOKHARIC, DAVEK, BARAZHAD, SUPERNAL };
	
	private static Map<LanguageType, ScriptType> languageToScript;
	
	private static Map<LanguageType, RaceType> languageToRace;
		
	static {
		languageToScript = new HashMap<LanguageType, ScriptType>();
		languageToScript.put(LanguageType.COMMON, ScriptType.COMMON);
		languageToScript.put(LanguageType.DEEP_SPEECH, ScriptType.RELLANIC);
		languageToScript.put(LanguageType.DRACONIC, ScriptType.IOKHARIC);
		languageToScript.put(LanguageType.DWARVEN, ScriptType.DAVEK);
		languageToScript.put(LanguageType.ELVEN, ScriptType.RELLANIC);
		languageToScript.put(LanguageType.GLANT, ScriptType.DAVEK);
		languageToScript.put(LanguageType.GOBLIN, ScriptType.COMMON);
		languageToScript.put(LanguageType.PRIMORDIAL, ScriptType.BARAZHAD);
		languageToScript.put(LanguageType.SUPERNAL, ScriptType.SUPERNAL);
		languageToScript.put(LanguageType.ABYSSAL, ScriptType.BARAZHAD);
		
		languageToRace = new HashMap<LanguageType, RaceType>();
		languageToRace.put(LanguageType.COMMON, RaceType.HUMAN);
		languageToRace.put(LanguageType.COMMON, RaceType.HALFLING);
		languageToRace.put(LanguageType.COMMON, RaceType.TIEFLING);
//		languageToRace.put(LanguageType.DEEP_SPEECH, RaceType.MIND_FLAYER);
//		languageToRace.put(LanguageType.DEEP_SPEECH, RaceType.GITHYANKL);
//		languageToRace.put(LanguageType.DEEP_SPEECH, RaceType.KUO_TOAS);
//		languageToRace.put(LanguageType.DRACONIC, RaceType.DRAGON);
		languageToRace.put(LanguageType.DRACONIC, RaceType.DRAGONBORN);
//		languageToRace.put(LanguageType.DRACONIC, RaceType.KOBOLDS);
		languageToRace.put(LanguageType.DWARVEN, RaceType.DWARF);
//		languageToRace.put(LanguageType.DWARVEN, RaceType.AZER);
		languageToRace.put(LanguageType.ELVEN, RaceType.ELF);
		languageToRace.put(LanguageType.ELVEN, RaceType.ELADRIN);
//		languageToRace.put(LanguageType.ELVEN, RaceType.FOMORLAN);
//		languageToRace.put(LanguageType.GLANT, RaceType.GLANTS);
//		languageToRace.put(LanguageType.GLANT, RaceType.ORCS);
//		languageToRace.put(LanguageType.GLANT, RaceType.OGRES);
//		languageToRace.put(LanguageType.GOBLIN, RaceType.GOBLIN);
//		languageToRace.put(LanguageType.GOBLIN, RaceType.HOBGOBLIN);
//		languageToRace.put(LanguageType.GOBLIN, RaceType.BUGBEAR);
//		languageToRace.put(LanguageType.PRIMORDIAL, RaceType.EFREETS);
//		languageToRace.put(LanguageType.PRIMORDIAL, RaceType.ARCHONS);
//		languageToRace.put(LanguageType.PRIMORDIAL, RaceType.ELEMENTAL);
//		languageToRace.put(LanguageType.SUPERNAL, RaceType.ANGEL);
//		languageToRace.put(LanguageType.SUPERNAL, RaceType.DEVIL);
//		languageToRace.put(LanguageType.SUPERNAL, RaceType.GOD);
//		languageToRace.put(LanguageType.ABYSSAL, RaceType.DEMON);
//		languageToRace.put(LanguageType.ABYSSAL, RaceType.GNOLL);
//		languageToRace.put(LanguageType.ABYSSAL, RaceType.SAHUAGIN);
	};
	
	private List<LanguageType> languages;
	private List<ScriptType> scripts;
	
	public Language() {
		
	}
	
	public Language(RaceType race) {
		languages = new ArrayList<LanguageType>();
		scripts = new ArrayList<ScriptType>();
		
		for(Entry<LanguageType, RaceType> map : languageToRace.entrySet()) {
			if(map.getValue() == race) {
				languages.add(map.getKey());
			}
		}
		
		for(LanguageType language : languages) {
			scripts.add(languageToScript.get(language));
		}
	}
	
	public List<LanguageType> getAllLanguages() {
		return languages;
	}
	
	public List<ScriptType> getAllScripts() {
		return scripts;
	}
	
	public boolean canSpeakLanguage(LanguageType language) {
		return languages.contains(language);
	}
	
	public boolean canReadScript(ScriptType script) {
		return scripts.contains(script);
	}
}
