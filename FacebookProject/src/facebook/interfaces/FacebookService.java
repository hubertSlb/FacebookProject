package facebook.interfaces;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.NotFoundException;

import facebook.entities.Facebook;

public interface FacebookService {
	
	/**
	* Zwraca obiekt reprezentuj�cy profil Facebooka na podstawie id
     * w czasie logarytmicznym
	*/
	Facebook findById(String id) throws NotFoundException;

	/**
	* Zwraca map� kt�rej kluczem jest s�owo a warto�ci� liczba jego
     * wyst�pie� - pod uwag� brane s� wszystkie posty      
	*/
	Map<String, Long> findMostCommonWords();

	/**
	* Zwraca zbi�r id Post�w zawieraj�cych s�owo word
	*/
	Set<String> findPostIdsByKeyword(String word);

	/**
     * Zwraca zbi�r obiekt�w reprezentuj�cych profile Facebooka
     * posortowane po firstname, lastname
	*/
	Set<Facebook> findAll();

}
