package wordsuggestion.on.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordSuggestion {
	static String input;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (!"q".equalsIgnoreCase(input)) {
		System.out.println("Enter search String");
		 input = br.readLine();
		
		getSuggestions(input);
		}
		
	}

	
	public static void getSuggestions(String in){
		
		List<String> inputs = Arrays.asList("atom","boy","canned food","dalman sweater","Eatery");
		
		List<String> out = inputs.stream().filter(str -> str.contains(in)).collect(Collectors.toList());
		
				if(out.size()>0) {
		out.stream().forEach(m->System.out.println(m));
				}else {
					System.out.println("No suggestions Found");
				}
		
	}
	
	
	
}
