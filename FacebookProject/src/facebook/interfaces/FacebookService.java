package facebook.interfaces;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.NotFoundException;

import facebook.entities.Facebook;

public interface FacebookService {
	
	/**
	* Zwraca obiekt reprezentuj¹cy profil Facebooka na podstawie id
     * w czasie logarytmicznym
	*/
	Facebook findById(String id) throws NotFoundException;

	/**
	* Zwraca mapê której kluczem jest s³owo a wartoœci¹ liczba jego
     * wyst¹pieñ - pod uwagê brane s¹ wszystkie posty      
	*/
	Map<String, Long> findMostCommonWords();

	/**
	* Zwraca zbiór id Postów zawieraj¹cych s³owo word
	*/
	Set<String> findPostIdsByKeyword(String word);

	/**
     * Zwraca zbiór obiektów reprezentuj¹cych profile Facebooka
     * posortowane po firstname, lastname
	*/
	Set<Facebook> findAll();

}
