package gui.util.managers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.loading.LoadingList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResourceManager { 
	private static ResourceManager _instance = new ResourceManager();
 
	private Map<String, Sound> soundMap;
	private Map<String, Image> imageMap;
	private Map<String, String> textMap;
	private Map<String, Font> fontMap;
 
	private ResourceManager(){
		soundMap 	 = new HashMap<String, Sound>();
		imageMap 	 = new HashMap<String, Image>();
		textMap 	 = new HashMap<String, String>();
		fontMap		 = new HashMap<String, Font>();
	}
 
	public final static ResourceManager getInstance(){
		return _instance;
	}
 
	public void loadResources(InputStream is) throws SlickException {
		loadResources(is, false);
	}
 
	public void loadResources(InputStream is, boolean deferred) throws SlickException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SlickException("Could not load resources", e);
		}
		Document doc = null;
        try {
			doc = docBuilder.parse (is);
		} catch (SAXException e) {
			throw new SlickException("Could not load resources", e);
		} catch (IOException e) {
			throw new SlickException("Could not load resources", e);
		}
 
		// normalize text representation
        doc.getDocumentElement ().normalize ();
 
        NodeList listResources = doc.getElementsByTagName("resource");
 
        int totalResources = listResources.getLength();
 
        if(deferred){
        	LoadingList.setDeferredLoading(true);
        } 
 
        for(int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++){
 
        	Node resourceNode = listResources.item(resourceIdx);
 
        	if(resourceNode.getNodeType() == Node.ELEMENT_NODE){
        		Element resourceElement = (Element)resourceNode;
 
        		String type = resourceElement.getAttribute("type");
 
        		if(type.equals("image")){
        			addElementAsImage(resourceElement);
        		}else if(type.equals("sound")){
        			addElementAsSound(resourceElement);
        		}else if(type.equals("text")){
        			addElementAsText(resourceElement);
        		}else if(type.equals("font")){
        			addElementAsFont(resourceElement);
        		}
        	}
        }
 
	}
 
	private void addElementAsFont(Element resourceElement) throws SlickException{
		loadFont(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
	
	public Font loadFont(String id, String path) throws SlickException {
		if(path == null || path.length() == 0)
			throw new SlickException("Font resource [" + id + "] has invalid path");
 
		Font startFont = null;
        try {
			startFont = Font.createFont(Font.TRUETYPE_FONT,
   new BufferedInputStream(ResourceManager.class.getClassLoader().getResourceAsStream(
   path)));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fontMap.put(id, startFont);
		
		return startFont;
	}
	 
	public final TrueTypeFont getFont(String ID, int size){
		Font baseFont = fontMap.get(ID).deriveFont(Font.PLAIN, size);
		return new TrueTypeFont(baseFont, true);
	}
	
	private void addElementAsText(Element resourceElement) throws SlickException{
		loadText(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
 
	public String loadText(String id, String value) throws SlickException{
		if(value == null)
			throw new SlickException("Text resource [" + id + "] has invalid value");
 
		textMap.put(id, value);
 
		return value;
	}
 
	public String getText(String ID) {
		return textMap.get(ID);
	}
 
	private void addElementAsSound(Element resourceElement) throws SlickException {
		loadSound(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
 
	public Sound loadSound(String id, String path) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Sound resource [" + id + "] has invalid path");
 
		Sound sound = null;
 
		try {
			sound = new Sound(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load sound", e);
		}
 
		this.soundMap.put(id, sound);
 
		return sound;
	}
 
	public final Sound getSound(String ID){
		return soundMap.get(ID);
	}
 
 
	private final void addElementAsImage(Element resourceElement) throws SlickException {
		loadImage(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
 
	public Image loadImage(String id, String path) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Image resource [" + id + "] has invalid path");
 
		Image image = null;
		try{
			image = new Image(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load image", e);
		}
 
		this.imageMap.put(id, image);
 
		return image;
	}
 
	public final Image getImage(String ID){
		return imageMap.get(ID);
	}
}