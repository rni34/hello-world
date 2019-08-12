package junitTests;
import main.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CrewMembers.*;
import Item.Apple;
import Item.BigHeal;
import Item.FoodItem;
import Item.Item;
import Item.MedicalItem;


class ActionsTest {
	private Game gameExample = new Game(3,3);
	private Crew crewExample = new Crew("fake_taxi", gameExample);
	public Actions actionsTest = new Actions(crewExample);
	
	@BeforeEach
	public void iniit() {
		gameExample.getPlanetList().get(0).setPartFound(true);
		crewExample.getCrewList().add(new Explorer("Adam"));
		crewExample.getCrewList().add(new Doctor("Carl"));
		crewExample.getCrewList().add(new Engineer("Daniel"));
		crewExample.getCrewList().add(new Captain("James"));

		
	}
	
	@Test
	public void testSearchPlanet() throws Exception {
		
		CrewMember member = crewExample.getCrewList().get(0);
		String check = actionsTest.searchPlanet(member, crewExample, gameExample.getPlanetList());
		assertEquals(member.getHunger(),90);
		assertEquals(member.getTiredness(),90);
		member.setRemainingActions(1000000000);
		ArrayList<String> expectedStringList = new ArrayList<String>();
		while(expectedStringList.size() != 110) {
			member.setRemainingActions(2);
			member.setHunger(100);
			member.setTiredness(100);
			check = actionsTest.searchPlanet(member, crewExample, gameExample.getPlanetList());
			if(!expectedStringList.contains(check)) {
				expectedStringList.add(check);
			
		}
		}
		assertEquals(expectedStringList.size(),110);
		member = crewExample.getCrewList().get(1);
		while(expectedStringList.size() != 110) {
			member.setRemainingActions(2);
			member.setHunger(100);
			member.setTiredness(100);
			check = actionsTest.searchPlanet(member, crewExample, gameExample.getPlanetList());
			if(!expectedStringList.contains(check)) {
				expectedStringList.add(check);
			
		}
		}
		assertEquals(expectedStringList.size(),110);
		member.setRemainingActions(0);
		check = actionsTest.searchPlanet(member, crewExample, gameExample.getPlanetList());
		assertEquals(check,"no action left");
		
	}

	@Test
	public void testEat() throws Exception {
		FoodItem Apple = new Apple();
		CrewMember member = crewExample.getCrewList().get(0);
		member.setRemainingActions(4);
		String check = actionsTest.eat(member, crewExample, Apple);
		assertEquals("Adam ate Apple<br/>now hunger level is 100",check);
		check = actionsTest.eat(member, crewExample, Apple);
		MedicalItem BigHeal = new BigHeal();
		check = actionsTest.medicate(member, crewExample, BigHeal);
		assertEquals(check,"Adam now has 100 hit points<br/>");
		check = actionsTest.medicate(member, crewExample, BigHeal);
	}
	
	@Test
	public void testEndDay() {
		ArrayList<String> expectedStringList = new ArrayList<String>();
		while(expectedStringList.size() < 10) {
			String check = actionsTest.endDay(crewExample);
			if(!expectedStringList.contains(check)) {
				expectedStringList.add(check);
		}
		}
		assertEquals(expectedStringList.size(),10);
	}
	
	@Test
	public void testflyShip(){
		ArrayList<String> expectedStringList = new ArrayList<String>();
		while(expectedStringList.size() < 2) {
	
			String check = actionsTest.flyShip(crewExample, crewExample.getCrewList().get(0), crewExample.getCrewList().get(1));
			if(!expectedStringList.contains(check)) {
				expectedStringList.add(check);
			}
		}
		assertEquals(expectedStringList.size(),2);
		
	}
	
	@Test
	public void testflyShipCaptain(){
		ArrayList<String> expectedStringList = new ArrayList<String>();
		while(expectedStringList.size() < 1) {
	
			String check = actionsTest.flyShip(crewExample, crewExample.getCrewList().get(0), crewExample.getCrewList().get(3));
			if(!expectedStringList.contains(check)) {
				expectedStringList.add(check);
			}
		}
		assertEquals(expectedStringList.size(),1);
		
	}
	
	@Test
	void testSleep(){
		
		String check = actionsTest.sleep(crewExample.getCrewList().get(0));
		
		System.out.println(check);
		assertEquals(check, "Adam is well rested.");
//		check = actionsTest.sleep(crewExample.getCrewList().get(0));
//		System.out.println(check);
//
//		assertEquals(check, "Adam is well rested.");
//		check = actionsTest.sleep(crewExample.getCrewList().get(0));
//		assertEquals(check, "no actions left");
//		System.out.println(check);

		
	}

	@Test
	public void testRepair(){
		
		String check = actionsTest.repairShip(crewExample.getCrewList().get(0), crewExample);
		assertEquals(check, "Adam repaired the ship.");
		
	}

}
