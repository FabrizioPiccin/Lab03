package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	Set<String> dizionario;
	
	public Dictionary(){
		dizionario = new HashSet<String>();
	}
	
	public void loadDictionary( String language){
		
		dizionario.clear();
		
		try{
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while((word = br.readLine()) != null){
				dizionario.add(word.toLowerCase());
			}
		}catch (IOException e){
			System.out.println("Errore lettura file");			
		}
		
	}
	
	public List<RichWord> spellCheckText( List<String> inputTextList){
		
		
		List<RichWord> parole = new LinkedList<RichWord>();
		
		for (String s : inputTextList){
			RichWord r = new RichWord(s);
			
			if( dizionario.contains(s)){
				r.setCorretta(true);		
			}

			parole.add(r);
			
		}
		return parole;
	}

	public void reset() {
		// TODO Auto-generated method stub
		dizionario.clear();
		
	}
	


}
