package junitTests;
import main.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CrewMembers.*;


class crewTest {
	private Game gameExample = new Game(3,3);
	private Crew crewTest = new Crew("fake_taxi", gameExample);
	
	@BeforeEach
	public void iniit() {
		Game gameExample = new Game(3,3);
		gameExample.getPlanetList().get(0).setPartFound(true);
		crewTest.getCrewList().add(new Captain("Adam"));
		CrewMember member2 = new Chef("Ben");
		member2.setSpacePlague(true);
		crewTest.getCrewList().add(member2);
		crewTest.getCrewList().add(new Doctor("Carl"));
		crewTest.getCrewList().add(new Engineer("Daniel"));
		

	}
	
	@Test
	public void testReturnString() throws Exception {
		assertEquals(crewTest.returnString(),"Ship name: fake_taxi<br>Ship health: 200<br>Crew members: <br>Captain, Adam. Tiredness: 100 Hunger: 100. Remaining actions: 2<br>Chef, Ben. is sick, Tiredness: 100 Hunger: 100. Remaining actions: 2<br>Doctor, Carl. Tiredness: 100 Hunger: 100. Remaining actions: 2<br>Engineer, Daniel. Tiredness: 100 Hunger: 100. Remaining actions: 2<br>");
	}
	@Test
	public void testGetPartsFound() throws Exception {
		assertEquals(crewTest.getPartsFound(),"No part has been found yet on Mercury<br>No part has been found yet on Venus<br>No part has been found yet on Earth<br>No part has been found yet on Mars<br>No part has been found yet on Jupiter<br>No part has been found yet on Saturn<br>No part has been found yet on Uranus<br>No part has been found yet on Neptune<br>No part has been found yet on Pluto<br>");
	}
	

}
